package com.sdm.hw.common.capability;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * This is JUnit test class for testing CapabilityLongKeyTest classs
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-07
 */

public class CapabilityLongKeyTest extends CapabilityTest{
    private static final Logger LOGGER = Logger.getLogger(CapabilityLongKeyTest.class.getName());

    @Test
    public void keyTest() {
        int keyCount = 0;
        for (CapabilityLongKey key : CapabilityLongKey.values()) {
            keyCount++;
            try {
                key.getLong();
                key.isGroup();
            } catch (NoSuchElementException nse){
                LOGGER.info(nse.getMessage());
            }
        }
        // confirm that all keys are accessible
        assertEquals(CapabilityLongKey.values().length, keyCount);
    }
}