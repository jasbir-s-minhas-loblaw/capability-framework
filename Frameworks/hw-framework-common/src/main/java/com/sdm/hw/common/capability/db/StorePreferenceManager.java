package com.sdm.hw.common.capability.db;

import com.sdm.hw.common.capability.db.entity.StorePreference;

import java.util.List;

public interface StorePreferenceManager {
    List<StorePreference> findAll();
    StorePreference findByCDSP(String CDSP);
}
