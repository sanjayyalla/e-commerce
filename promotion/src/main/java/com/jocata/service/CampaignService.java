package com.jocata.service;

import com.jocata.datamodel.promotion.form.CampaignForm;

import java.util.List;

public interface CampaignService {

    CampaignForm createCampaign(CampaignForm form);

    List<CampaignForm> getAllCampaigns();
}
