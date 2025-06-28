package com.jocata.service.impl;

import com.jocata.datamodel.inventory.form.InventoryForm;
import com.jocata.service.InventoryAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryAPIServiceImpl implements InventoryAPIService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public InventoryForm addStock(String productId, String quantity) {
        String url = "http://localhost:8080/inventory-service/api/inventory/add?productId="+productId+"&quantity="+quantity;
        return restTemplate.postForObject(url, null, InventoryForm.class);
    }

    @Override
    public InventoryForm reduceStock(String productId, String quantity) {
        String url = "http://localhost:8080/inventory-service/api/inventory/reduce?productId="+productId+"&quantity="+quantity;
        return restTemplate.postForObject(url, null, InventoryForm.class);
    }

    @Override
    public Long getStockByProductId(String productId) {
        String url = "http://localhost:8080/inventory-service/api/inventory" + "/getStock?productId=" + productId;
        Integer stock = restTemplate.getForObject(url,  Integer.class);
        return stock != null ? stock.longValue() : 0L;
    }
}
