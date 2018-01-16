package com.revature.dao;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.revature.services.PersistentServiceDelegator;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PersistentStorage;

public class PersistentStorageTest {

	@BeforeSuite
	public void before() throws IOException {
		HibernateUtil.getSession();
		new PersistentServiceDelegator().getAssociates();
		new PersistentServiceDelegator().getBatches();
		new PersistentServiceDelegator().getClients();
		new PersistentServiceDelegator().getCurriculums();
		new PersistentServiceDelegator().getMarketingStatuses();
	}
	
	@AfterSuite
	public void after() {
		HibernateUtil.shutdown();
	}
	
	@Test
	public void testPersistence() {
		assertTrue(PersistentStorage.getStorage().getAssociates() != null);
		assertTrue(PersistentStorage.getStorage().getBatches() != null);
		assertTrue(PersistentStorage.getStorage().getClients() != null);
		assertTrue(PersistentStorage.getStorage().getCurriculums() != null);
		assertTrue(PersistentStorage.getStorage().getMarketingStatuses() != null);
	}
}
