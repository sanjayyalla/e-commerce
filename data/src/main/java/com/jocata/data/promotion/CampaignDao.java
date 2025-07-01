package com.jocata.data.promotion;

import com.jocata.datamodel.promotion.entity.Campaign;

import java.util.List;

public interface CampaignDao {

    Campaign save(Campaign campaign);

    List<Campaign> findAll();

}
