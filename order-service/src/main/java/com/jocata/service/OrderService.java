package com.jocata.service;

import com.jocata.datamodel.order.form.OrderRequestForm;
import com.jocata.datamodel.order.form.response.OrderForm;
import com.jocata.response.OrderResponseForm;

public interface OrderService {
    OrderForm placeOrder(OrderRequestForm form);

    String updateOrderStatus(String orderId, String status);

    OrderResponseForm getOrderByOrderId(String orderId);
}
