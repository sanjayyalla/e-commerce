package com.jocata.controller;

import com.jocata.datamodel.transaction.form.InvoiceForm;
import com.jocata.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/order/{orderId}")
    public InvoiceForm getInvoiceByOrderId(@PathVariable String orderId) {
        return invoiceService.getInvoiceByOrderId(orderId);
    }

    @GetMapping("/generate/{orderId}")
    public ResponseEntity<byte[]> generateInvoice(@PathVariable String orderId) {
        byte[] pdf = invoiceService.generateInvoicePdf(orderId);
        if (pdf == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "invoice_" + orderId + ".pdf");
        return ResponseEntity.ok().headers(headers).body(pdf);
    }

}