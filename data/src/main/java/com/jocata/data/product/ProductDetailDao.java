package com.jocata.data.product;

import com.jocata.datamodel.product.entity.ProductDetail;

public interface ProductDetailDao {

    ProductDetail save(ProductDetail detail);

    ProductDetail findByProductId(Integer productId);
}
