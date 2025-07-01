package com.jocata.data.promotion.impl;

import com.jocata.data.promotion.CampaignDao;
import com.jocata.datamodel.promotion.entity.Campaign;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CampaignDaoImpl implements CampaignDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Campaign save(Campaign campaign) {
        return entityManager.merge(campaign);
    }

    @Override
    public List<Campaign> findAll() {
        return entityManager.createQuery("SELECT c FROM Campaign c", Campaign.class).getResultList();
    }
}
