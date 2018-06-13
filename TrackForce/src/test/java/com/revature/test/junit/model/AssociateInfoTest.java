package com.revature.test.junit.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;

import com.revature.model.AssociateInfo;

/***
 * jUnit tests for AssociateInfo in com.revature.model
 * @author David Kim
 * @since 6.18.06.08
 */
public class AssociateInfoTest {
	
	AssociateInfo associate = new AssociateInfo(175, "Brian", "Newman", "mapped", "Infosys", "JTA 1804", "Full Stack", 33554432, 1 );
	AssociateInfo associate2 = new AssociateInfo();

	
	
	@Test
	public void testGetMsid () {
		assertTrue(associate2.getMsid().equals(null));
		assertFalse(associate2.getMsid().equals("11"));
	}
	
	@Test
	public void testSetMsid() {
		associate2.setMsid(7);
		assertTrue(associate2.getMsid() == 7);
		assertFalse(associate2.getMsid() == 0);
	}
	
	@Test
	public void testGetClid() {
		assertTrue(associate2.getClid().equals(null));
		assertFalse(associate2.getClid() == 7);
	}
	
	@Test
	public void testSetClid() {
		associate2.setClid(9);
		assertTrue(associate2.getClid() == 9);
		assertFalse(associate2.getClid() == 0);
	}
	
	@Test 
	public void testGetEcid() {
		assertTrue(associate2.getEcid().equals(null));
		assertFalse(associate2.getEcid().equals(9));
	}
	
	@Test
	public void testSetEcid() {
		associate2.setEcid(7);
		assertTrue(associate2.getEcid() == 7);
		assertFalse(associate2.getEcid() == 0);
	}
	
	@Test
	public void testGetBid() {
		assertTrue(associate2.getBid().equals(null));
		assertFalse(associate2.getBid().equals("7"));
	}
	
	@Test
	public void testSetBid() {
		associate2.setBid(7);
		assertTrue(associate2.getBid() == 7);
		assertFalse(associate2.getBid() == 0);
	}
	
	@Test
	public void testGetCurid() {
		assertTrue(associate2.getCurid().equals(null));
		assertFalse(associate2.getCurid().equals(7));
	}
	
	@Test
	public void testSetCurid() {
		associate2.setCurid(7);
		assertTrue(associate2.getBid() == 7);
		assertFalse(associate2.getBid() == 0);
	}
	
	@Test
	public void testgetId() {
		assertTrue(associate.getId() == 175);
		assertFalse(associate.getId() == 0);
	}
	
	@Test
	public void testSetId() {
		associate.setId(200);
		assertTrue(associate.getId() == 200);
		assertFalse(associate.getId() == 175);
	}
	
	@Test 
	public void testGetFirstName() {
		assertTrue(associate.getFirstName().equals("Brian"));
		assertFalse(associate.getFirstName().equals("Bran"));
	}
	
	@Test
	public void testSetFirstName() {
		associate.setFirstName("Fred");
		assertTrue(associate.getFirstName().equals("Fred"));
		assertFalse(associate.getFirstName().equals("Frederick"));
	}
	
	@Test
	public void testGetLastName() {
		assertTrue(associate.getLastName().equals("Newman"));
		assertFalse(associate.getLastName().equals("Napolean"));
	}
	
	@Test
	public void testSetLastName() {
		associate.setLastName("Miller");
		assertTrue(associate.getLastName().equals("Miller"));
		assertFalse(associate.getLastName().equals("Myler"));
	}	
	
	@Test
	public void testGetMarketingStatus() {
		assertTrue(associate.getMarketingStatus().equals("mapped"));
		assertFalse(associate.getMarketingStatus().equals("unmapped"));
	}
	
	@Test
	public void testSetMarketingStatus() {
		associate.setMarketingStatus("unmapped");
		assertTrue(associate.getMarketingStatus().equals("unmapped"));
		assertFalse(associate.getMarketingStatus().equals("mapped"));
	}
	
	@Test
	public void testGetClient() {
		assertTrue(associate.getClient().equals("Infosys"));
		assertFalse(associate.getClient().equals("Accenture"));
	}
	
	@Test
	public void testSetClient() {
		associate.setClient("Accenture");
		assertTrue(associate.getClient().equals("Accenture"));
		assertFalse(associate.getClient().equals("Apple"));
	}
	
	@Test
	public void testGetEndClient() {
		assertTrue(associate2.getEndClient().equals(null));
		assertFalse(associate2.getEndClient().equals("Microsoft"));
	}
	
	@Test
	public void testSetEndClient() {
		associate2.setEndClient("Accenture");
		assertTrue(associate2.getEndClient().equals("Accenture"));
		assertFalse(associate2.getEndClient().equals(null));
	}
	
	@Test
	public void testGetBatchName() {
		assertTrue(associate.getBatchName().equals("JTA 1804"));
		assertFalse(associate.getBatchName().equals("JTA 1709"));
	}
	
	@Test
	public void testSetBatchName() {
		associate.setBatchName("JTA 1712");
		assertTrue(associate.getBatchName().equals("JTA 1712"));
		assertFalse(associate.getBatchName().equals("JTA 1804"));
	}
	
	@Test 
	public void testGetCurriculumName() {
		assertTrue(associate.getCurriculumName().equals("Full Stack"));
		assertFalse(associate.getCurriculumName().equals("Appian"));
	}
	
	@Test
	public void testSetCurriculumName() {
		associate.setCurriculumName("Appian");
		assertTrue(associate.getCurriculumName().equals("Appian"));
		assertFalse(associate.getCurriculumName().equals("C#"));
	}
	
	@Test
	public void testGetClientStartDate() {
		assertTrue(associate.getClientStartDate() ==  33554432);
		assertFalse(associate.getClientStartDate() == 257000);
	}
	
	@Test
	public void testSetClientStartDate() {
		associate.setClientStartDate(100000);
		assertTrue(associate.getClientStartDate() == 100000);
		assertFalse(associate.getClientStartDate() == 33554432);
	}
	
	@Test
	public void testGetIsApproved() {
		assertTrue(associate.getIsApproved() == 1);
		assertFalse(associate.getIsApproved() == 0);
	}
	
	@Test
	public void testSetIsApproved() {
		associate.setIsApproved(0);
		assertTrue(associate.getIsApproved() == 0);
		assertFalse(associate.getIsApproved() == 1);
	}
	
	@Test
	public void testSetBatchId() {
		associate2.setBatchId(null);
		assertTrue(associate.getBid().equals("-1"));
		assertFalse(associate.getBid() == 50);
	}
	
	@Test
	public void testSetClientId() {
		associate2.setClientId(null);
		assertTrue(associate2.getClid() == -1);
		assertFalse(associate2.getClid() == 11);
	}
	
	@Test
	public void testSetCurriculumId() {
		associate2.setCurriculumId(null);
		assertTrue(associate2.getCurid() == -1);
		assertFalse(associate2.getCurid() == 53);
	}
	
	@Test
	public void testSetEndCliendId() {
		associate2.setEndClientId(null);
		assertTrue(associate2.getEcid() == -1);
		assertFalse(associate2.getEcid() == 23);
	}
	
	@Test
	public void testSetMarketingStatusId() {
		associate2.setMarketingStatusId(null);
		assertTrue(associate2.getMsid() == -1);
		assertFalse(associate2.getMsid() == 0);
	}
	
	@Test
	public void testGetTotals() {
		assertTrue(AssociateInfo.getTotals().equals(""));
		assertFalse(AssociateInfo.getTotals().equals("reserved"));
	}
	
}
