package com.jocata.service;

import com.jocata.datamodel.product.form.ProductDetailForm;

public interface ProductDetailService {

    ProductDetailForm addProductDetail(ProductDetailForm form);

    ProductDetailForm getProductDetailByProductId(String productId);
}
