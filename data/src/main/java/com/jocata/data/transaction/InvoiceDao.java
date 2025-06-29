package com.jocata.data.transaction;

import com.jocata.datamodel.transaction.entity.Invoice;

public interface InvoiceDao {

    Invoice save(Invoice invoice);

}
