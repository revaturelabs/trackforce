package com.revature.test.resources;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response.Status;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.daoimpl.BatchDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.resources.BatchResource;
import com.revature.services.BatchService;

import io.jsonwebtoken.Claims;

public class BatchResourceTest {
	//More methods need implementing but will need to be done later.
	@Mock
	Claims payload;
	@Mock
	List<TfBatch> mockBatches;
	@Mock
	BatchService batchServe;
	@Mock
	TfBatch mockBatch;
	@Mock
	Timestamp startDate, endDate;
	@Mock
	Set<TfAssociate> mockAssociates;
	@Mock
	BatchDaoImpl mockImpl;
	
	
	@BeforeMethod
	protected void setUp() {
		MockitoAnnotations.initMocks(this);
		
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

	@Test
	public void getAllBatchesTest() {
		BatchResource bsMock = spy(new BatchResource());
		Long sDate = (long) 10000000;
		Long eDate = (long) 20000000;
		doReturn(batchServe).when(bsMock).getBatchService();
		when(batchServe.getAllBatches()).thenReturn(mockBatches);
		doReturn(payload).when(bsMock).jwtServiceProcessToken(anyString());
		when(payload.get("roleID")).thenReturn("1");
		when(mockBatches.size()).thenReturn(2);
		when(mockBatches.get(anyInt())).thenReturn(mockBatch);
		when(mockBatch.getStartDate()).thenReturn(startDate);
		when(mockBatch.getEndDate()).thenReturn(endDate);
		when(startDate.before(any(Date.class))).thenReturn(false);
		when(endDate.after(any(Date.class))).thenReturn(false);
		when(mockBatches.isEmpty()).thenReturn(false);
		
		assertEquals(bsMock.getAllBatches(sDate, eDate, "token").getStatusInfo(), Status.OK);;
		verify(startDate, times(2)).before(any(Date.class));
		verify(endDate, times(2)).after(any(Date.class));
		verify(mockBatches, never()).remove(anyInt());
	}
	
	@Test
	public void getAllBatchesForbiddenTest() {
		BatchResource bsMock = spy(new BatchResource());
		Long sDate = (long) 10000000;
		Long eDate = (long) 20000000;
		doReturn(batchServe).when(bsMock).getBatchService();
		when(batchServe.getAllBatches()).thenReturn(mockBatches);
		doReturn(payload).when(bsMock).jwtServiceProcessToken(anyString());
		when(payload.get("roleID")).thenReturn("5");
		when(mockBatches.size()).thenReturn(2);
		when(mockBatches.get(anyInt())).thenReturn(mockBatch);
		when(mockBatch.getStartDate()).thenReturn(startDate);
		when(mockBatch.getEndDate()).thenReturn(endDate);
		when(startDate.before(any(Date.class))).thenReturn(false);
		when(endDate.after(any(Date.class))).thenReturn(false);
		when(mockBatches.isEmpty()).thenReturn(false);
		
		assertEquals(bsMock.getAllBatches(sDate, eDate, "token").getStatusInfo(), Status.FORBIDDEN);;
		verify(startDate, never()).before(any(Date.class));
		verify(endDate, never()).after(any(Date.class));
		verify(mockBatches, never()).remove(anyInt());
	}
	
	@Test
	public void getBatchAssociatesTest() {
		BatchResource bsMock = spy(new BatchResource());
		doReturn(batchServe).when(bsMock).getBatchService();
		when(batchServe.getBatchById(anyInt())).thenReturn(mockBatch);
		when(mockBatch.getAssociates()).thenReturn(mockAssociates);
		when(mockAssociates.isEmpty()).thenReturn(false);
		doReturn(payload).when(bsMock).jwtServiceProcessToken(anyString());
		when(payload.get("roleID")).thenReturn("1");
		
		assertEquals(bsMock.getBatchAssociates(1, "token").getStatusInfo(), Status.OK);
	}
	
	@Test
	public void getBatchAssociatesUnauthorizedUserTest() {
		BatchResource bsMock = spy(new BatchResource());
		doReturn(batchServe).when(bsMock).getBatchService();
		when(batchServe.getBatchById(anyInt())).thenReturn(mockBatch);
		when(mockBatch.getAssociates()).thenReturn(mockAssociates);
		when(mockAssociates.isEmpty()).thenReturn(false);
		doReturn(payload).when(bsMock).jwtServiceProcessToken(anyString());
		when(payload.get("roleID")).thenReturn("5");
		
		assertEquals(bsMock.getBatchAssociates(1, "token").getStatusInfo(), Status.FORBIDDEN);
	}
	
	@Test
	public void getBatchAssociatesNoContentTest() {
		BatchResource bsMock = spy(new BatchResource());
		doReturn(batchServe).when(bsMock).getBatchService();
		when(batchServe.getBatchById(anyInt())).thenReturn(mockBatch);
		when(mockBatch.getAssociates()).thenReturn(mockAssociates);
		when(mockAssociates.isEmpty()).thenReturn(true);
		doReturn(payload).when(bsMock).jwtServiceProcessToken(anyString());
		when(payload.get("roleID")).thenReturn("1");
		
		assertEquals(bsMock.getBatchAssociates(1, "token").getStatusInfo(), Status.NO_CONTENT);
	}
	
	@Test
	public void getBatchDetailsTest() {
		BatchResource bsMock = spy(new BatchResource());
		Long sDate = (long) 10000000;
		Long eDate = (long) 20000000;
		doReturn(payload).when(bsMock).jwtServiceProcessToken(anyString());
		when(payload.get("roleID")).thenReturn("5");
		doReturn(mockImpl).when(bsMock).getBatchDaoImpl();
		when(mockImpl.getBatchesForPredictions(anyString(), any(Timestamp.class), any(Timestamp.class))).thenReturn(mockBatches);
		
		assertEquals(bsMock.getBatchDetails(sDate, eDate, "courseName", "token").getStatusInfo(), Status.FORBIDDEN);
	}
}
