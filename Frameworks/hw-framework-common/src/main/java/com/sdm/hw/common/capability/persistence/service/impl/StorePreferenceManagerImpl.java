package com.sdm.hw.common.capability.persistence.service.impl;

import com.sdm.hw.common.capability.persistence.dao.StorePreferenceDAO;
import com.sdm.hw.common.capability.persistence.entity.StorePreference;
import com.sdm.hw.common.capability.persistence.service.StorePreferenceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class is an implementation of a service interface to access province from database.
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-20
 *
 */

@Service
@Transactional( propagation = Propagation.SUPPORTS,readOnly = true )
public class StorePreferenceManagerImpl implements StorePreferenceManager {

    @Autowired
    private StorePreferenceDAO storePreferenceDAO;

    @Override
    public List<StorePreference> findAll() throws Exception{
        return storePreferenceDAO.findAll();
    }

    @Override
    public StorePreference findByCDSP(String CDSP) throws Exception{
        return storePreferenceDAO.findByCDSP(CDSP);
    }

    @Override
    public String getProvince() throws Exception {
        return storePreferenceDAO.findByCDSP("STORE_EHR_JURISDICTION").getValue();
    }

}
