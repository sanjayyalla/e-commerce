package com.jocata.controller;

import com.jocata.datamodel.order.form.OrderRequestForm;
import com.jocata.datamodel.order.form.response.OrderForm;
import com.jocata.datamodel.product.form.ProductForm;
import com.jocata.datamodel.user.form.UserForm;
import com.jocata.response.OrderResponseForm;
import com.jocata.service.OrderService;
import com.jocata.service.UserAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserAPIService userAPIService;

    @PostMapping("/place")
    public OrderForm placeOrder(@RequestParam String username, @RequestParam String password, @RequestBody OrderRequestForm form) {

        UserForm userForm = userAPIService.login(username, password);
        if (userForm.getMessage().equalsIgnoreCase("LOGIN SUCCESSFUL")) {
            return orderService.placeOrder(userForm.getId(), form);
        } else if (userForm.getMessage().equalsIgnoreCase("No user found with the given credentials")) {
            OrderForm notFoundForm = new OrderForm();
            notFoundForm.setMessage("No user found with the given credentials");
            return notFoundForm;
        } else {
            OrderForm emptyDetails = new OrderForm();
            emptyDetails.setMessage("You have not entered the username or password correctly");
            return emptyDetails;
        }
    }

    @GetMapping
    public OrderResponseForm getOrderByOrderId(@RequestParam String orderId) {
        if (!orderId.isEmpty() && !orderId.equals("0")) {
            return orderService.getOrderByOrderId(orderId);
        }
        return null;
    }

    @GetMapping("/updateStatus")
    public String updateOrderStatus(@RequestParam String orderId, @RequestParam String status) {
        if (!orderId.isEmpty() && !orderId.equals("0")) {
            return orderService.updateOrderStatus(orderId, status);
        }
        return "Provide the order ID";
    }


}
