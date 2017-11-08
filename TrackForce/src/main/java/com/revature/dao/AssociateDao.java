package com.revature.dao;

import java.math.BigDecimal;

import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;

public interface AssociateDao {

    public void updateInfo(BigDecimal id, TfMarketingStatus marketingStatus, TfClient client);
}
