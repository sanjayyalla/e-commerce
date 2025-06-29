package com.jocata.service;

import com.jocata.datamodel.transaction.form.BillingInfoForm;

import java.util.List;

public interface BillingInfoService {

    List<BillingInfoForm> getBillingInfoByUserId(String userId);
}
