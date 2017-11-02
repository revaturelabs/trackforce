package com.revature.dao;

import java.math.BigDecimal;


public interface AssociateDao {

	public BigDecimal getNoOfAssociates(BigDecimal batch_id);
	//public List<TfAssociate> getAssociatesByBatch(String batchName);
}
