package com.jocata.data.order;

import com.jocata.datamodel.order.entity.ShippingInfo;

public interface ShippingInfoDao {

    ShippingInfo save(ShippingInfo info);

    ShippingInfo findByOrderId(Integer orderId);
}
