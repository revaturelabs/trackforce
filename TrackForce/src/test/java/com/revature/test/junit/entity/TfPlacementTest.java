package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfPlacement;

/**
 * Tests to test basic getter and setter functionality for TfPlacement
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfPlacementTest {

	TfPlacement tfplacement = new TfPlacement();

	@Test
	public void test1() {
		tfplacement.setTfAssociate(new TfAssociate());
		assertTrue(tfplacement.getTfAssociate() instanceof TfAssociate);
	}
	@Test
	public void test2() {
		tfplacement.setTfClient(new TfClient());
		assertTrue(tfplacement.getTfClient() instanceof TfClient);
	}
	@Test
	public void test3() {
		tfplacement.setTfEndClient(new TfEndClient());
		assertTrue(tfplacement.getTfEndClient() instanceof TfEndClient);
	}
	@Test
	public void test4() {
		tfplacement.setTfPlacementEndDate(new Timestamp(1000L));
		assertTrue(tfplacement.getTfPlacementEndDate().getTime() == 1000L);
		assertFalse(tfplacement.getTfPlacementEndDate().getTime() == 2000L);
	}
	@Test
	public void test5() {
		tfplacement.setTfPlacementId(99);
		assertTrue(tfplacement.getTfPlacementId() == 99);
		assertFalse(tfplacement.getTfPlacementId() == 1234);
	}
	@Test
	public void test6() {
		tfplacement.setTfPlacementStartDate(new Timestamp(1000L));
		assertTrue(tfplacement.getTfPlacementStartDate().getTime() == 1000L);
		assertFalse(tfplacement.getTfPlacementStartDate().getTime() == 2000L);
	}
}
