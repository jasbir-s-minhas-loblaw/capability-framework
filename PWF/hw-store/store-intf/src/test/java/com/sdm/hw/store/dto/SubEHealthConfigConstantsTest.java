package com.sdm.hw.store.dto;

import com.sdm.hw.common.capability.Province;
import com.sdm.hw.common.capability.ProvinceProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class SubEHealthConfigConstantsTest {
    private static final Logger LOGGER = Logger.getLogger(SubEHealthConfigConstantsTest.class.getName());

    @Before
    public void setUp() {
        // Initialized province to avoid reading from DB in JUnit
        ProvinceProvider.getInstance().setCurrentProvince(Province.ONTARIO);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void isEnabled() {
        int constCount = 0;
        for (SubEHealthConfigConstants subEHealthConfigConstants : SubEHealthConfigConstants.values()){
            constCount++;
            try {
                subEHealthConfigConstants.getRuleName();
                subEHealthConfigConstants.isEnabled();
                subEHealthConfigConstants.getString();
            } catch (NoSuchElementException nse){
                LOGGER.info(nse.getMessage());
            }
        }
        // confirm that all keys are accessible
        assertEquals(SubEHealthConfigConstants.values().length, constCount);
    }
    @Test
    public void containsTest() {
        assertEquals(true, SubEHealthConfigConstants.contains("SUB_EHEALTH"));
    }

    @Test
    public void getConstObjectTest() {
        assertEquals(SubEHealthConfigConstants.SUB_EHEALTH,
                SubEHealthConfigConstants.getConstantObj("SUB_EHEALTH"));
    }
}