package com.revature.utils;

import com.revature.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.StoredProcedureQuery;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DBPopulaterUtil {

 	public void truncateDB() throws HibernateException, IOException {
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

	public void populateUser(String string, String string2, Integer j, Session session) {
		TfUser tfu = new TfUser();
		tfu.setTfUserHashpassword(string);
		tfu.setTfUserUsername(string2);
		tfu.setTfRole(j == null ? null : session.get(TfRole.class, new BigDecimal(j)));

		session.saveOrUpdate(tfu);
	}

	public void populateRole(Integer i, String string, Session session) {
		TfRole tfr = new TfRole();
		tfr.setTfRoleId(i == null ? null : new BigDecimal(i));
		tfr.setTfRoleName(string);

		session.saveOrUpdate(tfr);
	}

 	public void populateBatch(Integer i, String string, LocalDate of, LocalDate of2, Integer j, Integer k,
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

	public void populatePlacement(Integer i, LocalDate localDate, LocalDate localDate2, Integer j, Integer k,
                                  Integer l, Session session) {
		TfPlacement placement = new TfPlacement();
		TfClient client = j == null ? null : session.get(TfClient.class, new BigDecimal(j));
		TfEndClient ec = k == null ? null : session.get(TfEndClient.class, new BigDecimal(k));
		TfAssociate assoc = l == null ? null : session.get(TfAssociate.class, new BigDecimal(l));
		placement.setTfPlacementId(i == null ? null : new BigDecimal(i));
		placement.setTfPlacementStartDate(localDate == null ? null : Timestamp.valueOf(localDate.atStartOfDay()));
		placement.setTfPlacementEndDate(localDate2 == null ? null : Timestamp.valueOf(localDate2.atStartOfDay()));
		placement.setTfClient(client);
		placement.setTfEndClient(ec);
		placement.setTfAssociate(assoc);

		session.saveOrUpdate(placement);
	}

	public void populateInterview(Integer i, LocalDateTime of, String string, Integer j, Integer k, Integer l,
                                  Integer m, Session session) {
		TfInterview tfi = new TfInterview();
		TfClient tfc = j == null ? null : session.get(TfClient.class, new BigDecimal(j));
		TfEndClient tfec = k == null ? null : session.get(TfEndClient.class, new BigDecimal(k));
		TfInterviewType tfit = l == null ? null : session.get(TfInterviewType.class, new BigDecimal(l));
		TfAssociate tfa = m == null ? null : session.get(TfAssociate.class, new BigDecimal(m));
		tfi.setTfInterviewId(i == null ? null : new BigDecimal(i));
		tfi.setTfInterviewDate(Timestamp.valueOf(of));
		tfi.setTfInterviewFeedback(string);
		tfi.setTfClient(tfc);
		tfi.setTfEndClient(tfec);
		tfi.setTfInterviewType(tfit);
		tfi.setTfAssociate(tfa);

		session.saveOrUpdate(tfi);
	}

	public void populateAssociate(Integer i, String string, String string2, Integer j, Integer k, Integer l,
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

	public void populateBatchLocation(Integer i, String string, Session session) {
		TfBatchLocation tfbl = new TfBatchLocation();
		tfbl.setTfBatchLocationId(i == null ? null : new BigDecimal(i));
		tfbl.setTfBatchLocationName(string);

		session.saveOrUpdate(tfbl);
	}

	public void populateMarketingStatus(Integer i, String string, Session session){
		TfMarketingStatus tfms = new TfMarketingStatus();
		tfms.setTfMarketingStatusId(i == null ? null : new BigDecimal(i));
		tfms.setTfMarketingStatusName(string);

		session.saveOrUpdate(tfms);
	}

	public void populateCurriculum(Integer i, String string, Session session) {
		TfCurriculum tfc = new TfCurriculum();
		tfc.setTfCurriculumId(i == null ? null : new BigDecimal(i));
		tfc.setTfCurriculumName(string);

		session.saveOrUpdate(tfc);
	}

	public void populateInterviewType(Integer i, String string, Session session){
		TfInterviewType tfit = new TfInterviewType();
		tfit.setTfInterviewTypeId(i == null ? null : new BigDecimal(i));
		tfit.setTfInterviewTypeName(string);

		session.saveOrUpdate(tfit);
	}

	public void populateClient(Integer i, String string, Session session){
		TfClient tfc = new TfClient();
		tfc.setTfClientId(i == null ? null : new BigDecimal(i));
		tfc.setTfClientName(string);

		session.saveOrUpdate(tfc);
	}

	public void populateEndClient(Integer i, String string, Session session) {
		TfEndClient tfec = new TfEndClient();
		tfec.setTfEndClientId(i == null ? null : new BigDecimal(i));
		tfec.setTfEndClientName(string);

		session.saveOrUpdate(tfec);
	}
}
