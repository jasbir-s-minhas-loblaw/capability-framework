package com.sdm.hw.common.capability;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * This is JUnit test class for testing CapabilityDoubleKeyTest classs
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-07
 */

public class CapabilityDoubleKeyTest extends CapabilityTest {
    private static final Logger LOGGER = Logger.getLogger(CapabilityDoubleKeyTest.class.getName());

    @Test
    public void keyTest() {
        int keyCount = 0;
        for (CapabilityDoubleKey key : CapabilityDoubleKey.values()) {
            keyCount++;
            try {
                key.getDouble();
                key.isGroup();
            } catch (NoSuchElementException nse){
                LOGGER.info(nse.getMessage());
            }
        }
        // confirm that all keys are accessible
        assertEquals(CapabilityDoubleKey.values().length, keyCount);
    }
}