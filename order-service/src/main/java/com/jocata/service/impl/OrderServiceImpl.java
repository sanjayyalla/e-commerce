package com.jocata.service.impl;

import com.jocata.data.order.OrderDao;
import com.jocata.datamodel.inventory.form.InventoryForm;
import com.jocata.datamodel.order.entity.Order;
import com.jocata.datamodel.order.entity.OrderItem;
import com.jocata.datamodel.order.form.OrderRequestForm;
import com.jocata.datamodel.order.form.ProductQuantity;
import com.jocata.datamodel.order.form.response.OrderForm;
import com.jocata.datamodel.order.form.response.OrderItemForm;
import com.jocata.datamodel.product.form.ProductForm;
import com.jocata.response.OrderResponseForm;
import com.jocata.service.InventoryAPIService;
import com.jocata.service.OrderService;
import com.jocata.service.ProductAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductAPIService productAPIService;

    @Autowired
    private InventoryAPIService inventoryService;

    @Override
    public OrderForm placeOrder(OrderRequestForm form) {
        Order order = new Order();
        order.setUserId(Integer.parseInt(form.getUserId()));
        order.setShippingAddress(form.getShippingAddress());

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> validItems = new ArrayList<>();

        for (ProductQuantity pq : form.getItems()) {
            Long stock = inventoryService.getStockByProductId(pq.getProductId());

            if (stock >= Long.parseLong(pq.getQuantity())) {
                ProductForm product = productAPIService.getProductById(pq.getProductId());

                BigDecimal itemPrice = new BigDecimal(product.getPrice());
                BigDecimal subtotal = itemPrice.multiply(new BigDecimal(pq.getQuantity()));
                totalAmount = totalAmount.add(subtotal);

                InventoryForm inventoryForm = inventoryService.reduceStock(pq.getProductId(), pq.getQuantity());

                OrderItem item = new OrderItem();
                item.setProductId(Integer.parseInt(pq.getProductId()));
                item.setQuantity(Integer.parseInt(pq.getQuantity()));
                item.setPrice(itemPrice);
                item.setOrder(order);
                validItems.add(item);
            }
        }

        order.setItems(validItems);
        order.setTotalAmount(totalAmount);
        order.setStatus("PENDING");

        Order updated = orderDao.save(order);

        return convertToForm(updated);
    }

    @Override
    public OrderResponseForm getOrderByOrderId(String orderId) {
        Order order = orderDao.findById(Integer.parseInt(orderId));
        return convertOrderToOrderResponseForm(order);
    }

    private OrderResponseForm convertOrderToOrderResponseForm(Order order) {
        OrderResponseForm response = new OrderResponseForm();
        response.setId(String.valueOf(order.getId()));
        response.setUserId(String.valueOf(order.getUserId()));
        response.setTotalAmount(order.getTotalAmount().toPlainString());
        response.setStatus(order.getStatus());
        response.setShippingAddress(order.getShippingAddress());
        response.setOrderDate(order.getOrderDate().toString());
        response.setCreatedAt(order.getCreatedAt().toString());
        response.setUpdatedAt(order.getUpdatedAt().toString());

        List<com.jocata.response.OrderItemForm> itemForms = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            com.jocata.response.OrderItemForm itemForm = new com.jocata.response.OrderItemForm();
            itemForm.setProductId(String.valueOf(item.getProductId()));
            itemForm.setQuantity(String.valueOf(item.getQuantity()));
            itemForm.setPrice(item.getPrice().toPlainString());
            itemForms.add(itemForm);
        }

        response.setItems(itemForms);
        return response;
    }

    @Override
    public String updateOrderStatus(String orderId, String status) {
        Order order = orderDao.findById(Integer.parseInt(orderId));
        order.setStatus(status);
        orderDao.save(order);
        return "Updated";
    }


    private OrderForm convertToForm(Order order) {
        OrderForm form = new OrderForm();
        form.setOrderId(String.valueOf(order.getId()));
        form.setUserId(String.valueOf(order.getUserId()));
        form.setShippingAddress(order.getShippingAddress());
        form.setStatus(order.getStatus());
        form.setTotalAmount(order.getTotalAmount());

        List<OrderItemForm> itemForms = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            OrderItemForm itemForm = new OrderItemForm();
            itemForm.setProductId(String.valueOf(item.getProductId()));
            itemForm.setQuantity(String.valueOf(item.getQuantity()));
            itemForm.setPrice(item.getPrice());
            itemForms.add(itemForm);
        }

        form.setItems(itemForms);
        return form;
    }
}
