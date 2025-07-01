package com.jocata.api;

import com.jocata.datamodel.inventory.form.InventoryForm;

public interface InventoryAPIService {

    InventoryForm addStock(String productId, String quantity);

    InventoryForm reduceStock(String productId, String quantity);

    Long getStockByProductId(String productId);
}
