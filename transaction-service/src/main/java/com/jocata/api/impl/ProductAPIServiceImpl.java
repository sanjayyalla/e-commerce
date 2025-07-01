package com.jocata.api.impl;

import com.jocata.api.ProductAPIService;
import com.jocata.datamodel.product.form.ProductForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductAPIServiceImpl implements ProductAPIService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ProductForm getProductById(String productId) {
        String url = "http://localhost:8080/product-service/api/products" + "/" + productId;
        return restTemplate.getForObject(url, ProductForm.class);
    }
}
