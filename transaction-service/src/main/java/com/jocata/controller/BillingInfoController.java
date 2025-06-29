package com.jocata.controller;

import com.jocata.datamodel.transaction.form.BillingInfoForm;
import com.jocata.service.BillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingInfoController {

    @Autowired
    private BillingInfoService billingInfoService;

    @GetMapping("/user/{userId}")
    public List<BillingInfoForm> getByUserId(@PathVariable String userId) {
        return billingInfoService.getBillingInfoByUserId(userId);
    }
}