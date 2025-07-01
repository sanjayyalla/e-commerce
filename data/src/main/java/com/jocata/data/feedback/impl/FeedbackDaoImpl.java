package com.jocata.data.feedback.impl;

import com.jocata.data.feedback.FeedbackDao;
import com.jocata.datamodel.reviewandratings.entity.Feedback;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class FeedbackDaoImpl implements FeedbackDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Feedback save(Feedback feedback) {
        return em.merge(feedback);
    }

    @Override
    public List<Feedback> getFeedbacksByProductId(Integer productId) {
        return em.createQuery("SELECT f FROM Feedback f WHERE f.productId = :productId", Feedback.class)
                .setParameter("productId", productId)
                .getResultList();
    }
}