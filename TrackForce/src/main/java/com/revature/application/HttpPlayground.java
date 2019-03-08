package com.revature.application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;

import com.revature.dao.BatchDao;
import com.revature.dao.UserDao;
import com.revature.daoimpl.BatchDaoImpl;
import com.revature.daoimpl.UserDaoImpl;
import com.revature.entity.TfBatch;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.services.UserService;
import com.revature.utils.Dev3ApiUtil;
import com.revature.utils.HibernateUtil;
import com.revature.utils.ThreadUtil;
import static com.revature.utils.LogUtil.logger;

public class HttpPlayground {
	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		logger.debug("Started Session Factory");
		BatchDao bdao = new BatchDaoImpl();
		UserDao udao = new UserDaoImpl();
		UserService userv = new UserService();
		TfUser newAdmin = new TfUser();
		if (userv.getUser("NewAdmin") == null) {
			newAdmin.setUsername("NewAdmin");
			newAdmin.setPassword("password");
			newAdmin.setTfRole(new TfRole(1));
			newAdmin.setIsApproved(1);
			userv.insertUser(newAdmin);
		}

		Dev3ApiUtil.login();
		List<TfBatch> batches = Dev3ApiUtil.getBatchesEndingWithinLastNMonths(6);
		System.out.println(batches.size());
//		for (TfBatch tfBatch : batches) {
//			if (bdao.getBatchBySalesforceId(tfBatch.getSalesforceId()) == null) {
//				Dev3ApiUtil.loadBatchAndAssociatesIntoDB(tfBatch.getSalesforceId());
//			}
//		}
		System.out.println("Reached End");
		HibernateUtil.shutdown();
	}
}
