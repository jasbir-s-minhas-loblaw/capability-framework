package com.sdm.hw.common.capability;

import com.sdm.hw.common.capability.persistence.service.StorePreferenceManager;
import com.sdm.hw.logging.intf.HwLogger;
import com.sdm.hw.logging.services.LogManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class provides the current province
 */
public final class ProvinceProvider {

    private static final HwLogger LOGGER = LogManager.getLogger(CapabilityManager.class);
    private static ProvinceProvider _instance = new ProvinceProvider();
    private Province province;

    /**
     * Private constructor implementing singleton
     */
    private ProvinceProvider() {
    }

    public static ProvinceProvider getInstance() {
        return _instance;
    }

    private String getProvinceCodeFromDB() {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");

        String provCode = null;

        // get bean from the Spring container
        StorePreferenceManager storePreferenceManager =
                applicationContext.getBean("storePreferenceManager", StorePreferenceManager.class);

        try {
            // get province
            provCode = storePreferenceManager.getProvince();
        } catch (Exception ex) {
            LOGGER.logFatal("Exception getting province from database.", ex.getMessage(), ex);
        }
        applicationContext.close();
        return provCode;
    }

    /**
     * @return province for the current province
     */
    public Province getCurrentProvince() {
    	if ( province == null){
            String provinceCode = getProvinceCodeFromDB();
            province = Province.getProvince(provinceCode);
    	}
        return province;
    }

    /**
     *
     * @param province province
     */
    public void setCurrentProvince(Province province){
        this.province = province;
    }
}

