package com.revature.dao;

import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.*;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeSuite;

public class HomeDaoImplTest {

  @Test
  public void getAllTfAssociatesTest() {
	  Session session = HibernateUtil.getSession().openSession();
	  assertNotNull(session);
		CriteriaQuery<TfAssociate> cq = session.getCriteriaBuilder().createQuery(TfAssociate.class);
		assertNotNull(cq);
		cq.from(TfAssociate.class);
		assertNotNull(cq);
		assertEquals(cq.getRoots().size(), 1);
		List<TfAssociate> associates = session.createQuery(cq).getResultList();
		assertFalse(associates.isEmpty());
		for (TfAssociate associate : associates) {
			if (associate.getTfBatch() != null) {
				Hibernate.initialize(associate.getTfBatch());
				System.out.println(associate.getTfBatch().getTfBatchName());
				//assertNotNull(associate.getTfBatch());
			}
			Hibernate.initialize(associate.getTfMarketingStatus());
			//assertNotNull(associate.getTfMarketingStatus());
			Hibernate.initialize(associate.getTfClient());
			//assertNotNull(associate.getTfClient());
			
			if (associate.getTfBatch() != null) {
				if(associate.getTfBatch().getTfCurriculum()  != null) {
					Hibernate.initialize(associate.getTfBatch().getTfCurriculum());
					//System.out.println(associate.getTfBatch().getTfCurriculum().getTfCurriculumName());
				}
			}
			//assertNotNull(associate.getTfBatch().getTfCurriculum());
		}
		
		

		session.close();
		assertFalse(session.isConnected());
  }
}
