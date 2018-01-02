package com.sdm.hw.common.capability;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProvinceProviderTest {

    ProvinceProvider provinceProvider = ProvinceProvider.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getInstance() throws Exception {
        ProvinceProvider provinceProvider2 = ProvinceProvider.getInstance();
        assertEquals(provinceProvider, provinceProvider2);
    }

    @Test
    public void reloadProvince() throws Exception {
    }

    @Test
    public void getCurrentProvince() throws Exception {
        assertEquals(Province.NEW_BRUNSWICK, provinceProvider.getCurrentProvince());
    }

}