package com.jocata.data.product;

import com.jocata.datamodel.product.entity.Product;
import com.jocata.datamodel.product.entity.ProductCategoryRel;
import com.jocata.datamodel.product.entity.ProductTagRel;

import java.util.List;

public interface ProductDao {

    Product save(Product product);

    Product findById(Integer id);

    List<Product> findAll();

    String deleteById(Integer id);

    void saveCategoryRel(ProductCategoryRel rel);

    void saveTagRel(ProductTagRel rel);

    List<ProductCategoryRel> findCategoryRelations(Integer productId);

    List<ProductTagRel> findTagRelations(Integer productId);
}
