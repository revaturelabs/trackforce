package com.revature.dao;

import java.math.BigDecimal;

<<<<<<< HEAD
import com.revature.entity.TfAssociate;

public interface AssociateDao {
	  public TfAssociate getAssociate(BigDecimal associateId);
=======
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;

public interface AssociateDao {

    public void updateInfo(BigDecimal id, TfMarketingStatus marketingStatus, TfClient client);
    public TfAssociate getAssociate(BigDecimal associateid);
}
