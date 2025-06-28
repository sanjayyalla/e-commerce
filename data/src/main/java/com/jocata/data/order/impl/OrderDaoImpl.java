package com.jocata.data.order.impl;

import com.jocata.data.order.OrderDao;
import com.jocata.datamodel.order.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order save(Order order) {
        return entityManager.merge(order);
    }

    @Override
    public Order findById(Integer id) {
        return entityManager.find(Order.class, id);
    }
}
