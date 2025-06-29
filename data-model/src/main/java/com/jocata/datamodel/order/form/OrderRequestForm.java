package com.jocata.datamodel.order.form;

import java.util.List;

public class OrderRequestForm {
    private String userId;
    private String shippingAddress;
    private String couponCode;
    private List<ProductQuantity> items;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<ProductQuantity> getItems() {
        return items;
    }

    public void setItems(List<ProductQuantity> items) {
        this.items = items;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
