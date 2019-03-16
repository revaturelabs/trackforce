package com.revature.application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.SessionFactory;

import com.revature.dao.BatchDao;
import com.revature.dao.UserDao;
import com.revature.daoimpl.BatchDaoImpl;
import com.revature.daoimpl.UserDaoImpl;
import com.revature.entity.TfBatch;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.services.UserService;
import com.revature.utils.DailyDatabaseSync;
import com.revature.utils.Dev3ApiUtil;
import com.revature.utils.HibernateUtil;
import com.revature.utils.ThreadUtil;
import static com.revature.utils.LogUtil.logger;

public class HttpPlayground {
	public static void main(String[] args) {
//		SessionFactory sf = HibernateUtil.getSessionFactory();
//		populateDatabase();
		System.out.println("select count(a.tf_associate_id) " + 
					"from admin.tf_associate a where a.tf_marketing_status_id > 5 AND a.tf_batch_id IN " + 
					"(" + 
					"    select b.tf_batch_id " + 
					"    from admin.tf_batch b " + 
					"    where b.tf_curriculum_id IN " + 
					"    (" + 
					"        select c.tf_curriculum_id " + 
					"        from admin.tf_curriculum c " + 
					"        where c.tf_curriculum_name = :curriculumName " +
					"    )" + 
					"    AND b.tf_batch_start_date >= TO_TIMESTAMP(:startDate, 'YYYY-MM-DD HH24:MI:SS.FF')" +
					"    AND b.tf_batch_end_date <= TO_TIMESTAMP(:endDate, 'YYYY-MM-DD HH24:MI:SS.FF')" +
					")");
		
	}

	private static void populateDatabase() {
		
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
		if (Dev3ApiUtil.isLoggedIn()) {
			List<TfBatch> batches = Dev3ApiUtil.getBatchesEndingWithinLastNMonths(1);
			System.out.println(batches.size());
			for (TfBatch tfBatch : batches) {
				if (bdao.getBatchBySalesforceId(tfBatch.getSalesforceId()) == null) {
					Dev3ApiUtil.loadBatchAndAssociatesIntoDB(tfBatch.getSalesforceId());
				}
			}
			System.out.println("Reached End");
		}
		
	}
}
