package com.sdm.hw.common.capability;

import com.sdm.hw.common.capability.persistence.entity.StorePreference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
    public void getCurrentProvince() throws Exception {

        TypedQuery<StorePreference> query  = Mockito.mock(TypedQuery.class);
        EntityManager entityManager = Mockito.mock(EntityManager.class);
        StorePreference storePreference = new StorePreference();
        storePreference.setCdsp("STORE_EHR_JURISDICTION");
        storePreference.setValue("ON");
        Mockito.when(query.getSingleResult()).thenReturn(storePreference);
        Mockito.when(entityManager.createQuery(Mockito.anyString(), StorePreference.class)).thenReturn(query);
        assertEquals(Province.NEW_BRUNSWICK, provinceProvider.getCurrentProvince());
    }
}