package com.sdm.hw.common.capability;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is JUnit test class
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2018-01-21
 */

//Load Spring context
//@ContextConfiguration(locations = {"classpath:/ApplicationContext.xml"})
//@RunWith(SpringJUnit4ClassRunner.class)
public class ProvinceProviderTest {


    private ProvinceProvider  provinceProvider = ProvinceProvider.getInstance();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getInstance() {
        ProvinceProvider provinceProvider2 = ProvinceProvider.getInstance();
        assertEquals(provinceProvider, provinceProvider2);
    }
    @Test
    public void getCurrentProvince() {
        provinceProvider.setCurrentProvince(Province.NEW_BRUNSWICK);
        assertEquals(Province.NEW_BRUNSWICK, provinceProvider.getCurrentProvince());
    }

    @Ignore("Ignored for regular unit testing as it tests Spring->JPA-Hibernate->DB access.\n" +
            "It should be executed when any code change is performed in this package.")
    @Test
    public void getCurrentProvinceFromDB() {
        assertEquals(Province.NEW_BRUNSWICK, provinceProvider.getCurrentProvince());
    }

}