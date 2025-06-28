package com.jocata.controller;

import com.jocata.datamodel.transaction.form.TransactionForm;
import com.jocata.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    public TransactionForm createTransaction(@RequestBody TransactionForm form) {
        return transactionService.processTransaction(form);
    }

    @GetMapping("/order/{orderId}")
    public List<TransactionForm> getByOrderId(@PathVariable String orderId) {
        return transactionService.getTransactionsByOrderId(orderId);
    }
}
