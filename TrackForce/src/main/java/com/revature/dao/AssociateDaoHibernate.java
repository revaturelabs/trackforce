package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.AssociateInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;

public class AssociateDaoHibernate implements AssociateDao {

    /**
     * Get a associate from the database given its id.
     *
     * @param associateid
     * @throws IOException
     */
    @Override
    public AssociateInfo getAssociate(BigDecimal associateid) {
        TfAssociate associate = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        	CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TfAssociate> criteriaQuery = builder.createQuery(TfAssociate.class);
            Root<TfAssociate> root = criteriaQuery.from(TfAssociate.class);
            criteriaQuery.select(root).where(builder.equal(root.get("tfAssociateId"), associateid));
            Query<TfAssociate> query = session.createQuery(criteriaQuery);
            associate = query.getSingleResult();
        }
        catch (HibernateException e) {
        	e.printStackTrace();
        }
        return Dao2DoMapper.map(associate);
    }

    /**
     * Updates an associate's marketing status and client in the database.
     *
     * @param id              - The ID of the associate to update.
     * @param marketingStatus - A TfMarketingStatus object with the status to change the associate to.
     * @param client          - A TfClient object with what client the associate will be mapped to.
     */
    @Override
    public boolean updateAssociate(BigDecimal id, int marketingStatus, int client) {
        TfClient tfclient = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            tfclient = session.get(TfClient.class, client);
            TfMarketingStatus tfms = session.load(TfMarketingStatus.class, marketingStatus);
            TfAssociate associate = session.load(TfAssociate.class, id);
            associate.setTfMarketingStatus(tfms);
            associate.setTfClient(tfclient);
            session.saveOrUpdate(associate);
            return true;
        }
        catch (HibernateException e) {
        	e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Updates multiple associates' marketing status and client in the database.
     *
     * @param ids             - The array of IDs of the associates to update.
     * @param marketingStatus - A TfMarketingStatus int of which status to change the associate to.
     * @param client          - A TfClient int of which client the associate will be mapped to.
     */
    @Override
    public boolean updateAssociates(BigDecimal[] ids, int marketingStatus, int client) {
        TfClient tfclient = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            tfclient = session.get(TfClient.class, client);
            TfMarketingStatus tfms = session.load(TfMarketingStatus.class, marketingStatus);
            for (int i=ids[0].intValue();i<ids.length;i++) {
            	TfAssociate associate = session.load(TfAssociate.class, ids[i]);
                associate.setTfMarketingStatus(tfms);
                associate.setTfClient(tfclient);
                session.saveOrUpdate(associate);
            }
            return true;
        }
        catch (HibernateException e) {
        	e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<BigDecimal, AssociateInfo> getAssociates() {
        List<TfAssociate> associatesEnt;
        Map<BigDecimal, AssociateInfo> map = new HashMap<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        	CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TfAssociate> cq = cb.createQuery(TfAssociate.class);
            Root<TfAssociate> from = cq.from(TfAssociate.class);
            CriteriaQuery<TfAssociate> all = cq.select(from);
            Query<TfAssociate> tq = session.createQuery(all);
            
            associatesEnt = tq.getResultList();
            if (associatesEnt != null) {
                for (TfAssociate tfa : associatesEnt) {
                    map.put(tfa.getTfAssociateId(), Dao2DoMapper.map(tfa));
                    AssociateInfo.appendToMap(tfa.getTfMarketingStatus());
                }
            }
        }
        catch(HibernateException e) {
        	e.printStackTrace();
        }
        return map;
    }
}