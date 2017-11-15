package com.revature.utils;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfBatchLocation;
import com.revature.entity.TfClient;
import com.revature.entity.TfCurriculum;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfPlacement;
import com.revature.model.StatusInfo;

public class StatusInfoUtilTest {

	Method getStageBasedOnStatusInfosAndStatusIDMethod;
	List<TfAssociate> tfAssociates = new ArrayList<>();

	@BeforeClass
	public void beforeClass() {
		BigDecimal aBigDecimal = new BigDecimal(1);
		TfClient tfClient = new TfClient(aBigDecimal);
		TfCurriculum tfCurriculum = new TfCurriculum(aBigDecimal);
		TfBatch tfBatch = new TfBatch(aBigDecimal, new TfBatchLocation(), tfCurriculum, "Python", new Timestamp(1),
				new Timestamp(1), new HashSet<TfAssociate>());

		tfAssociates = new ArrayList<>();
		TfAssociate tfAssociate = new TfAssociate(aBigDecimal, tfBatch, new TfMarketingStatus(aBigDecimal), tfClient,
				new TfEndClient(), "John", "Doe", new HashSet<TfInterview>(), new HashSet<TfPlacement>());
		tfAssociates.add(tfAssociate);

		StatusInfoUtil.class.getDeclaredMethods();
		try {
			getStageBasedOnStatusInfosAndStatusIDMethod = StatusInfoUtil.class
					.getDeclaredMethod("getStageBasedOnStatusInfosAndStatusID", List.class, int.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		getStageBasedOnStatusInfosAndStatusIDMethod.setAccessible(true);
	}

	@AfterClass
	public void afterClass() {
		StatusInfoUtil.clearMaps();
	}

	/**
	 * Test of private method that makes maps for a list of StatusInfo and a
	 * statusID
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void getStageBasedOnStatusInfosAndStatusIDTest() {
		try {
			List<StatusInfo> statusInfos = new ArrayList<>();
			statusInfos.add(new StatusInfo());
			List<Map<String, Object>> maps = (List<Map<String, Object>>) getStageBasedOnStatusInfosAndStatusIDMethod
					.invoke(null, statusInfos, 5);
			Map<String, Object> map = maps.get(0);
			String name = (String) map.get("name");
			assertNotNull(name);
			int count = (int) map.get("count");
			assertNotNull(count);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void checkStoredObjectsHaveDataAfterUpdateStatusInfoFromAssociates() {
		StatusInfoUtil.updateStatusInfoFromAssociates(tfAssociates);
		StatusInfo allAssociatesStatusInfo = StatusInfoUtil.getAllAssociatesStatusInfo();
		List<StatusInfo> clientsStatusInfo = StatusInfoUtil.getSpecificClientStatusInfoAsList();
		List<StatusInfo> curriculumsStatusInfo = StatusInfoUtil.getSpecificCurriculumStatusInfoAsList();
		
		allAssociatesStatusInfo.setName("");
		assertNotEquals(allAssociatesStatusInfo, new StatusInfo());
		assertFalse(clientsStatusInfo.isEmpty());
		assertFalse(curriculumsStatusInfo.isEmpty());
	}

}
