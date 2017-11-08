package com.revature.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;

public class AssociationMappingTest {

	@Test
	public void test() {
		EntityManager em = HibernateUtil.getSession().createEntityManager();
//		TfClient client = em.find(TfClient.class, 1);
		
		
		TfAssociate associate = em.find(TfAssociate.class, BigDecimal.valueOf(1));
		System.out.println(associate.toString());
		TfMarketingStatus marketingstatus = associate.getTfMarketingStatus();
		System.out.println(marketingstatus.toString());
		
		
		
		
	}

}
