package com.jocata.data.product;

import com.jocata.datamodel.product.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    ProductCategory save(ProductCategory category);

    ProductCategory findById(Integer id);

    List<ProductCategory> findAll();

    String deleteById(Integer id);
}
