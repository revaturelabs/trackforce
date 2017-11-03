package com.revature.dao;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.testng.annotations.Test;
import com.revature.entity.TfBatch;

public class testBatchDaoHibernate {
	@Test(priority = 0)
	public void getNoOfAssociatesTest() {
		AssociateDao associatedao = new AssociateDaoHibernate();
		BigDecimal count_associate = associatedao.getNoOfAssociates(BigDecimal.valueOf(50));
		Assert.assertNotNull(count_associate);
		System.out.println(count_associate);
	}

	@Test
	public void testgetBatchDetails() {
		BatchDao batchdao = new BatchDaoHibernate();
		List<TfBatch> batchlist = batchdao.getBatchDetails("Nov 03 2017", "Jan 04 2017");
		Assert.assertNotNull(batchlist);
		System.out.println(batchlist);
		Assert.assertNull(batchlist);
	}
}