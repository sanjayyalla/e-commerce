package com.jocata.data.product;

import com.jocata.datamodel.product.entity.ProductTag;

import java.util.List;

public interface ProductTagDao {

    ProductTag save(ProductTag tag);

    ProductTag findById(Integer id);

    List<ProductTag> findAll();

    String deleteById(Integer id);
}
