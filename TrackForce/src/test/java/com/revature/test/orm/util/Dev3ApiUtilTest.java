package com.revature.test.orm.util;

import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfBatch;
import com.revature.utils.Dev3ApiUtil;

public class Dev3ApiUtilTest {

	@Test(priority=1)
	public void testIsNotLoggedIn() {
		Assert.assertFalse(Dev3ApiUtil.isLoggedIn());
	}
	@Test(priority=2)
	public void testLogin() {
		Assert.assertTrue(Dev3ApiUtil.login());
	}
	@Test(priority=3)
	public void testIsLoggedIn() {
		Assert.assertTrue(Dev3ApiUtil.isLoggedIn());
	}
	@Test(priority=4)
	public void testGetMostRecentBatches() {
		List<TfBatch> batches = Dev3ApiUtil.getBatchesEndingWithinLastNMonths(0);
		Assert.assertNotNull(batches);
		for (TfBatch tfBatch : batches) {
			Assert.assertTrue(tfBatch.getEndDate().after(new Date()));
		}
	}
}
