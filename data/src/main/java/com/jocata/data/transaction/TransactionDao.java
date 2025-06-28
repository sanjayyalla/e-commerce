package com.jocata.data.transaction;

import com.jocata.datamodel.transaction.entity.Transaction;

import java.util.List;

public interface TransactionDao {
    Transaction save(Transaction transaction);

    List<Transaction> findByOrderId(Long orderId);
}
