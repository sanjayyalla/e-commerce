package com.jocata.datamodel.transaction.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "transaction_date")
    private Timestamp transactionDate;

    @PrePersist
    protected void onCreate() {
        this.transactionDate = new Timestamp(System.currentTimeMillis());
    }

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}