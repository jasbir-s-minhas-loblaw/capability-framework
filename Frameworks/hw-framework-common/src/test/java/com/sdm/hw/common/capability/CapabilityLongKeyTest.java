package com.sdm.hw.common.capability;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is JUnit test class for testing CapabilityLongKeyTest classs
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-07
 */

public class CapabilityLongKeyTest extends CapabilityTest{
    @Test
    public void keyTest() {
        int keyCount = 0;
        for (CapabilityLongKey key : CapabilityLongKey.values()) {
            key.getLong();
            key.isGroup();
            keyCount++;
        }
        // confirm that all keys are accessible
        assertEquals(CapabilityLongKey.values().length, keyCount);
    }
}