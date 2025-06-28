package com.jocata.data.order;

import com.jocata.datamodel.order.entity.Order;

public interface OrderDao {

    Order save(Order order);

    Order findById(Integer id);
}
