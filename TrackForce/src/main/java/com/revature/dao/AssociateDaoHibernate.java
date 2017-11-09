package com.revature.dao;

import java.math.BigDecimal;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;

public class AssociateDaoHibernate implements AssociateDao {
    /**
     * Get a associate from the database given its id.
     * 
     * @param associateid
     *            
     */
    @Override
    public TfAssociate getAssociate(BigDecimal associateid) {
        SessionFactory sessionFactory = HibernateUtil.getSession();
		Session session = sessionFactory.openSession();
		CriteriaBuilder builder=session.getCriteriaBuilder();
		CriteriaQuery<TfAssociate> criteriaQuery=builder.createQuery(TfAssociate.class);
		Root<TfAssociate> root=criteriaQuery.from(TfAssociate.class);
		criteriaQuery.select(root).where(builder.equal(root.get("tfAssociateId"), associateid));
		Query<TfAssociate> query=session.createQuery(criteriaQuery);
		
		TfAssociate associate;
		try {
			associate=query.getSingleResult();
			
			Hibernate.initialize(associate.getTfMarketingStatus());
            Hibernate.initialize(associate.getTfClient());
            Hibernate.initialize(associate.getTfEndClient());
		}catch(NoResultException nre) {
			associate=new TfAssociate();
		} finally {
		    session.close();
		}
		
    	return associate;
	} 

    @Override
    public void updateInfo(BigDecimal id, TfMarketingStatus marketingStatus, TfClient client) {

        TfBatch batch = new TfBatch();

        TfAssociate associate = new TfAssociate();
        associate.setTfMarketingStatus(marketingStatus);
        associate.setTfBatch(batch);
        associate.setTfClient(client);

        SessionFactory factory = HibernateUtil.getSession();
        Session session = factory.openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            session.saveOrUpdate(associate);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
    
    public void updateInfo2(BigDecimal id, String marketingStatus, String client) {

        TfBatch batch = new TfBatch();
        TfAssociate associate = getAssociate(id);
        TfBatch batch = associate.getTfBatch();
        TfClient associateClient = associate.getTfClient();
        associate.setTfMarketingStatus(associate.getTfMarketingStatus());
        associate.setTfClient(client);

        SessionFactory factory = HibernateUtil.getSession();
        Session session = factory.openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            session.saveOrUpdate(associate);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
    
    public void updateInfo3(BigDecimal id, TfMarketingStatus marketingStatus, TfClient client) {

        SessionFactory factory = HibernateUtil.getSession();
        Session session = factory.openSession();
        
        TfMarketingStatus status = session.get(TfMarketingStatus.class, marketingStatus.getTfMarketingStatusId());
        TfClient tfclient = session.get(TfClient.class, client.getTfClientId());
        
        System.out.println(status);
        System.out.println(tfclient);
        
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            TfAssociate associate = session.get(TfAssociate.class, id);
            associate.setTfMarketingStatus(status);
            associate.setTfClient(tfclient);

            session.update(associate);
            
            transaction.commit();
            session.flush();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}
