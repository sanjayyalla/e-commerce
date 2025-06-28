package com.jocata.controller;

import com.jocata.datamodel.product.form.ProductForm;
import com.jocata.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ProductForm createProduct(@RequestBody ProductForm form) {
        return productService.createProduct(form);
    }

    @GetMapping("/{id}")
    public ProductForm getProduct(@PathVariable("id") String id) {
        return productService.getProductById(id);
    }

    @GetMapping("/all")
    public List<ProductForm> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        return productService.deleteProductById(id);
    }
}