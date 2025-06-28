package com.jocata.data.inventory;

import com.jocata.datamodel.inventory.entity.Inventory;

public interface InventoryDao {
    Inventory save(Inventory inventory);

    Inventory findByProductId(Integer productId);
}
