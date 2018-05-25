package com.revature.test.dao;

import static com.revature.utils.LogUtil.logger;
import org.testng.annotations.Test;

import com.revature.utils.HibernateUtil;

public class DaoTest {
	

	@Test
	public void daoTest() {
		logger.info("Testing database connection...");
		try {
			HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			logger.info("Exception Caught!");
		}
		logger.info("daoTest end.");
	}
}
