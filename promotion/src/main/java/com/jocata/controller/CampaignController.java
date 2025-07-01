package com.jocata.controller;
import com.jocata.datamodel.promotion.form.CampaignForm;
import com.jocata.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @PostMapping("/create")
    public CampaignForm createCampaign(@RequestBody CampaignForm form) {
        if (form.getName() == null || form.getDiscountPercentage() == null
                || form.getStartDate() == null || form.getEndDate() == null) {
            CampaignForm response = new CampaignForm();
            response.setMessage("All fields (name, discountPercentage, startDate, endDate) must be provided.");
            return response;
        }
        return campaignService.createCampaign(form);
    }

    @GetMapping("/all")
    public List<CampaignForm> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }
}
