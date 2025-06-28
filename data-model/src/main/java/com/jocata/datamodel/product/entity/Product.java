package com.jocata.datamodel.product.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ProductDetail productDetail;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategoryRel> categoryRelations;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductTagRel> tagRelations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public List<ProductCategoryRel> getCategoryRelations() {
        return categoryRelations;
    }

    public void setCategoryRelations(List<ProductCategoryRel> categoryRelations) {
        this.categoryRelations = categoryRelations;
    }

    public List<ProductTagRel> getTagRelations() {
        return tagRelations;
    }

    public void setTagRelations(List<ProductTagRel> tagRelations) {
        this.tagRelations = tagRelations;
    }
}
