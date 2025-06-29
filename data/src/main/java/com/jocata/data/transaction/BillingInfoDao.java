package com.jocata.data.transaction;

import com.jocata.datamodel.transaction.entity.BillingInfo;

public interface BillingInfoDao {

    BillingInfo save(BillingInfo info);
}
