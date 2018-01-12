package com.revature.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.ClientDaoImpl;
import com.revature.dao.HomeDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.model.StatusInfo;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.StatusInfoUtil;

@Path("/clients")
public class ClientResource {

	private ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
	private HomeDaoImpl homeDaoImpl = new HomeDaoImpl();

	/**
	 * Returns a map of all of the clients with associates from the TfClient table
	 * as a response object.
	 * 
	 * @return A map of TfClients with associates as a Response object
	 * @throws IOException
	 * @throws HibernateException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllClientsWithAssociates() throws IOException {
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			List<TfClient> clients = clientDaoImpl.getAllTfClients();
			List<Map<String, Object>> entity = new ArrayList<>();
			for (TfClient client : clients) {
				if (!client.getTfAssociates().isEmpty()) {
					Map<String, Object> map = new HashMap<>();
					map.put("id", client.getTfClientId());
					map.put("name", client.getTfClientName());
					entity.add(map);
				}
			}

			session.flush();
			tx.commit();
			return Response.ok(entity).build();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			tx.rollback();
			throw new IOException("Could not get clients", e);
		} finally {
			session.close();
		}
	}

	/**
	 * Returns a map of all of the clients from the TfClient table as a response
	 * object.
	 * 
	 * @return A map of TfClients as a Response object
	 * @throws IOException
	 * @throws HibernateException
	 */
	@GET
	@Path("all")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllClients() throws IOException {
		List<TfClient> clients = clientDaoImpl.getAllTfClients();
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			List<Map<String, Object>> entity = new ArrayList<>();
			for (TfClient client : clients) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", client.getTfClientId());
				map.put("name", client.getTfClientName());
				entity.add(map);
			}

			session.flush();
			tx.commit();
			return Response.ok(entity).build();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			tx.rollback();
			throw new IOException("could not get clients", e);
		}
	}

	/**
	 * Returns a StatusInfo object representing all clients' associates and their
	 * statuses.
	 * 
	 * @return A StatusInfo object for all clients
	 * @throws IOException
	 * @throws HibernateException
	 */
	@GET
	@Path("info")
	@Produces({ MediaType.APPLICATION_JSON })
	public StatusInfo getAllClientInfo() throws IOException {
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		StatusInfo info;
		try {
			init();
			info = StatusInfoUtil.getAllAssociatesStatusInfo();
			session.flush();
			tx.commit();
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			tx.rollback();
			throw new IOException("could not get info", e);
		} finally {
			session.close();
		}
	}

	/**
	 * Returns a StatusInfo object representing a client's associates and their
	 * statuses.
	 * 
	 * @param clientid
	 *            The id of the client in the TfClient table
	 * @return A StatusInfo object for a specified client
	 * @throws IOException
	 * @throws HibernateException
	 */
	@GET
	@Path("{clientid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public StatusInfo getClientInfo(@PathParam("clientid") int clientid) throws HibernateException, IOException {
		init();
		StatusInfo info;
		if (clientid < 1)
			return new StatusInfo();
		info = StatusInfoUtil.getClientStatusInfo(clientid);

		return info;
	}

	/**
	 * Initializes objects needed for functionality from the StatusInfoUtil when
	 * maps in StatusInfoUtil are empty.
	 * 
	 * @throws IOException
	 * @throws HibernateException
	 */
	private void init() throws HibernateException, IOException {
		if (StatusInfoUtil.mapsAreEmpty()) {
			initForce();
		}
	}

	/**
	 * Forces initialization of objects needed for functionality from the
	 * StatusInfoUtil.
	 * 
	 * @throws IOException
	 * @throws HibernateException
	 */
	@POST
	@Path("init")
	public void initForce() throws HibernateException, IOException {
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			HomeDaoImpl.clearAssociates();
			clientDaoImpl.clearClients();
			StatusInfoUtil.clearMaps();
			List<TfAssociate> tfAssociates = homeDaoImpl.getAllTfAssociates(session);
			StatusInfoUtil.updateStatusInfoFromAssociates(tfAssociates);

			session.flush();
			tx.commit();
		} catch (Exception e) {
			session.flush();
			tx.rollback();
		} finally {
			session.close();
		}
	}
}
