package com.jocata.data.rating.impl;

import com.jocata.data.rating.RatingDao;
import com.jocata.datamodel.reviewandratings.entity.Rating;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class RatingDaoImpl implements RatingDao {

    @PersistenceContext
    private EntityManager em;

    public Rating save(Rating rating) {
        em.persist(rating);
        return rating;
    }

    public Double findAverageRatingByProductId(Integer productId) {
        Double avg = em.createQuery("SELECT AVG(r.rating) FROM Rating r WHERE r.productId = :pid", Double.class)
                .setParameter("pid", productId)
                .getSingleResult();
        return avg;
    }
}
