package com.jocata.data.transaction;

import com.jocata.datamodel.transaction.entity.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice save(Invoice invoice);

    List<Invoice> findByUserId(Long userId);
}
