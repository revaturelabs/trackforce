package com.revature.dao;

import static org.testng.Assert.assertNotNull;

import java.math.BigDecimal;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;

public class AssociateDaoImplTest {

	AssociateDaoHibernate aDao = new AssociateDaoHibernate();

	@DataProvider(name="associateId")
	public BigDecimal[] associateIds() {
		BigDecimal[] ids = new BigDecimal[] {new BigDecimal(212), new BigDecimal(213), new BigDecimal(215)};
		return ids;
	}
	
	@Test(dataProvider="associateId")
	public void getAssociate(BigDecimal id) {
		TfAssociate result = aDao.getAssociate(id);
		assertNotNull(result);
	}
}
