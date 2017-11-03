package com.revature.test;

import static org.junit.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.revature.dao.BatchDao;
import com.revature.dao.BatchDaoHibernate;
import com.revature.entity.TfBatch;

public class BatchDaoHibernateTest {
    
  @Test (enabled=false)
  public void getCurriculumNameTest() {
      BatchDao batchDao = new BatchDaoHibernate();
      
      String curriculumName = batchDao.getBatchCirriculumName(0);
      assertNotNull(curriculumName);
      
      System.out.println(curriculumName);
  }
  
  @Test
  public void getBatchByIDTest() {
      BatchDao batchDao = new BatchDaoHibernate();
      TfBatch batch = batchDao.getBatch(1);
      
      assertNotNull(batch);
      System.out.println(batch);
  }
}
