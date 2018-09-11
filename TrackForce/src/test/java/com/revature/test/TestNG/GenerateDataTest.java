package com.revature.test.TestNG;

import org.testng.annotations.Test;

import com.revature.application.GenerateData;

public class GenerateDataTest {

	@Test
	void testGetRandomString() {
		String result = GenerateData.getRandomString();
		assert(result != null);
	}
	
	@Test
	void testGetRandomInt() {
		int result;
		result = GenerateData.getRandomInt(0, 1000000);
		assert(result != 0);
	}
	
	@Test
	void testConstructGenerateData() {
		GenerateData result = new GenerateData();
		assert(result != null);
	}
	
}
