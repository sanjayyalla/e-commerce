package com.jocata.controller;

import com.jocata.datamodel.transaction.form.InvoiceForm;
import com.jocata.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/user/{userId}")
    public List<InvoiceForm> getInvoicesByUserId(@PathVariable String userId) {
        return invoiceService.getInvoicesByUserId(userId);
    }
}