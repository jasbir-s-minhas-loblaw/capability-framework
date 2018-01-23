package com.sdm.hw.common.capability;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * This is JUnit test class for testing CapabilityStringKeyTest classs
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-07
 */

public class CapabilityStringKeyTest extends CapabilityTest{
    private static final Logger LOGGER = Logger.getLogger(CapabilityStringKeyTest.class.getName());

    @Test
    public void keyTest() {
        int keyCount = 0;
        for (CapabilityStringKey key : CapabilityStringKey.values()) {
            keyCount++;
            try {
                key.getString();
                key.isGroup();
            } catch (NoSuchElementException nse){
                LOGGER.info(nse.getMessage());
            }
        }
        // confirm that all keys are accessible
        assertEquals(CapabilityStringKey.values().length, keyCount);
    }
}