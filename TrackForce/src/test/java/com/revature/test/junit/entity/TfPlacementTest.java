package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfPlacement;

/**
 * Tests to test basic getter and setter functionality for TfPlacement
 * 
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfPlacementTest {
	TfAssociate associate = new TfAssociate();
	TfClient client = new TfClient();
	TfEndClient endClient = new TfEndClient();
	Timestamp start = new Timestamp(1L);
	Timestamp end = new Timestamp(2L);

	TfPlacement tfplacement1 = new TfPlacement(1, associate, client, endClient, start, end);
	TfPlacement tfplacement2 = new TfPlacement(1, associate, client, endClient, start, end);
	TfPlacement tfplacement = new TfPlacement();

	@Test
	public void test1() {
		tfplacement.setAssociate(new TfAssociate());
		assertTrue(tfplacement.getAssociate() instanceof TfAssociate);
	}

	@Test
	public void test2() {
		tfplacement.setClient(new TfClient());
		assertTrue(tfplacement.getClient() instanceof TfClient);
	}

	@Test
	public void test3() {
		tfplacement.setEndClient(new TfEndClient());
		assertTrue(tfplacement.getEndClient() instanceof TfEndClient);
	}

	@Test
	public void test4() {
		tfplacement.setEnd(new Timestamp(1000L));
		assertTrue(tfplacement.getEnd().getTime() == 1000L);
		assertFalse(tfplacement.getEnd().getTime() == 2000L);
	}

	@Test
	public void test5() {
		tfplacement.setId(99);
		assertTrue(tfplacement.getId() == 99);
		assertFalse(tfplacement.getId() == 1234);
	}

	@Test
	public void test6() {
		tfplacement.setStart(new Timestamp(1000L));
		assertTrue(tfplacement.getStart().getTime() == 1000L);
		assertFalse(tfplacement.getStart().getTime() == 2000L);
	}

	@Test
	public void test7() {
		assertTrue(tfplacement1.equals(tfplacement2));
		assertFalse(tfplacement1.equals(new TfPlacement()));
	}

	@Test
	public void test8() {
		assertEquals(tfplacement1.hashCode(), tfplacement2.hashCode());
		assertNotEquals(tfplacement1.hashCode(), new TfPlacement().hashCode());
	}
}
