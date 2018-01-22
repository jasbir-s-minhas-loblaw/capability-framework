package com.sdm.hw.common.capability;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * This is JUnit test class for testing CapabilityBoolean classs
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-07
 */

public class CapabilityBooleanKeyTest extends CapabilityTest{

    @Test
    public void keyTest() {
        int keyCount = 0;
        for (CapabilityBooleanKey key : CapabilityBooleanKey.values()) {
            key.isEnabled();
            key.isGroup();
            keyCount++;
        }
        // confirm that all keys are accessible
        assertEquals(CapabilityBooleanKey.values().length, keyCount);
    }
}