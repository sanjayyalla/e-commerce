package com.jocata.controller;

import com.jocata.datamodel.product.form.ProductDetailForm;
import com.jocata.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productDetails")
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @PostMapping("/add")
    public ProductDetailForm addProductDetail(@RequestBody ProductDetailForm form) {
        return productDetailService.addProductDetail(form);
    }

    @GetMapping("/{productId}")
    public ProductDetailForm getProductDetail(@PathVariable String productId) {
        return productDetailService.getProductDetailByProductId(productId);
    }
}
