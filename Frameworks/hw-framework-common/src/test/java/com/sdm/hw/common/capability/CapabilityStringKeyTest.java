package com.sdm.hw.common.capability;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is JUnit test class for testing CapabilityStringKeyTest classs
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-07
 */

public class CapabilityStringKeyTest extends CapabilityTest{
    @Test
    public void keyTest() {
        int keyCount = 0;
        for (CapabilityStringKey key : CapabilityStringKey.values()) {
            key.getString();
            key.isGroup();
            keyCount++;
        }
        // confirm that all keys are accessible
        assertEquals(CapabilityStringKey.values().length, keyCount);
    }
}