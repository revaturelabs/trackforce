package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.testng.annotations.AfterMethod;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfPlacement;
import com.revature.entity.TfUser;

/**
 * Tests to test basic getter and GetSet functionality for TfAssociate
 * 
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfAssociateTest {
	TfUser user = new TfUser();
	TfBatch batch = new TfBatch();
	TfMarketingStatus marketingStatus = new TfMarketingStatus();
	TfClient client = new TfClient();
	TfEndClient endClient = new TfEndClient();
	Set<TfInterview> interviews = new HashSet<>();
	Set<TfPlacement> placements = new HashSet<>();
	Timestamp clientStartDate = new Timestamp(10L);
	TfAssociate associate1 = new TfAssociate(1, user, batch, marketingStatus, client, endClient, "firstName",
			"lastName", interviews, placements, clientStartDate);

	TfAssociate associate2 = new TfAssociate(1, user, batch, marketingStatus, client, endClient, "firstName",
			"lastName", interviews, placements, clientStartDate);
	TfAssociate tfassociate = new TfAssociate();

	@Test
	public void testAssociateFirstNameGetSet() {
		tfassociate.setFirstName("Bob");
		assertTrue(tfassociate.getFirstName().equals("Bob"));
		assertFalse(tfassociate.getFirstName().equals("bob"));
	}

	@Test
	public void testAssociateIDGetSet() {
		tfassociate.setId(13);
		assertTrue(tfassociate.getId() == 13);
		assertFalse(tfassociate.getId() == 14);
	}

	@Test
	public void testAssociateLastNameGetSet() {
		tfassociate.setLastName("Bobbert");
		assertTrue(tfassociate.getLastName().equals("Bobbert"));
		assertFalse(tfassociate.getLastName().equals("bobbert"));
	}

	@Test
	public void testAssociateBatchGetSet() {
		tfassociate.setBatch(new TfBatch());
		assertTrue(tfassociate.getBatch() instanceof TfBatch);
	}

	@Test
	public void testAssociateClientGetSet() {
		tfassociate.setClient(new TfClient());
		assertTrue(tfassociate.getClient() instanceof TfClient);
	}

	@Test
	public void testAssociateClientStartGetSet() {
		tfassociate.setClientStartDate(new Timestamp(1000L));
		assertTrue(tfassociate.getClientStartDate().getTime() == 1000L);
		assertFalse(tfassociate.getClientStartDate().getTime() == 2000L);
	}

	@Test
	public void testAssociateEndClientGetSet() {
		tfassociate.setEndClient(new TfEndClient());
		assertTrue(tfassociate.getEndClient() instanceof TfEndClient);
	}

	@Test
	public void testAssociateInterviewGetSet() {
		tfassociate.setInterview(new HashSet<TfInterview>());
		assertTrue(tfassociate.getInterview() instanceof HashSet);
	}

	@Test
	public void testAssociateMarketStatusGetSet() {
		tfassociate.setMarketingStatus(new TfMarketingStatus());
		assertTrue(tfassociate.getMarketingStatus() instanceof TfMarketingStatus);
	}

	@Test
	public void testAssociatePlacementGetSet() {
		tfassociate.setPlacement(new HashSet<TfPlacement>());
		assertTrue(tfassociate.getPlacement() instanceof HashSet);
	}
	
	@Test
	public void testAssociateFeedbackGetSet() {
		String feedback1 = "For tfassociate";
		String feedback2 = "For associate1";
		tfassociate.setStagingFeedback(feedback1);
		associate1.setStagingFeedback(feedback2);
		assertTrue(tfassociate.getStagingFeedback().equals(feedback1));
		assertTrue(!associate1.getStagingFeedback().equals(feedback1));
	}
	
	//Have to hard code SerialID for now as it is private static,
	//always verify this is using the correct SerialID as defined
	//in the TfAssociate.java file
	@Test
	public void testAssociateSerialIDGet() {
		long sid = -2324082555924677252L;
		assertTrue(TfAssociate.getSerialversionuid() == sid);
	}

	@Test
	public void testAssociateEquivalence() {
		assertTrue(associate1.toString().equals(associate2.toString()));
		assertFalse(associate1.equals(new TfAssociate()));
	}
	
	@AfterMethod
	public void afterTest() {
		
	}
}
