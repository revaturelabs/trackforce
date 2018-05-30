package com.revature.services;

import java.io.IOException;

import javax.persistence.StoredProcedureQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.BatchDaoHibernate;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.CurriculumDaoImpl;
import com.revature.dao.InterviewDaoHibernate;
import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

/**
 * For all intensive purposes, this service mocks Salesforce albeit extreme
 * Salesforce would actually insert smaller data sets to be added to the cache
 * DatabaseService just wipes the entire DB or populates it
 */
@Path("database")
public class DatabaseServices {

	private SessionFactory sessionFactory;

	public DatabaseServices() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	/**
	 * injectable dependencies for easier testing
	 *
	 * @param psd
	 */

	public DatabaseServices(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@GET
	@Path("populateDB")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response populateDB() throws IOException, HibernateException {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			StoredProcedureQuery spq = session.createStoredProcedureCall("admin.populateAllTables_PROC");
			spq.execute();
		} catch (Exception e) {
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
		} finally {
			update();
			session.close();
		}
		return Response.ok().build();

	}

	@DELETE
	@Path("deleteFromDB")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteDB() throws IOException, HibernateException {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			StoredProcedureQuery spq = session.createStoredProcedureCall("admin.truncateAllDevTeam");
			spq.execute();
		} catch (Exception e) {
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
		} finally {
			update();
			session.close();
		}
		return Response.ok().build();
	}

	@GET
	@Path("populateDBSF")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response populateDBSF() throws IOException {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			StoredProcedureQuery spq = session.createStoredProcedureCall("admin.populateAllTablesSF_PROC");
			spq.execute();
		} catch (Exception e) {
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
		} finally {
			update();
			session.close();
		}
		return Response.ok().build();
	}

	public void update() throws IOException {
		AssociateDaoHibernate.getInstance().cacheAllAssociates();
		new BatchDaoHibernate().cacheAllBatches();
		new ClientDaoImpl().cacheAllClients();
		new CurriculumDaoImpl().cacheAllCurriculms();
		new MarketingStatusDaoHibernate().cacheAllMarketingStatuses();
		new InterviewDaoHibernate().cacheAllInterviews();
	}
}
