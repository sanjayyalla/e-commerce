package com.jocata.data.product;

import com.jocata.datamodel.product.entity.ProductCategoryRel;

import java.util.List;

public interface ProductCategoryRelDao {

    ProductCategoryRel save(ProductCategoryRel rel);

    List<ProductCategoryRel> findByProductId(Integer productId);

    void deleteByProductId(Integer productId);
}
