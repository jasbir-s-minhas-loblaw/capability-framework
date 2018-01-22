package com.sdm.hw.store.dto;

import com.sdm.hw.common.capability.Province;
import com.sdm.hw.common.capability.ProvinceProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubEHealthConfigConstantsTest {

    @Before
    public void setUp() throws Exception {
        // Initialized province to avoid reading from DB in JUnit
        ProvinceProvider.getInstance().setCurrentProvince(Province.ONTARIO);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isEnabled() throws Exception {
        int constCount = 0;
        for (SubEHealthConfigConstants subEHealthConfigConstants : SubEHealthConfigConstants.values()){
            subEHealthConfigConstants.getRuleName();
            subEHealthConfigConstants.isEnabled();
            subEHealthConfigConstants.getString();
            constCount++;
        }
        // confirm that all keys are accessible
        assertEquals(SubEHealthConfigConstants.values().length, constCount);
    }
    @Test
    public void containsTest() throws Exception{
        assertEquals(true, SubEHealthConfigConstants.contains("SUB_EHEALTH"));
    }

    @Test
    public void getConstObjectTest() throws Exception {
        assertEquals(SubEHealthConfigConstants.SUB_EHEALTH,
                SubEHealthConfigConstants.getConstantObj("SUB_EHEALTH"));
    }
}