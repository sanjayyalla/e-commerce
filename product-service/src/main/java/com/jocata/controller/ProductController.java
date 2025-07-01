package com.jocata.controller;

import com.jocata.api.UserAPIService;
import com.jocata.datamodel.product.form.ProductForm;
import com.jocata.datamodel.user.form.UserForm;
import com.jocata.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    @Autowired
    private UserAPIService userAPIService;

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ProductForm createProduct(@RequestParam String username, @RequestParam String password, @RequestBody ProductForm form) {

        UserForm userForm = userAPIService.login(username, password);
        if (userForm.getMessage().equalsIgnoreCase("LOGIN SUCCESSFUL")) {
            if (userForm.getRoleForm().getRoleName().equalsIgnoreCase("ADMIN")) {
                return productService.createProduct(form);
            } else {
                ProductForm notAdminForm = new ProductForm();
                notAdminForm.setMessage("You are not admin to create product");
                return notAdminForm;
            }
        } else if (userForm.getMessage().equalsIgnoreCase("No user found with the given credentials")) {

            ProductForm notFoundForm = new ProductForm();
            notFoundForm.setMessage("No user found with the given credentials");
            return notFoundForm;
        } else {
            ProductForm emptyDetails = new ProductForm();
            emptyDetails.setMessage("You have not entered the username or password correctly");
            return emptyDetails;
        }

    }

    @GetMapping("/{id}")
    public ProductForm getProduct(@PathVariable("id") String id) {
        return productService.getProductById(id);
    }

    @GetMapping("/all")
    public List<ProductForm> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete")
    public String deleteProduct(@RequestParam String username, @RequestParam String password, @RequestParam String id) {

        UserForm userForm = userAPIService.login(username, password);
        if (userForm.getMessage().equalsIgnoreCase("LOGIN SUCCESSFUL")) {
            if (userForm.getRoleForm().getRoleName().equalsIgnoreCase("ADMIN")) {
                return productService.deleteProductById(id);
            } else {
                return "You are not admin to delete product";
            }
        } else if (userForm.getMessage().equalsIgnoreCase("No user found with the given credentials")) {
            return "No user found with given credentials";
        } else {
            return "You have not given the user or password clearly";
        }

    }
}