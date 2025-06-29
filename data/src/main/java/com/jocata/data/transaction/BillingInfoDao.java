package com.jocata.data.transaction;

import com.jocata.datamodel.transaction.entity.BillingInfo;

import java.util.List;

public interface BillingInfoDao {

    BillingInfo save(BillingInfo info);

    List<BillingInfo> findByUserId(Long userId);
}
