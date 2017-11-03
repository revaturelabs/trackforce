package com.revature.test;

import static org.junit.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.revature.dao.BatchDao;
import com.revature.dao.BatchDaoHibernate;
import com.revature.entity.TfBatch;

public class BatchDaoHibernateTest {
    
  @Test
  public void getBatchByIDTest() {
      BatchDao batchDao = new BatchDaoHibernate();
      TfBatch batch = batchDao.getBatch(1);
      
      assertNotNull(batch);
      System.out.println(batch.getTfClient());
  }
}