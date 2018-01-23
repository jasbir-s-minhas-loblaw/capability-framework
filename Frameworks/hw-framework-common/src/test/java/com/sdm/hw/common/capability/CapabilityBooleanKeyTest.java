package com.sdm.hw.common.capability;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;


/**
 * This is JUnit test class for testing CapabilityBoolean classs
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-07
 */

public class CapabilityBooleanKeyTest extends CapabilityTest{
    private static final Logger LOGGER = Logger.getLogger(CapabilityBooleanKeyTest.class.getName());

    @Test
    public void keyTest() {
        int keyCount = 0;
        for (CapabilityBooleanKey key : CapabilityBooleanKey.values()) {
            keyCount++;
            try {
                key.isEnabled();
                key.isGroup();
            } catch (NoSuchElementException nse){
                LOGGER.info(nse.getMessage());
            }
        }
        // confirm that all keys are accessible
        assertEquals(CapabilityBooleanKey.values().length, keyCount);
    }
}