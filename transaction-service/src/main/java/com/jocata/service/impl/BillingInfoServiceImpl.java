package com.jocata.service.impl;

import com.jocata.data.transaction.BillingInfoDao;
import com.jocata.datamodel.transaction.entity.BillingInfo;
import com.jocata.datamodel.transaction.form.BillingInfoForm;
import com.jocata.service.BillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillingInfoServiceImpl implements BillingInfoService {

    @Autowired
    private BillingInfoDao billingInfoDao;

    @Override
    public List<BillingInfoForm> getBillingInfoByUserId(String userId) {

        List<BillingInfo> entities = billingInfoDao.findByUserId(Long.parseLong(userId));
        List<BillingInfoForm> forms = new ArrayList<>();

        for (BillingInfo info : entities) {
            BillingInfoForm form = new BillingInfoForm();
            form.setId(String.valueOf(info.getId()));
            form.setUserId(String.valueOf(info.getUserId()));
            form.setOrderId(String.valueOf(info.getOrderId()));
            form.setCardNumber(info.getCardNumber());
            form.setExpirationDate(info.getExpirationDate());
            form.setBillingAddress(info.getBillingAddress());
            form.setCreatedAt(info.getCreatedAt().toString());
            forms.add(form);
        }

        return forms;
    }
}
