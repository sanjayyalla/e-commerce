package com.jocata.service;

import com.jocata.datamodel.transaction.form.InvoiceForm;

import java.util.List;

public interface InvoiceService {

    List<InvoiceForm> getInvoicesByUserId(String userId);

}
