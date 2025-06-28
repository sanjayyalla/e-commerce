package com.jocata.controller;

import com.jocata.datamodel.order.form.OrderRequestForm;
import com.jocata.datamodel.order.form.response.OrderForm;
import com.jocata.response.OrderResponseForm;
import com.jocata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public OrderForm placeOrder(@RequestBody OrderRequestForm form) {
        return orderService.placeOrder(form);
    }

    @GetMapping
    public OrderResponseForm getOrderByOrderId(@RequestParam String orderId){
        if(!orderId.isEmpty() && !orderId.equals("0")){
            return orderService.getOrderByOrderId(orderId);
        }
        return null;
    }

    @GetMapping("/updateStatus")
    public String updateOrderStatus(@RequestParam String orderId, @RequestParam String status) {
       if(!orderId.isEmpty() && !orderId.equals("0")){
           return orderService.updateOrderStatus(orderId, status);
       }
       return "Provide the order ID";
    }




}
