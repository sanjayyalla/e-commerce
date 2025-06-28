package com.jocata.service.impl;

import com.jocata.data.product.ProductCategoryDao;
import com.jocata.data.product.ProductDao;
import com.jocata.data.product.ProductTagDao;
import com.jocata.datamodel.product.entity.*;
import com.jocata.datamodel.product.form.ProductForm;
import com.jocata.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductCategoryDao categoryDao;

    @Autowired
    private ProductTagDao tagDao;

    @Override
    public ProductForm createProduct(ProductForm form) {
        Product product = new Product();
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setPrice(new BigDecimal(form.getPrice()));

        Product savedProduct = productDao.save(product);

        if (form.getCategoryIds() != null) {
            for (String categoryId : form.getCategoryIds()) {
                ProductCategory category = categoryDao.findById(Integer.parseInt(categoryId));
                if (category != null) {
                    ProductCategoryRel rel = new ProductCategoryRel();
                    rel.setProduct(savedProduct);
                    rel.setCategory(category);
                    productDao.saveCategoryRel(rel);
                }
            }
        }

        if (form.getTagIds() != null) {
            for (String tagId : form.getTagIds()) {
                ProductTag tag = tagDao.findById(Integer.parseInt(tagId));
                if (tag != null) {
                    ProductTagRel rel = new ProductTagRel();
                    rel.setProduct(savedProduct);
                    rel.setTag(tag);
                    productDao.saveTagRel(rel);
                }
            }
        }

        return toForm(savedProduct);
    }

    @Override
    public ProductForm getProductById(String id) {
        Product product = productDao.findById(Integer.parseInt(id));
        return product != null ? toForm(product) : null;
    }

    @Override
    public List<ProductForm> getAllProducts() {
        return productDao.findAll().stream().map(this::toForm).collect(Collectors.toList());
    }

    @Override
    public String deleteProductById(String id) {
        return productDao.deleteById(Integer.parseInt(id));
    }

    private ProductForm toForm(Product product) {
        ProductForm form = new ProductForm();
        form.setId(String.valueOf(product.getId()));
        form.setName(product.getName());
        form.setDescription(product.getDescription());
        form.setPrice(product.getPrice() != null ? product.getPrice().toString() : null);


        List<String> categoryIds = new ArrayList<>();
        List<String> categoryNames = new ArrayList<>();
        List<ProductCategoryRel> categoryRels = productDao.findCategoryRelations(product.getId());
        for (ProductCategoryRel rel : categoryRels) {
            categoryIds.add(String.valueOf(rel.getCategory().getId()));
            categoryNames.add(rel.getCategory().getCategoryName());
        }
        form.setCategoryIds(categoryIds);
        form.setCategoryNames(categoryNames);

        List<String> tagIds = new ArrayList<>();
        List<String> tagNames = new ArrayList<>();
        List<ProductTagRel> tagRels = productDao.findTagRelations(product.getId());
        for (ProductTagRel rel : tagRels) {
            tagIds.add(String.valueOf(rel.getTag().getId()));
            tagNames.add(rel.getTag().getTagName());
        }
        form.setTagIds(tagIds);
        form.setTagNames(tagNames);

        return form;
    }
}