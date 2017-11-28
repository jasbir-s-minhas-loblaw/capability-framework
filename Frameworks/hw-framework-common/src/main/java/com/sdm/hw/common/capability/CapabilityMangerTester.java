package com.sdm.hw.common.capability;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Testing of CapabilityManager requires multi-threading or human intervention. This class in meant to be used
 *  during development to validate complex JUnit scenarios where a developer is modify the configuration file while the
 *  test is running.
 */
public class CapabilityMangerTester {

    private static final Logger LOGGER = Logger.getLogger(CapabilityMangerTester.class.getName());

    public static void main(String[] args) throws Exception {
        test();
    }


    private static void test() {
        ProvinceProvider provinceProvider = ProvinceProvider.getInstance();
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(5);
                LOGGER.info(System.currentTimeMillis() + " : "
                        + "Value of " + CapabilityBooleanKey.ALLERGY_STATUS + " : "
                        + CapabilityBooleanKey.ALLERGY_STATUS.isEnabled()
                        + " | Province : " + provinceProvider.getCurrentProvince());
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }
    }
}
