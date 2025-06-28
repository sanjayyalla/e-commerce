package com.jocata.service;

import com.jocata.datamodel.product.form.ProductForm;

import java.util.List;

public interface ProductService {

    ProductForm createProduct(ProductForm form);

    ProductForm getProductById(String id);

    List<ProductForm> getAllProducts();

    String deleteProductById(String id);
}
