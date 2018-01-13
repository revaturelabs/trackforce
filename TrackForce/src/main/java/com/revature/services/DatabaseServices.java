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
import com.revature.utils.DBLoaderUtil;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.StatusInfoUtil;

@Path("database") // http://localhost:8080/
public class DatabaseServices {

	@GET
	@Path("populateDB")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response populateDB() throws IOException {
			DBLoaderUtil.populateDB();
			StatusInfoUtil.clearMaps();
			return Response.ok().build();
	}

	@DELETE
	@Path("deleteFromDB")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteDB() throws IOException {
		DBLoaderUtil.truncateDB();
		StatusInfoUtil.clearMaps();
		return Response.ok().build();
	}

	@GET
	@Path("populateDBSF")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response populateDBSF() throws IOException {
		DBLoaderUtil.populateDBSF();
		StatusInfoUtil.clearMaps();
		return Response.ok().build();
	}
}
