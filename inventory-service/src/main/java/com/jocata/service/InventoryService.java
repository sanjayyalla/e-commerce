package com.jocata.service;

import com.jocata.datamodel.inventory.form.InventoryForm;

public interface InventoryService {

    InventoryForm addStock(String productId, String quantity);

    InventoryForm reduceStock(String productId, String quantity);

    Integer getStockByProductId(String productId);
}
