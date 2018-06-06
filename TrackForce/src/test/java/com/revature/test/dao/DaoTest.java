package com.revature.test.dao;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;
import java.sql.SQLException;
import org.testng.annotations.Test;

import com.revature.services.JWTService;
import com.revature.utils.HibernateUtil;

/**
 * TODO
 *
 * CAUSES: `java.lang.NoClassDefFoundError: java/sql/SQLException` in maven
 */
public class DaoTest {

	// @Test
	// public void getSessionFactoryTest() {
	// logger.info("getSessionFactoryTest()...");
	// try {
	// HibernateUtil.getSessionFactory();
	// } catch (Exception e) {
	// logger.info("Exception Caught!" + e);
	//
	// e.printStackTrace();
	// }
	// logger.info("getSessionFactoryTest() end.");
	// }
	//
	// @Test(enabled = false)
	// public void daoTest() {
	// try {
	// HibernateUtil.getSession();
	// } catch (Exception e) {
	// logger.info("Exception Caught!" + e);
	// }
	// }
	//
	//
	// @Test(enabled = false)
	// public void JWTServiceTest() {
	// try {
	// new JWTService();
	// } catch (Exception e) {
	// logger.info("Exception Caught!");
	// }
	// }
}
