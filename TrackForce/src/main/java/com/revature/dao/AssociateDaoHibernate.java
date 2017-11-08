package com.revature.dao;

import java.math.BigDecimal;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfAssociate;
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
		Session session=HibernateUtil.getSession();
		CriteriaBuilder builder=session.getCriteriaBuilder();
		CriteriaQuery<TfAssociate> criteriaQuery=builder.createQuery(TfAssociate.class);
		Root<TfAssociate> root=criteriaQuery.from(TfAssociate.class);
		criteriaQuery.select(root).where(builder.equal(root.get("tfAssociateId"), associateid));
		Query<TfAssociate> query=session.createQuery(criteriaQuery);
		
		TfAssociate associate;
		try {
			associate=query.getSingleResult();
		}catch(NoResultException nre) {
			associate=new TfAssociate();
		}
    	return associate;
		
	} 
}
