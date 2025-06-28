package com.jocata.data.product;

import com.jocata.datamodel.product.entity.ProductTagRel;

import java.util.List;

public interface ProductTagRelDao {

    ProductTagRel save(ProductTagRel rel);

    List<ProductTagRel> findByProductId(Integer productId);

    void deleteByProductId(Integer productId);
}
