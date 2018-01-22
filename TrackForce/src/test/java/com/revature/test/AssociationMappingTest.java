package com.revature.test;

import java.io.IOException;
import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;

public class AssociationMappingTest {

	@Test
	public void test() throws IOException {
		EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
//		TfClient client = em.find(TfClient.class, 1);
		
		
		TfAssociate associate = em.find(TfAssociate.class, BigDecimal.valueOf(1));
		System.out.println(associate.toString());
		TfMarketingStatus marketingstatus = associate.getTfMarketingStatus();
		System.out.println(marketingstatus.toString());
		
		
		
		
	}

}
