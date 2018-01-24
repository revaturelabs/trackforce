package com.revature.test.services;
import static org.testng.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;

import org.testng.annotations.Test;

import com.revature.model.AssociateInfo;
import com.revature.services.AssociateService;
import com.revature.utils.PersistentStorage;

public class PersistentStorageTest {
	
	@Test
	public void testActualPersistence() {
		assertNotNull(PersistentStorage.getStorage().getAssociates());
		assertFalse(PersistentStorage.getStorage().getAssociates().isEmpty());
		assertNotNull(PersistentStorage.getStorage().getBatches());
		assertFalse(PersistentStorage.getStorage().getBatches().isEmpty());
		assertNotNull(PersistentStorage.getStorage().getClients());
		assertFalse(PersistentStorage.getStorage().getClients().isEmpty());
		assertNotNull(PersistentStorage.getStorage().getCurriculums());
		assertFalse(PersistentStorage.getStorage().getCurriculums().isEmpty());
		assertNotNull(PersistentStorage.getStorage().getMarketingStatuses());
		assertFalse(PersistentStorage.getStorage().getMarketingStatuses().isEmpty());
	}
	
	@Test
	public void updateAssociate() throws NumberFormatException, IOException {
		System.err.println(PersistentStorage.getStorage().getAssociateAsMap());
//		AssociateInfo ai = PersistentStorage.getStorage().getAssociateAsMap();
//		AssociateInfo copy = new AssociateInfo();
//		
//		// We need an associate with different info to test against
//		assertNotEquals(2, ai.getMsid());
//		assertNotEquals(1, ai.getClid());
//		
//		copy.setBid(ai.getBid());
//		copy.setClid(ai.getClid());
//		copy.setCurid(ai.getCurid());
//		copy.setMarketingStatusId(ai.getMsid());
//		copy.setEcid(ai.getEcid());
//		
//		AssociateService as = new AssociateService();
//		as.updateAssociate("1", "2", "1");
//		
//		assertEquals(2, ai.getMsid());
//		assertEquals(1, ai.getClid());
	}
}
