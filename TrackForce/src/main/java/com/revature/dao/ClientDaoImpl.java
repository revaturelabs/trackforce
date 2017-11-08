package com.revature.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.entity.TfClient;
import com.revature.model.StatusInfo;
import com.revature.utils.HibernateUtil;

public class ClientDaoImpl implements ClientDao {

    @Override
    public List<TfClient> getAllTfClients() {
        Session session = HibernateUtil.getSession().openSession();
        CriteriaQuery<TfClient> cq = session.getCriteriaBuilder().createQuery(TfClient.class);
        cq.from(TfClient.class);
        List<TfClient> clients = session.createQuery(cq).getResultList();

        session.close();
        return clients;
    }

    @Override
    public TfClient getClient(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.openSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TfClient> criteriaQuery = builder.createQuery(TfClient.class);

        Root<TfClient> root = criteriaQuery.from(TfClient.class);
        
        criteriaQuery.select(root).where(builder.equal(root.get("tfClientName"), name));
        
        Query<TfClient> query = session.createQuery(criteriaQuery);
        
        TfClient client;
        
        try {
            client = query.getSingleResult();
        } catch(NoResultException nre) {
            client = new TfClient();
        } finally {
            session.close();
        }
        
        return client;
    }

    @Override
    public StatusInfo getAllClientInfo() {
        final int numberOfStatuses = 10;
        EntityManager em = HibernateUtil.getSession().createEntityManager();

        int[] counts = new int[numberOfStatuses];
        for (int i = 1; i <= numberOfStatuses; i++) {
            StoredProcedureQuery query = em.createStoredProcedureQuery("ADMIN.GET_ALLCLIENTS_STATUS_COUNT");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
            query.setParameter(1, i);
            query.execute();
            counts[i - 1] = (int) query.getOutputParameterValue(2);
        }
        StatusInfo clientInfo = setClientInfoWithIntArray(counts);
        clientInfo.setName("All Clients");

        em.close();
        return clientInfo;
    }

    @Override
    public StatusInfo getClientInfo(int id) {

        EntityManager em = HibernateUtil.getSession().createEntityManager();

        final int numberOfStatuses = 10;

        int[] counts = new int[numberOfStatuses];

        for (int i = 1; i <= numberOfStatuses; i++) {
            StoredProcedureQuery query = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
            query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
            query.setParameter(1, id);
            query.setParameter(2, i);
            query.execute();
            counts[i - 1] = (int) query.getOutputParameterValue(3);
        }
        StatusInfo clientInfo = setClientInfoWithIntArray(counts);
        clientInfo.setName("Client name");

        em.close();
        return clientInfo;
    }

    /**
     * Returns a ClientInfo object with each status set based off of counts.
     * 
     * @param counts
     *            int array representing count of each status
     * @return a ClientInfo object with statuses set
     */
    private StatusInfo setClientInfoWithIntArray(int[] counts) {
    	StatusInfo clientInfo = new StatusInfo();
        clientInfo.setTrainingMapped(counts[0]);
        clientInfo.setReservedMapped(counts[1]);
        clientInfo.setSelectedMapped(counts[2]);
        clientInfo.setConfirmedMapped(counts[3]);
        clientInfo.setDeployedMapped(counts[4]);
        clientInfo.setTrainingUnmapped(counts[5]);
        clientInfo.setOpenUnmapped(counts[6]);
        clientInfo.setSelectedUnmapped(counts[7]);
        clientInfo.setConfirmedUnmapped(counts[8]);
        clientInfo.setDeployedUnmapped(counts[9]);
        return clientInfo;
    }
}
