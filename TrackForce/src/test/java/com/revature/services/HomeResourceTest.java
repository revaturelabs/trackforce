package com.revature.services;

import static org.testng.Assert.assertNotNull;

import javax.ws.rs.core.Response;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.model.StatusInfo;

import java.io.IOException;

public class HomeResourceTest {

	HomeResource hRes = new HomeResource();
	
	@DataProvider(name="statusID")
	public int[] statusID() {
		int[] statusIDs = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		return statusIDs;
	}
	
	@Test
	public void getMappedAndUnmappedInfo() throws IOException {
		StatusInfo result = hRes.getMappedAndUnmappedInfo();
		assertNotNull(result);
	}

	@Test(enabled=false)
	public void getClientsByStatus(int statusID) throws IOException {
		Response result = hRes.getClientsByStatus(statusID);
		assertNotNull(result);
	}
	
	@Test(enabled=false)
	public void getCurriculumByStatus(int statusID) throws IOException {
		Response result = hRes.getCurriculumsByStatus(statusID);
		assertNotNull(result);
	}
}