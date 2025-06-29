package com.jocata.service;

import com.jocata.datamodel.order.form.ShippingInfoForm;

public interface ShippingInfoService {

    ShippingInfoForm createShippingInfo(String orderId);

    String updateShippingStatus(String orderId, String status);
}
