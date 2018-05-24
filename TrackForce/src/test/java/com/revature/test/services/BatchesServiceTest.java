package com.revature.test.services;

import com.revature.dao.BatchDao;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.services.BatchesService;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.*;

public class BatchesServiceTest {
	@Mock
	private BatchDao mockBatchDao;
	private BatchesService batchService;
	private AssociateInfo assoc1, assoc2, assoc3;
	private BatchInfo batch1, batch2, batch3;
	private Set<BatchInfo> batchSet;
	private List<BatchInfo> batchList;
	private Set<AssociateInfo> associateSet;

	@BeforeClass
	public void beforeClass() throws IOException {
		MockitoAnnotations.initMocks(this);

		// creates 3 associates
//		Integer id, String firstName, String lastName, String marketingStatus, String client,
//		String batchName, String curriculumName, long startDate
		assoc1 = new AssociateInfo(1, "Ian", "B", "s1", "client1", "b1", "jta", 40418L);
		assoc2 = new AssociateInfo(2, "Rich ", "Park", "s1", "client2", "b1", "jta", 40418L);
		assoc3 = new AssociateInfo(2, "Cuong ", "Nguyen", "s1", "client3", "b1", "jta", 40418L);

		associateSet = new TreeSet<>();
		associateSet.add(assoc1);

		batch1 = new BatchInfo(1, "b1", "c1", "Reston", "Jan", "Feb");
		batch1.setAssociates(new HashSet<>(Arrays.asList(assoc1)));

		batch2 = new BatchInfo(2, "b2", "c2", "Reston", "Jan", "Feb");
		batch2.setAssociates(new HashSet<>(Arrays.asList(assoc2)));

		batch3 = new BatchInfo(3, "b3", "c3", "Florida", "Mar", "June");
		batch3.setAssociates(new HashSet<>(Arrays.asList(assoc3)));

		batchList = new ArrayList<>();
		batchList.add(batch1);
		batchList.add(batch2);
		batchList.add(batch3);

		batchSet = new TreeSet<>();
		batchSet.add(batch1);

		Mockito.when(mockBatchDao.getAllBatches()).thenReturn(batchSet);
		Mockito.when(mockBatchDao.getBatchesSortedByDate()).thenReturn(batchList);
		Mockito.when(mockBatchDao.getBatchById(Matchers.anyInt())).thenReturn(batch1);
		Mockito.when(mockBatchDao.getBatchAssociates(Matchers.anyInt())).thenReturn(associateSet);
	}

	@BeforeMethod
	public void beforeEach() {
		batchService = new BatchesService(mockBatchDao);
	}

	@Test(enabled = true)
	public void testGetBatches() throws Exception {
		List<BatchInfo> results = batchService.getBatches(new Long(50), new Long(250));
		List<BatchInfo> comparator = new ArrayList<>();
		comparator.add(batch1);
		comparator.add(batch2);
//		Assert.assertEquals(comparator, results);
	}
}