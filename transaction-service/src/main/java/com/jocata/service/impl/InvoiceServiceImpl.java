package com.jocata.service.impl;

import com.jocata.data.transaction.InvoiceDao;
import com.jocata.datamodel.transaction.entity.Invoice;
import com.jocata.datamodel.transaction.form.InvoiceForm;
import com.jocata.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;

    @Override
    public List<InvoiceForm> getInvoicesByUserId(String userId) {
        List<Invoice> invoices = invoiceDao.findByUserId(Long.valueOf(userId));
        List<InvoiceForm> forms = new ArrayList<>();

        for (Invoice invoice : invoices) {
            InvoiceForm form = new InvoiceForm();
            form.setId(String.valueOf(invoice.getId()));
            form.setTransactionId(String.valueOf(invoice.getTransactionId()));
            form.setUserId(String.valueOf(invoice.getUserId()));
            form.setAmount(invoice.getAmount().toString());
            form.setInvoiceDate(invoice.getInvoiceDate().toString());
            forms.add(form);
        }
        return forms;
    }
}
