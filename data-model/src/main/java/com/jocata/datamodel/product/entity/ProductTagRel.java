package com.jocata.datamodel.product.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_tag_rel")
public class ProductTagRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private ProductTag tag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductTag getTag() {
        return tag;
    }

    public void setTag(ProductTag tag) {
        this.tag = tag;
    }
}
