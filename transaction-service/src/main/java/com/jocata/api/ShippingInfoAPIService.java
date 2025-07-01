package com.jocata.api;

import com.jocata.datamodel.order.form.ShippingInfoForm;
import com.jocata.datamodel.order.form.ShippingInfoUpdateForm;


public interface ShippingInfoAPIService {


    ShippingInfoForm createShippingInfo(String orderId);

    String updateShippingStatus(ShippingInfoUpdateForm shippingInfoUpdateForm);

}
