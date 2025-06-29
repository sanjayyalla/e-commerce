package com.jocata.service.impl;

import com.jocata.data.order.ShippingInfoDao;
import com.jocata.datamodel.order.entity.ShippingInfo;
import com.jocata.datamodel.order.form.ShippingInfoForm;
import com.jocata.service.ShippingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ShippingInfoServiceImpl implements ShippingInfoService {

    @Autowired
    private ShippingInfoDao shippingInfoDao;

    @Override
    public ShippingInfoForm createShippingInfo(String orderId) {
        ShippingInfo info = new ShippingInfo();
        info.setOrderId(Integer.parseInt(orderId));
        info.setShippingStatus("DISPATCHED");
        info.setShippingDate(new Timestamp(System.currentTimeMillis()));
//        info.setDeliveryDate(new Timestamp(System.currentTimeMillis()+ 3*24 * 60 * 60 * 1000L));
        ShippingInfo updated = shippingInfoDao.save(info);

        return convertToForm(updated);
    }

    @Override
    public String updateShippingStatus(String orderId, String status) {
        ShippingInfo info = shippingInfoDao.findByOrderId(Integer.parseInt(orderId));
        if (info == null) return "Shipping info not found";

        info.setShippingStatus(status);
        if ("DELIVERED".equalsIgnoreCase(status)) {
            info.setDeliveryDate(new Timestamp(System.currentTimeMillis()));
        }
        shippingInfoDao.save(info);
        return "Updated";
    }

    private ShippingInfoForm convertToForm(ShippingInfo info) {
        ShippingInfoForm form = new ShippingInfoForm();
        form.setId(String.valueOf(info.getId()));
        form.setOrderId(String.valueOf(info.getOrderId()));
        form.setShippingStatus(info.getShippingStatus());
        form.setShippingDate(info.getShippingDate() != null ? info.getShippingDate().toString() : null);
        form.setDeliveryDate(info.getDeliveryDate() != null ? info.getDeliveryDate().toString() : null);
        return form;
    }
}
