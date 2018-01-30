package com.sdm.hw.common.capability.persistence.dao.impl;

import com.sdm.hw.common.capability.persistence.dao.StorePreferenceDAO;
import com.sdm.hw.common.capability.persistence.entity.StorePreference;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

 /**
 *  StorePreferenceDAO implementation.
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-20
 */
@Repository
public class StorePreferenceDAOImpl implements StorePreferenceDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String QUERY = "from StorePreference sp where sp.cdsp = :CDSP";

    /**
     *
     * @return List containing all StorePreference objects
     * @throws Exception
     */
    @Override
    public List<StorePreference> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StorePreference> criteriaQuery = builder.createQuery(StorePreference.class);
        Root<StorePreference> root = criteriaQuery.from(StorePreference.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     *
     * @param cdsp
     * @return StorePreference Store Preference
     */
    @Override
    public StorePreference findByCDSP(String cdsp) {
        TypedQuery<StorePreference> query =
                entityManager
                        .createQuery(QUERY, StorePreference.class)
                        .setParameter("CDSP", cdsp);
        return query.getSingleResult();
    }
}
