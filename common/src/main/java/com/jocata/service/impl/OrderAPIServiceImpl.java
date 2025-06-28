package com.jocata.service.impl;

import com.jocata.response.OrderResponseForm;
import com.jocata.service.OrderAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderAPIServiceImpl implements OrderAPIService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String updateOrderStatus(String orderId, String status) {
        String url = "http://localhost:8080/order-service/api/orders/updateStatus?orderId="+orderId+"&status="+status;
        String res = restTemplate.getForObject(url, String.class);
        return res;
    }

    @Override
    public OrderResponseForm getOrderByOrderId(String orderId) {
        String url = "http://localhost:8080/order-service/api/orders?orderId="+orderId;
        return restTemplate.getForObject(url, OrderResponseForm.class);
    }
}
