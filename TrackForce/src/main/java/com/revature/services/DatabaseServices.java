package com.revature.services;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.DatabaseDAOImpl;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

@Path("database") // http://localhost:8080/
public class DatabaseServices {

	@GET
	@Path("populateDB")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response populateDB() throws IOException {
		String string;
		DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			string = dbCalls.populate();
			session.flush();
			tx.commit();

			return Response.ok(string).build();
		} catch (Exception e) {
			LogUtil.logger.error(e);
			e.printStackTrace();
			tx.rollback();
			throw new IOException("Could not populat DB", e);
		} finally {
			session.close();
		}
	}

	@DELETE
	@Path("deleteFromDB")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteDB() throws HibernateException, IOException {
		String string;
		DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			string = dbCalls.deleteAll();
			session.flush();
			tx.commit();

			return Response.ok(string).build();
		} catch (Exception e) {
			LogUtil.logger.error(e);
			e.printStackTrace();
			tx.rollback();
			throw new IOException("Could not populat DB", e);
		} finally {
			session.close();
		}
	}

	@GET
	@Path("populateDBSF")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response populateDBSF() throws HibernateException, IOException {
		String string;
		DatabaseDAOImpl dbCalls = new DatabaseDAOImpl();
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			string = dbCalls.populateSF();

			session.flush();
			tx.commit();

			return Response.ok(string).build();
		} catch (Exception e) {
			LogUtil.logger.error(e);
			e.printStackTrace();
			tx.rollback();
			throw new IOException("Could not populat DB", e);
		} finally {
			session.close();
		}
	}
}
