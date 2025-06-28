package com.jocata.service;

import com.jocata.datamodel.transaction.form.TransactionForm;

import java.util.List;

public interface TransactionService {

    TransactionForm processTransaction(TransactionForm form);

    List<TransactionForm> getTransactionsByOrderId(String orderId);

}
