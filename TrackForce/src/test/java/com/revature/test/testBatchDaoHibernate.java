package com.revature.test;

import java.util.List;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.revature.dao.BatchDao;
import com.revature.dao.BatchDaoHibernate;
import com.revature.entity.TfBatch;

public class testBatchDaoHibernate {


	@Test
	public void testgetBatchDetails() {
		BatchDao batchdao = new BatchDaoHibernate();
		List<TfBatch> batchlist = batchdao.getBatchDetails("Nov 03 2017", "Jan 04 2017");
		Assert.assertNotNull(batchlist);
		System.out.println(batchlist);
		Assert.assertNull(batchlist);
	}
}