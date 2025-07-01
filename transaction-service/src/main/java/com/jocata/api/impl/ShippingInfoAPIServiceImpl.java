package com.jocata.api.impl;

import com.jocata.api.ShippingInfoAPIService;
import com.jocata.datamodel.order.form.ShippingInfoForm;
import com.jocata.datamodel.order.form.ShippingInfoUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ShippingInfoAPIServiceImpl implements ShippingInfoAPIService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ShippingInfoForm createShippingInfo(String orderId) {
        String url = "http://localhost:8080/order-service/api/shipping/create?orderId=" + orderId;
        return restTemplate.getForObject(url, ShippingInfoForm.class);
    }

    @Override
    public String updateShippingStatus(ShippingInfoUpdateForm shippingInfoUpdateForm) {
        String url = "http://localhost:8080/order-service/api/shipping/update";
        return restTemplate.postForObject(url, shippingInfoUpdateForm, String.class);
    }
}
