package com.sdm.hw.common.capability.persistence.service.impl;

import com.sdm.hw.common.capability.persistence.dao.StorePreferenceDAO;
import com.sdm.hw.common.capability.persistence.entity.StorePreference;
import com.sdm.hw.common.capability.persistence.service.StorePreferenceManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * This is JUnit test class
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2018-01-21
 */

public class StorePreferenceManagerImplTest {

    private StorePreference storePreference = new StorePreference();
    private List<StorePreference> storePreferenceList = new ArrayList<>();

    @Mock
    private StorePreferenceDAO storePreferenceDAO;

    @InjectMocks
    private StorePreferenceManager storePreferenceManager = new StorePreferenceManagerImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        storePreference.setCdsp("STORE_EHR_JURISDICTION");
        storePreference.setValue("ON");
        // for testing StorePreference List
        for (int i = 0;i < 10; i++) {
            storePreferenceList.add(new StorePreference());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findAll()  throws Exception {
        // setup mock objects
        given(storePreferenceDAO.findAll()).willReturn(storePreferenceList);
        // assert
        assertEquals(storePreferenceList.size(), storePreferenceManager.findAll().size());
        // verify the mock objects were executed as expected
        verify(storePreferenceDAO, times(1)).findAll();
    }

    @Test
    public void findByCDSP() throws Exception {
        given(storePreferenceDAO.findByCDSP(anyString())).willReturn(storePreference);
        assertEquals(storePreference, storePreferenceManager.findByCDSP(anyString()));
        // verify the mock objects were executed as expected
        verify(storePreferenceDAO, times(1)).findByCDSP(anyString());
    }

    @Test
    public void getProvince() throws  Exception {
        // setup mock objects
        given(storePreferenceDAO.findByCDSP(anyString())).willReturn(storePreference);
        // assert
        assertEquals("ON", storePreferenceManager.getProvince());
        // verify the mock objects were executed as expected
        verify(storePreferenceDAO, times(1)).findByCDSP(anyString());
    }
}