package com.jocata.service.impl;

import com.jocata.data.transaction.BillingInfoDao;
import com.jocata.data.transaction.InvoiceDao;
import com.jocata.data.transaction.TransactionDao;
import com.jocata.datamodel.order.form.ShippingInfoForm;
import com.jocata.datamodel.transaction.entity.BillingInfo;
import com.jocata.datamodel.transaction.entity.Invoice;
import com.jocata.datamodel.transaction.entity.Transaction;
import com.jocata.datamodel.transaction.form.TransactionForm;
import com.jocata.response.OrderResponseForm;
import com.jocata.service.OrderAPIService;
import com.jocata.service.ShippingInfoAPIService;
import com.jocata.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private OrderAPIService orderAPIService;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private BillingInfoDao billingInfoDao;

    @Autowired
    private ShippingInfoAPIService shippingInfoAPIService;

    @Override
    public TransactionForm processTransaction(TransactionForm form) {

        Long orderId = Long.valueOf(form.getOrderId());

        //this function is to check whether there is previously success transaction for the order id
        List<Transaction> previousTransactions = transactionDao.findByOrderId(orderId);
        for (Transaction txn : previousTransactions) {
            if ("SUCCESS".equalsIgnoreCase(txn.getStatus())) {
                form.setStatus("ALREADY_SUCCESS");
                form.setId(String.valueOf(txn.getId()));
                form.setTransactionDate(txn.getTransactionDate().toString());
                return form;
            }
        }


        BigDecimal txnAmount = new BigDecimal(form.getAmount());

        OrderResponseForm orderResponseForm = orderAPIService.getOrderByOrderId(form.getOrderId());
        BigDecimal orderAmount = new BigDecimal(orderResponseForm.getTotalAmount());
        String status = txnAmount.compareTo(orderAmount) >= 0 ? "SUCCESS" : "PENDING";
        form.setStatus(status);
        Transaction txn = new Transaction();
        txn.setOrderId(Long.valueOf(form.getOrderId()));
        txn.setAmount(txnAmount);
        txn.setStatus(status);
        Transaction updated = transactionDao.save(txn);

        if("SUCCESS".equals(status)){
            String res = orderAPIService.updateOrderStatus(form.getOrderId(),"SUCCESS");
            ShippingInfoForm shippingInfoForm = shippingInfoAPIService.createShippingInfo(orderResponseForm.getId());

            //when the transaction is success then
            Invoice invoice = new Invoice();
            invoice.setTransactionId(Long.valueOf(updated.getId()));
            invoice.setAmount(txnAmount);
            invoice.setUserId(Long.valueOf(orderResponseForm.getUserId()));
            invoice.setOrderId(Long.valueOf(orderResponseForm.getId()));
            invoiceDao.save(invoice);

            BillingInfo billingInfo = new BillingInfo();
            billingInfo.setUserId(Long.valueOf(orderResponseForm.getUserId()));
            billingInfo.setOrderId(Long.valueOf(orderResponseForm.getId()));
            billingInfo.setCardNumber(form.getCardNumber());
            billingInfo.setExpirationDate(form.getExpiryDate());
            billingInfo.setBillingAddress(orderResponseForm.getShippingAddress());
            billingInfoDao.save(billingInfo);

        }

        form.setId(String.valueOf(updated.getId()));
        form.setTransactionDate(updated.getTransactionDate().toString());
        return form;

    }
    @Override
    public List<TransactionForm> getTransactionsByOrderId(String orderId) {
        List<Transaction> transactions = transactionDao.findByOrderId(Long.valueOf(orderId));
        List<TransactionForm> forms = new ArrayList<>();

        for (Transaction txn : transactions) {
            TransactionForm form = new TransactionForm();
            form.setId(String.valueOf(txn.getId()));
            form.setOrderId(String.valueOf(txn.getOrderId()));
            form.setAmount(txn.getAmount().toString());
            form.setStatus(txn.getStatus());
            form.setTransactionDate(txn.getTransactionDate().toString());
            forms.add(form);
        }

        return forms;
    }
}
