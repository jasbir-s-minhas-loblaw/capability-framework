package com.sdm.hw.common.capability;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is JUnit test class
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2018-01-21
 */

public class ProvinceTest {

    @Test
    public void keyTest(){
        int provinceCount = 0;
        for (Province province : Province.values()) {
            Province.getProvince(province.getCode());
            provinceCount++;
        }
        // confirm that all keys are accessible
        assertEquals(Province.values().length, provinceCount);
    }

    @Test
    public void getCodeTest() {
        assertEquals("ON", Province.ONTARIO.getCode());
        assertEquals("AB", Province.ALBERTA.getCode());
        assertEquals("BC", Province.BRITISH_COLUMBIA.getCode());
        assertEquals("MB", Province.MANITOBA.getCode());
        assertEquals("NB", Province.NEW_BRUNSWICK.getCode());
        assertEquals("NL", Province.NEWFOUNDLAND_AND_LABRADOR.getCode());
        assertEquals("NT", Province.NORTHWEST_TERRITORIES.getCode());
        assertEquals("NS", Province.NOVA_SCOTIA.getCode());
        assertEquals("NU", Province.NUNAVUT.getCode());
        assertEquals("ON", Province.ONTARIO.getCode());
        assertEquals("PE", Province.PRINCE_EDWARD_ISLAND.getCode());
        assertEquals("QC", Province.QUEBEC.getCode());
        assertEquals("SK", Province.SASKATCHEWAN.getCode());
        assertEquals("YT", Province.YUKON.getCode());
    }
}