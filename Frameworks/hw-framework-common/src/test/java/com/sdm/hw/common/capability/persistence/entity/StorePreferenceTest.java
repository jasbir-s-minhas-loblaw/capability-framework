package com.sdm.hw.common.capability.persistence.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is JUnit test class
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2018-01-21
 */

public class StorePreferenceTest {

    private StorePreference storePreference = new StorePreference();

    @Before
    public void setUp() {
        storePreference.setCdsp("STORE_EHR_JURISDICTION");
        storePreference.setValue("ON");
        storePreference.setDescription("Some Description");
    }

    @Test
    public void getCdsp() {
        assertEquals("STORE_EHR_JURISDICTION", storePreference.getCdsp());
    }

    @Test
    public void getValue() {
        assertEquals("ON", storePreference.getValue());
    }

    @Test
    public void getDescription() {
        assertEquals("Some Description", storePreference.getDescription());
    }

    @Test
    public void storePreference(){
        String sysoutStr = "StorePreference{cdsp='STORE_EHR_JURISDICTION', value='ON', description='Some Description'}";
        StorePreference storePreference2 =
                new StorePreference("STORE_EHR_JURISDICTION", "ON",
                        "Some Description");
        assertEquals(sysoutStr, storePreference2.toString());

    }
}