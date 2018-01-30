package com.sdm.hw.store.services;

import com.sdm.hw.store.dto.SubEHealthConfigConstants;
import com.sdm.hw.exception.services.HwBaseAppException;

public class HwStoreServiceImpl {
    /**
     * @deprecated (Should pass enum rather than passing a string)
     * @param capabilityName
     * @return
     * @throws HwBaseAppException
     */
    @Deprecated
    public boolean isSubEHealthEnabled(String capabilityName)
            throws HwBaseAppException {
        /// New Code
        return SubEHealthConfigConstants.getConstantObj(capabilityName).isEnabled();
        /// End of new Code
    }
}
