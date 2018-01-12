package com.revature.dao;

import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeSuite;

public class HomeDaoImplTest {

	HomeDaoImpl hdao = new HomeDaoImpl();
	
	@Test
	public void getAllTfAssociatesTest() throws IOException {
		//List<TfAssociate> result = hdao.getAllTfAssociates();
		//assertNotNull(result);
	}
}
