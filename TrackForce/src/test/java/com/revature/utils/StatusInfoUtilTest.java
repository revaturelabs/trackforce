package com.revature.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.services.ClientResource;

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

	@Test
	public void getStageBasedOnStatusInfosAndStatusIDTest() {
		System.out.println("The method: ");
		try {
			System.out.println(getStageBasedOnStatusInfosAndStatusIDMethod.invoke(null, new ArrayList<>(), 5));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void tempTest() {

		ClientResource clientResource = new ClientResource();

		List<Map<String, Object>> maps = (List<Map<String, Object>>) clientResource.getAllClientsWithAssociates().getEntity();

		for(Map<String, Object> map : maps) {

			System.out.print(map.get("id") + " ");

			System.out.println(map.get("name"));

		}

	}

	

	@SuppressWarnings("unchecked")
	@Test
	public void tempTest2() {

		System.out.println();

		

		ClientResource clientResource = new ClientResource();

		List<Map<String, Object>> maps = (List<Map<String, Object>>) clientResource.getAllClients().getEntity();

		for(Map<String, Object> map : maps) {

			System.out.print(map.get("id") + " ");

			System.out.println(map.get("name"));

		}

	}
}
