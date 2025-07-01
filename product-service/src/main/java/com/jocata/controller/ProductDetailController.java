package com.jocata.controller;

import com.jocata.api.UserAPIService;
import com.jocata.datamodel.product.form.ProductDetailForm;
import com.jocata.datamodel.user.form.UserForm;
import com.jocata.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productDetails")
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private UserAPIService userAPIService;

    @PostMapping("/add")
    public ProductDetailForm addProductDetail(@RequestParam String username,@RequestParam String password,@RequestBody ProductDetailForm form) {

        UserForm userForm = userAPIService.login(username, password);
        if (userForm.getMessage().equalsIgnoreCase("LOGIN SUCCESSFUL")) {
            if (userForm.getRoleForm().getRoleName().equalsIgnoreCase("ADMIN")) {
                return productDetailService.addProductDetail(form);
            } else {
                ProductDetailForm notAdminForm = new ProductDetailForm();
                notAdminForm.setMessage("You are not admin to create product");
                return notAdminForm;
            }
        } else if (userForm.getMessage().equalsIgnoreCase("No user found with the given credentials")) {

            ProductDetailForm notFoundForm = new ProductDetailForm();
            notFoundForm.setMessage("No user found with the given credentials");
            return notFoundForm;
        } else {
            ProductDetailForm emptyDetails = new ProductDetailForm();
            emptyDetails.setMessage("You have not entered the username or password correctly");
            return emptyDetails;
        }


    }

    @GetMapping("/{productId}")
    public ProductDetailForm getProductDetail(@PathVariable String productId) {
        return productDetailService.getProductDetailByProductId(productId);
    }
}
