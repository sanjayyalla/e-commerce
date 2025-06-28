package com.jocata.service;

import com.jocata.datamodel.inventory.form.InventoryForm;
import org.springframework.web.bind.annotation.RequestParam;

public interface InventoryAPIService {

    InventoryForm addStock(String productId, String quantity);

    InventoryForm reduceStock(String productId, String quantity);

    Long getStockByProductId(String productId);
}
