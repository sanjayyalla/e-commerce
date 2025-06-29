package com.jocata.data.review.impl;

import com.jocata.data.review.ReviewDao;
import com.jocata.datamodel.reviewandratings.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ReviewDaoImpl implements ReviewDao {

    @PersistenceContext
    private EntityManager em;

    public Review save(Review review) {
        em.persist(review);
        return review;
    }

    public List<Review> findByProductId(Integer productId) {
        return em.createQuery("SELECT r FROM Review r WHERE r.productId = :pid", Review.class)
                .setParameter("pid", productId)
                .getResultList();
    }
}
