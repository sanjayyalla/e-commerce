package com.jocata.service;

import com.jocata.response.OrderResponseForm;

public interface OrderAPIService {
    String updateOrderStatus(String orderId, String status);

    OrderResponseForm getOrderByOrderId(String orderId);
}
