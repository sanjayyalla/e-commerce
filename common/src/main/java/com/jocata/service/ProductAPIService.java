package com.jocata.service;

import com.jocata.datamodel.product.form.ProductForm;

public interface ProductAPIService {
    ProductForm getProductById(String productId);
}
