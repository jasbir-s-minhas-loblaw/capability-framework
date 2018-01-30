package com.sdm.hw.common.capability.persistence.service;

import com.sdm.hw.common.capability.persistence.entity.StorePreference;

import java.util.List;
/**
 * This class is a service interface to access province from database.
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-20
 *
 */

public interface StorePreferenceManager {
    List<StorePreference> findAll();
    StorePreference findByCDSP(String cdsp);
    String getProvince();
}
