package com.revature.dao;

import java.math.BigDecimal;

import com.revature.entity.TfAssociate;

public interface AssociateDao {
	  public TfAssociate getAssociate(BigDecimal associateId);
}
