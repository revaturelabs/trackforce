package com.revature.utils;

import static org.testng.Assert.assertNotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.model.StatusInfo;

public class StatusInfoUtilTest {

	Method getStageBasedOnStatusInfosAndStatusIDMethod;

	@BeforeClass
	public void beforeClass() {
		StatusInfoUtil.class.getDeclaredMethods();
		try {
			getStageBasedOnStatusInfosAndStatusIDMethod = StatusInfoUtil.class
					.getDeclaredMethod("getStageBasedOnStatusInfosAndStatusID", List.class, int.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		getStageBasedOnStatusInfosAndStatusIDMethod.setAccessible(true);
	}

	/**
	 * Test of private method that makes maps for a list of StatusInfo and a
	 * statusID
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void getStageBasedOnStatusInfosAndStatusIDTest() {
		System.out.println("The method: ");
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
}
