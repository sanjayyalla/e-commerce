package com.jocata.service.impl;

import com.jocata.data.inventory.InventoryDao;
import com.jocata.datamodel.inventory.entity.Inventory;
import com.jocata.datamodel.inventory.form.InventoryForm;
import com.jocata.service.InventoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryDao inventoryDao;

    @Override
    public InventoryForm addStock(String productId, String quantity) {
        Integer pid = Integer.parseInt(productId);
        Integer qty = Integer.parseInt(quantity);

        Inventory inventory = inventoryDao.findByProductId(pid);
        if (inventory == null) {
            inventory = new Inventory();
            inventory.setProductId(pid);
            inventory.setQuantity(qty);
        } else {
            inventory.setQuantity(inventory.getQuantity() + qty);
        }

        Inventory saved = inventoryDao.save(inventory);
        return toForm(saved);
    }

    @Override
    public InventoryForm reduceStock(String productId, String quantity) {

        Integer qty = Integer.parseInt(quantity);

        Inventory inventory = inventoryDao.findByProductId(Integer.parseInt(productId));
        if (inventory == null || inventory.getQuantity() < qty) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        inventory.setQuantity(inventory.getQuantity() - qty);
        Inventory saved = inventoryDao.save(inventory);
        return toForm(saved);
    }

    @Override
    public Integer getStockByProductId(String productId) {
        Inventory inventory = inventoryDao.findByProductId(Integer.parseInt(productId));
        return inventory.getQuantity();
    }

    private InventoryForm toForm(Inventory inventory) {
        InventoryForm form = new InventoryForm();
        form.setProductId(String.valueOf(inventory.getProductId()));
        form.setQuantity(String.valueOf(inventory.getQuantity()));
        return form;
    }
}