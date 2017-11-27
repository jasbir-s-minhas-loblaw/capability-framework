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
package com.sdm.hw.common.capability.db.dao.impl;

import com.sdm.hw.common.capability.db.dao.StorePreferenceDAO;
import com.sdm.hw.common.capability.db.entity.StorePreference;
import com.sdm.hw.logging.intf.HwLogger;
import com.sdm.hw.logging.services.LogManager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

//import com.sdm.hw.exception.services.HwBaseAppException;
//import com.sdm.hw.hibernate.HwDAOHelper;

/**
 * The Class StorePreferenceDAOImpl.
 */
@Repository
public class StorePreferenceDAOImpl implements StorePreferenceDAO
{

    /** The hw base persistence. */
//    IHwBasePersistence hwBasePersistence;

    /** The hw dao helper. */
//    HwDAOHelper hwDAOHelper;

    @PersistenceContext
    private EntityManager entityManager;

    /** The Constant logger. */
    private static final HwLogger logger = LogManager
            .getLogger(StorePreferenceDAOImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.sdm.hw.store.dao.StorePreferenceDAO#findAll()
     */
    @Override
    public List<StorePreference> findAll() throws Exception
    {
//        final Query query = this.hwBasePersistence
//                .createNamedQuery("STOREPREFERENCE_QUERY");
//        final List<StorePreference> storePreferences = query.getResultList();
//        return storePreferences;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StorePreference> criteriaQuery = builder.createQuery(StorePreference.class);
        Root<StorePreference> root = criteriaQuery.from(StorePreference.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public StorePreference findByCDSP(String CDSP) throws Exception {
        TypedQuery<StorePreference> query =
                entityManager.createQuery("from StorePreference sp where sp.cdsp = 'STORE_EHR_JURISDICTION' ",
                        StorePreference.class);
        return query.getSingleResult();
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sdm.hw.store.dao.StorePreferenceDAO#findByPrimaryKey(java.lang.String
     * )
     */
//    @Override
//    public StorePreference findByPrimaryKey(final String id)
//        throws HwBaseAppException
//    {
//        final StorePreference storePreference = this.hwBasePersistence.read(
//                StorePreference.class, id);
//        return storePreference;
//    }

//    /**
//     * Sets the hw base persistence.
//     *
//     * @param hwBasePersistence
//     *            the new hw base persistence
//     */

//    public void setHwBasePersistence(final IHwBasePersistence hwBasePersistence)
//    {
//        this.hwBasePersistence = hwBasePersistence;
//    }

//    /**
//     * Sets the hw dao helper.
//     *
//     * @param hwDAOHelper
//     *            the new hw dao helper
//     */
//    public void setHwDAOHelper(final HwDAOHelper hwDAOHelper)
//    {
//        this.hwDAOHelper = hwDAOHelper;
//    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sdm.hw.common.capability.db.dao.StorePreferenceDAO#updateStorePreference
     */
//    @Override
//    public void updateStorePreference(
//        final StorePreference generateStorePreference)
//        throws Exception
//    {
////        hwDAOHelper.update(generateStorePreference);
//        logger.logDebug("Store Preferences Updated");
//    }
}
