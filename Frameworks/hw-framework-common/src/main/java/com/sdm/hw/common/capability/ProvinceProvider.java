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


    private static ProvinceProvider provinceProvider;

    private Province province;

    /**
     * Private constructor implementing singleton
     */
    private ProvinceProvider() {
    }

    public static synchronized ProvinceProvider getInstance() {
        if (provinceProvider == null) {
            provinceProvider = new ProvinceProvider();
            String provinceCode = provinceProvider.getProvinceCodeFromDB();
            provinceProvider.province = Province.getProvince(provinceCode);
        }
        return provinceProvider;
    }

    /**
     *  Reloads province code from database
     */
    public static synchronized void reloadProvince() {
        ProvinceProvider provinceProvider = getInstance();
        String provinceCode = provinceProvider.getProvinceCodeFromDB();
        provinceProvider.province = Province.getProvince(provinceCode);
    }

    private String getProvinceCodeFromDB() {

        String provCode = null;

        // get application context from the classpath
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");

        // get bean from the Spring container
        StorePreferenceManager storePreferenceManager =
                applicationContext.getBean("storePreferenceManager", StorePreferenceManager.class);

        try {
            // get province
            provCode = storePreferenceManager.getProvince();
        } catch (Exception ex) {
            LOGGER.logFatal("Exception getting province from database.", ex.getMessage(), ex);
        } finally {
            applicationContext.close();
            return provCode;
        }
    }


    /**
     * @return province for the current province
     */
    Province getCurrentProvince() {
        return province;
    }

    /**
     *
     * @param province
     */
    void setCurrentProvince(Province province){
        this.province = province;
    }
}

