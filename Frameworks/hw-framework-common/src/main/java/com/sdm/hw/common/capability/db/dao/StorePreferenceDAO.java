// === File Prolog ===========================================================
// This code was developed for SDM, HealthWatch, Code 520
// for the Next Generation 6.0 (NG) project.
//
// --- Notes -----------------------------------------------------------------
// This class requires JDK version 1.1 or later.
//
// --- Development History ---------------------------------------------------
//
// 10/07/13 TCS
//
// Initial version.
//
// 10/07/13 TCS
//
// Converted class to comply with JavaBeans conventions.
// Now uses serialization to send/receive event objects.
//
// --- Warning ---------------------------------------------------------------
// This software is property of the Shoppers Drug Mart.
// Unauthorized use or duplication of this software is
// strictly prohibited. Authorized users are subject to the following
// restrictions:
// * Neither the author, their corporation, nor SDM is responsible for
// any consequence of the use of this software.
// * The origin of this software must not be misrepresented either by
// explicit claim or by omission.
// * Altered versions of this software must be plainly marked as such.
// * This notice may not be removed or altered.
//
// === End File Prolog =======================================================
package com.sdm.hw.common.capability.db.dao;

import com.sdm.hw.common.capability.db.entity.StorePreference;
import java.util.List;

/**
 * The Interface StorePreferenceDAO.
 */
public interface StorePreferenceDAO {
    /**
     * Find all.
     * @return the list
     * @throws Exception
     *             the hw base app exception
     */
    List<StorePreference> findAll() throws Exception;

    StorePreference findByCDSP(String CDSP) throws Exception;

    /**
     * Find by primary key.
     * 
     * @param id
     *
     *            the id
     * @return the store preference
     * @throws Exception
     *             the hw base app exception
     */
//    StorePreference findByPrimaryKey(String id) throws Exception;

    /**
     * Update store preference.
     * 
     * @param generateStorePreference
     *            the generate store preference
     * @throws Exception
     *             the hw base app exception
     */
//    void updateStorePreference(StorePreference generateStorePreference)
//        throws Exception;
}
