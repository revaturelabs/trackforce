package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.AssociateInfo;
import com.revature.model.ClientInfo;
import com.revature.model.MarketingStatusInfo;
import com.revature.utils.Dao2DoMapper;

public class AssociateDaoHibernate implements AssociateDao {

    /**
     * Get a associate from the database given its id.
     *
     * @param associateid
     * @throws IOException
     */
    @Override
    public AssociateInfo getAssociate(BigDecimal associateid, Session session) {
        TfAssociate associate;

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TfAssociate> criteriaQuery = builder.createQuery(TfAssociate.class);
        Root<TfAssociate> root = criteriaQuery.from(TfAssociate.class);
        criteriaQuery.select(root).where(builder.equal(root.get("tfAssociateId"), associateid));
        Query<TfAssociate> query = session.createQuery(criteriaQuery);

        associate = query.getSingleResult();

        return Dao2DoMapper.map(associate);
    }

    /**
     * Updates an associate's marketing status and client in the database.
     *
     * @param id              - The ID of the associate to update.
     * @param marketingStatus - A TfMarketingStatus object with the status to change the
     *                        associate to.
     * @param client          - A TfClient object with what client the associate will be mapped
     *                        to.
     * @throws IOException
     */
    @Override
    public void updateInfo(Session session, BigDecimal id, MarketingStatusInfo marketingStatus, ClientInfo client) throws IOException {

        TfClient tfclient = null;
        if (client.getId() != null) {
            tfclient = session.get(TfClient.class, client.getId());
        }

        TfMarketingStatus tfms = session.get(TfMarketingStatus.class, marketingStatus.getId());

        TfAssociate associate = session.load(TfAssociate.class, id);
        associate.setTfMarketingStatus(tfms);
        associate.setTfClient(tfclient);
        session.saveOrUpdate(associate);

    }

    @Override
    public Map<BigDecimal, AssociateInfo> getAssociates(Session session) {
        List<TfAssociate> associatesEnt;
        Map<BigDecimal, AssociateInfo> map = new HashMap<>();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TfAssociate> cq = cb.createQuery(TfAssociate.class);
        Root<TfAssociate> from = cq.from(TfAssociate.class);
        CriteriaQuery<TfAssociate> all = cq.select(from);
        Query<TfAssociate> tq = session.createQuery(all);

        associatesEnt = tq.getResultList();
        if (associatesEnt != null) {
            for (TfAssociate tfa : associatesEnt) {    //TfAssociate class found in .entity package
                map.put(tfa.getTfAssociateId(), Dao2DoMapper.map(tfa));   //Dao2DoMapper found in utils package
                AssociateInfo.appendToMap(tfa.getTfMarketingStatus());
            }
        }

        return map;
    }
}