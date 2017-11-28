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

    /**
     *
     * @return List containing all StorePreference objects
     * @throws Exception
     */
    @Override
    public List<StorePreference> findAll() throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StorePreference> criteriaQuery = builder.createQuery(StorePreference.class);
        Root<StorePreference> root = criteriaQuery.from(StorePreference.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     *
     * @param CDSP
     * @return
     * @throws Exception
     */
    @Override
    public StorePreference findByCDSP(String CDSP) throws Exception {
        TypedQuery<StorePreference> query =
                entityManager.createQuery("from StorePreference sp where sp.cdsp = '" + CDSP + "'",
                        StorePreference.class);
        return query.getSingleResult();
    }
}
