package com.sdm.hw.common.capability.persistence.dao.impl;

import com.sdm.hw.common.capability.persistence.dao.StorePreferenceDAO;
import com.sdm.hw.common.capability.persistence.entity.StorePreference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
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

public class StorePreferenceDAOImplTest {

    private StorePreference storePreference = new StorePreference();
    private List<StorePreference> storePreferenceList = new ArrayList<>();

    @Mock
    private EntityManager entityManagerMock;

    @Mock
    private CriteriaBuilder builderMock;

    @Mock
    private TypedQuery<StorePreference> typedQueryMock;

    @Mock
    private CriteriaQuery<StorePreference> criteriaQueryMock;

    @Mock
    private Root<StorePreference> rootMock;

    @InjectMocks
    private StorePreferenceDAO storePreferenceDAO = new StorePreferenceDAOImpl();

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
    public void findAll() throws Exception {
        // setup mock objects
        given(entityManagerMock.getCriteriaBuilder()).willReturn(builderMock);
        given(builderMock.createQuery(any(Class.class))).willReturn(criteriaQueryMock);
        given(criteriaQueryMock.from(any(Class.class))).willReturn(rootMock);
        given(criteriaQueryMock.select(rootMock)).willReturn(criteriaQueryMock);
        given(entityManagerMock.createQuery(criteriaQueryMock)).willReturn(typedQueryMock);
        given(typedQueryMock.getResultList()).willReturn(storePreferenceList);

        // assert
        assertEquals(storePreferenceList, storePreferenceDAO.findAll());

        // verify the mock objects were executed as expected
        verify(entityManagerMock, times(1)).getCriteriaBuilder();
        verify(builderMock, times(1)).createQuery(any(Class.class));
        verify(criteriaQueryMock, times(1)).from(any(Class.class));
        verify(criteriaQueryMock, times(1)).select(rootMock);
        verify(entityManagerMock, times(1)).createQuery(criteriaQueryMock);
        verify(typedQueryMock, times(1)).getResultList();
    }

    @Test
    public void findByCDSP() throws Exception {
        // setup mock objects
        given(entityManagerMock.createQuery(anyString(),any(Class.class))).willReturn(typedQueryMock);
        given(typedQueryMock.setParameter(anyString(), anyString())).willReturn(typedQueryMock);
        given(typedQueryMock.getSingleResult()).willReturn(storePreference);

        // assert
        assertEquals(storePreference, storePreferenceDAO.findByCDSP("STORE_EHR_JURISDICTION"));

        // verify the mock objects were executed as expected
        verify(entityManagerMock, times(1)).createQuery(anyString(),any(Class.class));
        verify(typedQueryMock, times(1)).getSingleResult();
    }
}