package com.sdm.hw.common.capability.db;

import com.sdm.hw.common.capability.db.dao.StorePreferenceDAO;
import com.sdm.hw.common.capability.db.entity.StorePreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorePreferenceManagerImpl implements StorePreferenceManager {

    @Autowired
    private StorePreferenceDAO storePreferenceDAO;

    @Override
    public List<StorePreference> findAll() {
        return null;
    }

    @Override
    public StorePreference findByCDSP(String CDSP) {
        return null;
    }
}
