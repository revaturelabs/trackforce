package com.revature.test.dao;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class DaoTest {
	static final Logger logger = Logger.getLogger(DaoTest.class);

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
