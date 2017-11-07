package com.revature.dao;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;

public class AssociateDaoHibernate implements AssociateDao {

    @Override
    public void updateInfo(BigDecimal id, TfMarketingStatus marketingStatus, TfClient client) {

        TfBatch batch = new TfBatch();

        TfAssociate associate = new TfAssociate();
        associate.setTfMarketingStatus(marketingStatus);
        associate.setTfBatch(batch);
        associate.getTfBatch().setTfClient(client);

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
}
