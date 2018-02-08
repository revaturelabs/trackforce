package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.model.ClientInfo;
import com.revature.model.StatusInfo;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

@Path("/clients")
public class ClientService implements Service {
    private ClientDao clientDao;
    private SessionFactory sessionFactory;

    public ClientService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
        this.clientDao = new ClientDaoImpl();
    }


    /**
     * @param clientDao injectable dao for easier testing
     */
    public ClientService(ClientDao clientDao, SessionFactory sessionFactory) {
        this.clientDao = clientDao;
        this.sessionFactory = sessionFactory;
    }


    /**
     * Returns a map of all of the clients from the TfClient table as a response
     * object.
     * <p>
     * uses cache mechanism
     *
     * @return A map of TfClients as a Response object
     * @throws IOException
     * @throws HibernateException
     */
    private Set<ClientInfo> getAllClients() throws IOException {
        Set<ClientInfo> clients = PersistentStorage.getStorage().getClients();
        if (clients == null || clients.isEmpty()) {
            execute();
            return PersistentStorage.getStorage().getClients();
        }
        return clients;
    }


    /**
     * same as getAllClients but does not use cache mechanism
     *
     * @return
     * @throws IOException
     */
    public Map<BigDecimal, ClientInfo> getClients() throws IOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Map<BigDecimal, ClientInfo> map = clientDao.getAllTfClients(session);

            session.flush();
            tx.commit();
            return map;
        } catch (Exception e) {
            LogUtil.logger.error(e);
            tx.rollback();
            throw new IOException("could not get clients", e);
        } finally {
            session.close();
        }
    }


    /**
     * Returns a StatusInfo object representing a client's associates and their
     * statuses.
     *
     * @param clientid The id of the client in the TfClient table
     * @return A StatusInfo object for a specified client
     * @throws IOException
     * @throws HibernateException
     */
    @GET
    @Path("{clientid}")
    @Produces({MediaType.APPLICATION_JSON})
    public StatusInfo getClientInfo(@PathParam("clientid") int clientid) throws IOException {
        Map<BigDecimal, ClientInfo> map = PersistentStorage.getStorage().getClientAsMap();
        if (map == null || map.isEmpty()) {
            execute();
        }
        if (map == null)
            throw new IOException("Could not populate map");
        return map.get(new BigDecimal(clientid)).getStats();
    }


    @SuppressWarnings("unchecked")
    private <T> Set<T> getTotals() throws IOException {
        Set<StatusInfo> set = new HashSet<>();
        Set<ClientInfo> ci = PersistentStorage.getStorage().getClients();
        if (ci == null || ci.isEmpty()) {
            execute();
        }
        StatusInfo si = PersistentStorage.getStorage().getTotals();
        set.add(si);
        return (Set<T>) set;
    }

    @Override
    public synchronized void execute() throws IOException {
        Set<ClientInfo> ci = PersistentStorage.getStorage().getClients();
        if (ci == null || ci.isEmpty())
            PersistentStorage.getStorage().setClients(getClients());
    }

    // these are fine and tested
    @SuppressWarnings("unchecked")
    @Override
    public <T> Set<T> read(String... args) throws IOException {
        if (args == null || args.length == 0)
            return (Set<T>) getAllClients();
        return getTotals();
    }
}
