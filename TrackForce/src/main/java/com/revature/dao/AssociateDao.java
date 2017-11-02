package com.revature.dao;

import java.math.BigDecimal;
import java.util.List;

import com.revature.entity.TfAssociate;

public interface AssociateDao {

	public BigDecimal getNoOfAssociates(BigDecimal batch_id);
	public List<TfAssociate> getAssociatesByBatch(String batchName);
}
