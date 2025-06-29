package com.jocata.service.impl;

import com.jocata.data.product.ProductCategoryDao;
import com.jocata.data.product.ProductDao;
import com.jocata.data.product.ProductDetailDao;
import com.jocata.data.product.ProductTagDao;
import com.jocata.datamodel.product.entity.*;
import com.jocata.datamodel.product.form.ProductDetailForm;
import com.jocata.service.ProductDetailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@Transactional
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductDetailDao detailDao;

    @Override
    public ProductDetailForm addProductDetail(ProductDetailForm form) {
        Product product = productDao.findById(Integer.parseInt(form.getProductId()));
        if (product == null) {
            throw new IllegalArgumentException("Invalid product ID");
        }

        ProductDetail detail = new ProductDetail();
        detail.setProduct(product);
        detail.setWeight(BigDecimal.valueOf(form.getWeight() != null ? Double.parseDouble(form.getWeight()) : null));
        detail.setDimensions(form.getDimensions());
        detail.setColor(form.getColor());

        ProductDetail saved = detailDao.save(detail);
        return toForm(saved);
    }

    @Override
    public ProductDetailForm getProductDetailByProductId(String productId) {
        ProductDetail detail = detailDao.findByProductId(Integer.parseInt(productId));
        return detail != null ? toForm(detail) : null;
    }

    private ProductDetailForm toForm(ProductDetail detail) {
        ProductDetailForm form = new ProductDetailForm();
        form.setId(String.valueOf(detail.getId()));
        form.setProductId(String.valueOf(detail.getProduct().getId()));
        form.setWeight(detail.getWeight() != null ? detail.getWeight().toString() : null);
        form.setColor(detail.getColor());
        form.setDimensions(detail.getDimensions());
        form.setMessage("SUCCESS");
        return form;
    }
}