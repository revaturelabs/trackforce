package com.revature.test.context;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.StoredProcedureQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfBatchLocation;
import com.revature.entity.TfClient;
import com.revature.entity.TfCurriculum;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterviewType;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class TestDBLoader {

	public static void load(String user) throws HibernateException, IOException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
//		session.createNativeQuery("ALTER SCHEMA PUBLIC RENAME TO ADMIN").executeUpdate();
//		session.createNativeQuery("SET DATABASE SQL SYNTAX ORA TRUE").executeUpdate();
		for(Object o : session.createNativeQuery("select * from INFORMATION_SCHEMA.SYSTEM_TABLES WHERE TABLE_SCHEM = 'ADMIN'").list()) {
			Object[] arr = (Object[]) o;
			System.out.print("[ ");
			for(int i = 0; i < arr.length; i++) {
				System.out.print(arr[i]);
				if(i + 1 < arr.length) {
					System.out.print(", ");
				}
			}
			System.out.println("]");
		}
		
		
		try {
			populateEndClient(1, "Accenture", session);
			populateEndClient(2, "Infosys", session);
			populateClient(1, "Accenture", session);
			populateClient(2, "Infosys", session);
			populateInterviewType(1, "Phone", session);
			populateInterviewType(2, "Online", session);
			populateCurriculum(1, "JTA", session);
			populateCurriculum(2, "Java", session);
			populateCurriculum(3, ".Net", session);
			populateCurriculum(4, "PEGA", session);
			populateMarketingStatus(1, "MAPPED,  TRAINING", session);
			populateMarketingStatus(2, "MAPPED,  RESERVED", session);
			populateBatchLocation(1, "Revature LLC, 11730 Plaza America Drive, 2nd Floor | Reston, VA 20190", session);
			populateBatchLocation(2, "UMUC", session);
			populateBatchLocation(3, "USF", session);
			populateBatch(1, "1712 Dec04 AP, USF", LocalDate.of(2017, 12, 4), LocalDate.of(2018, 2, 16), 2, 3, session);
			populateBatch(2, "1710 Oct09 PEGA", LocalDate.of(2017, 10, 9), LocalDate.of(2017, 12, 15), 4, 1, session);
			populateAssociate(1, "Frank", "Hind", 1, 2, 1, 0, session);
			populateAssociate(2, "Thomas", "Page", 1, 2, 1, 0, session);
			populateAssociate(3, "Lucas", "Normand", 1, 2, 1, 0, session);
			populateAssociate(4, "Jhonnie", "Cole", 1, 2, 1, 0, session);
			populateAssociate(5, "Ramona", "Reyes", 1, 2, 1, 0, session);
			populateRole(1, "Admin", session);
			populateRole(2, "Manager", session);
			populateRole(3, "Vice President", session);
			populateRole(4, "Associate", session);
			populateUser(1, "sha1:64000:18:zBfcx3rxxYev6SuYjw/EoTzwwhDW0+5I:TE/5QDShUo2DpVtwM1wfpnmD", "TestAdmin", 1, session);
			

			session.flush();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new IOException("Could not populate DB", e);
		} finally {
			session.close();
		}
	}

	private static void populateUser(int i, String string, String string2, Integer j, Session session) {
		TfUser tfu = new TfUser();
		tfu.setTfUserId(i);
		tfu.setTfUserHashpassword(string);
		tfu.setTfUserUsername(string2);
		tfu.setTfRole(j == null ? null : session.get(TfRole.class, new BigDecimal(j)));
		
	}

	private static void populateRole(Integer i, String string, Session session) {
		TfRole tfr = new TfRole();
		tfr.setTfRoleId(i == null ? null : new BigDecimal(i));
		tfr.setTfRoleName(string);
		
		session.saveOrUpdate(tfr);
		
	}

	public static void populateAssociate(Integer i, String string, String string2, Integer j, Integer k, Integer l,
			Integer m, Session session) {
		TfAssociate tfa = new TfAssociate();
		TfMarketingStatus tfms = j == null ? null : session.get(TfMarketingStatus.class, new BigDecimal(j));
		TfClient tfc = k == null ? null : session.get(TfClient.class, new BigDecimal(k));
		TfEndClient tfec = l == null ? null : session.get(TfEndClient.class, new BigDecimal(l));
		TfBatch tfb = m == null ? null : session.get(TfBatch.class, new BigDecimal(m));
		tfa.setTfAssociateId(i == null ? null : new BigDecimal(i));
		tfa.setTfAssociateFirstName(string);
		tfa.setTfAssociateLastName(string2);
		tfa.setTfMarketingStatus(tfms);
		tfa.setTfClient(tfc);
		tfa.setTfEndClient(tfec);
		tfa.setTfBatch(tfb);

		session.saveOrUpdate(tfa);

	}

	public static void populateBatchLocation(Integer i, String string, Session session) {
		TfBatchLocation tfbl = new TfBatchLocation();
		tfbl.setTfBatchLocationId(i == null ? null : new BigDecimal(i));
		tfbl.setTfBatchLocationName(string);

		session.saveOrUpdate(tfbl);
	}

	public static void populateBatch(Integer i, String string, LocalDate of, LocalDate of2, Integer j, Integer k,
			Session session) {
		TfBatch batch = new TfBatch();
		TfCurriculum tfc = j == null ? null : session.get(TfCurriculum.class, new BigDecimal(j));
		TfBatchLocation tfbl = k == null ? null : session.get(TfBatchLocation.class, new BigDecimal(k));
		batch.setTfBatchId(i == null ? null : new BigDecimal(i));
		batch.setTfBatchName(string);
		batch.setTfBatchStartDate(of == null ? null : Timestamp.valueOf(of.atStartOfDay()));
		batch.setTfBatchEndDate(of2 == null ? null : Timestamp.valueOf(of2.atStartOfDay()));
		batch.setTfCurriculum(tfc);
		batch.setTfBatchLocation(tfbl);

		session.saveOrUpdate(batch);

	}

	public static void populateMarketingStatus(Integer i, String string, Session session) {
		TfMarketingStatus tfms = new TfMarketingStatus();
		tfms.setTfMarketingStatusId(i == null ? null : new BigDecimal(i));
		tfms.setTfMarketingStatusName(string);

		session.saveOrUpdate(tfms);

	}

	public static void populateCurriculum(Integer i, String string, Session session) {
		TfCurriculum tfc = new TfCurriculum();
		tfc.setTfCurriculumId(i == null ? null : new BigDecimal(i));
		tfc.setTfCurriculumName(string);

		session.saveOrUpdate(tfc);

	}

	public static void populateInterviewType(Integer i, String string, Session session) {
		TfInterviewType tfit = new TfInterviewType();
		tfit.setTfInterviewTypeId(i == null ? null : new BigDecimal(i));
		tfit.setTfInterviewTypeName(string);

		session.saveOrUpdate(tfit);
	}

	public static void populateClient(Integer i, String string, Session session) {
		TfClient tfc = new TfClient();
		tfc.setTfClientId(i == null ? null : new BigDecimal(i));
		tfc.setTfClientName(string);

		session.saveOrUpdate(tfc);
	}

	public static void populateEndClient(Integer i, String string, Session session) {
		TfEndClient tfec = new TfEndClient();
		tfec.setTfEndClientId(i == null ? null : new BigDecimal(i));
		tfec.setTfEndClientName(string);

		session.saveOrUpdate(tfec);

	}

	public static void truncateDB() throws HibernateException, IOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {

			StoredProcedureQuery spq = session.createStoredProcedureCall("admin.truncateAllDevTeam");
			spq.execute();

			session.flush();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.flush();
			tx.rollback();
		} finally {

			session.close();
		}
	}

}
