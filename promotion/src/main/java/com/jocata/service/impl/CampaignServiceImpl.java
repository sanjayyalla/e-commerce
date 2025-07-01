package com.jocata.service.impl;

import com.jocata.service.CampaignService;

import com.jocata.data.promotion.CampaignDao;
import com.jocata.datamodel.promotion.entity.Campaign;
import com.jocata.datamodel.promotion.form.CampaignForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignDao campaignDao;

    @Override
    public CampaignForm createCampaign(CampaignForm form) {
        Campaign campaign = new Campaign();
        campaign.setName(form.getName());
        campaign.setDescription(form.getDescription());
        campaign.setDiscountPercentage(Double.parseDouble(form.getDiscountPercentage()));
        campaign.setStartDate(LocalDate.parse(form.getStartDate()));
        campaign.setEndDate(LocalDate.parse(form.getEndDate()));

        Campaign saved = campaignDao.save(campaign);

        CampaignForm response = toForm(saved);
        response.setMessage("Campaign created successfully");
        return response;
    }

    @Override
    public List<CampaignForm> getAllCampaigns() {
        List<CampaignForm> result = new ArrayList<>();
        for (Campaign c : campaignDao.findAll()) {
            result.add(toForm(c));
        }
        return result;
    }

    private CampaignForm toForm(Campaign c) {
        CampaignForm form = new CampaignForm();
        form.setId(String.valueOf(c.getId()));
        form.setName(c.getName());
        form.setDescription(c.getDescription());
        form.setDiscountPercentage(String.valueOf(c.getDiscountPercentage()));
        form.setStartDate(c.getStartDate().toString());
        form.setEndDate(c.getEndDate().toString());
        return form;
    }
}
