package com.jocata.controller;

import com.jocata.datamodel.inventory.form.InventoryForm;
import com.jocata.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add")
    public InventoryForm addStock(@RequestParam String productId, @RequestParam String quantity) {
        if(!quantity.isEmpty() && !quantity.equals("0")&& !productId.equals("0") && !productId.isEmpty()){
            return inventoryService.addStock(productId, quantity);
        }
        return null;
    }

    @GetMapping("/getStock")
    public Integer getStockByProductId(@RequestParam String productId) {
        if (!productId.equals("0") && !productId.isEmpty()) {
            return inventoryService.getStockByProductId(productId);
        }
        return null;
    }

    @PostMapping("/reduce")
    public InventoryForm reduceStock(@RequestParam String productId, @RequestParam String quantity) {
        if(!quantity.isEmpty() && !quantity.equals("0")&& !productId.equals("0") && !productId.isEmpty()){
            return inventoryService.reduceStock(productId, quantity);
        }
        return null;
    }
}