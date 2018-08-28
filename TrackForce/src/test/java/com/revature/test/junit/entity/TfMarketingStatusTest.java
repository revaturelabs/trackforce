package com.revature.test.junit.entity;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfMarketingStatus;
import org.testng.annotations.Test;
import java.util.HashSet;
import java.util.Set;
import static org.testng.Assert.*;

/**
 * Tests to test basic getter and setter functionality for TfMarketingStatus
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfMarketingStatusTest {
	Set<TfAssociate> associates = new HashSet<>();

	TfMarketingStatus status1 = new TfMarketingStatus(1, "name", associates);
	TfMarketingStatus status2 = new TfMarketingStatus(1, "name", associates);
	TfMarketingStatus tfms = new TfMarketingStatus();

	@Test
	public void test1() {
		tfms.setAssociates(new HashSet<TfAssociate>());
		assertTrue(tfms.getAssociates() instanceof HashSet);
	}

	@Test
	public void test2() {
		tfms.setId(64);
		assertTrue(tfms.getId() == 64);
		assertFalse(tfms.getId() == 123);
	}

	@Test
	public void test3() {
		tfms.setName("MarketStatus");
		assertTrue(tfms.getName().equals("MarketStatus"));
		assertFalse(tfms.getName().equals("marketstatus"));
	}

	@Test
	public void test4() {
		assertTrue(status1.equals(status2));
		assertFalse(status1.equals(new TfMarketingStatus()));
	}

	@Test
	public void test5() {
		assertEquals(status1.hashCode(), status2.hashCode());
		assertNotEquals(status1.hashCode(), new TfMarketingStatus().hashCode());
	}

}
