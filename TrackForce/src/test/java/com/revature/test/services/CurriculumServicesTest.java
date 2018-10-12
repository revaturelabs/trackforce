package com.revature.test.services;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.daoimpl.CurriculumDaoImpl;
import com.revature.entity.TfCurriculum;
import com.revature.services.CurriculumService;
import com.revature.test.utils.Log;

public class CurriculumServicesTest {

	private CurriculumService service;
	private Properties props;
	
	@BeforeClass
	public void initialize() {
		service = new CurriculumService(new CurriculumDaoImpl());
		props = new Properties();

		try {
			FileInputStream propFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\database_entries.properties");
			props.load(propFile);
			propFile.close();
		} catch(FileNotFoundException e) {
			Log.Log.error(e.getMessage());
		} catch (IOException e) {
			Log.Log.error(e.getMessage());
		}
	}

	@Test
	public void testCurriculumGetAll() {

		List<TfCurriculum> allCurriculum = new ArrayList<TfCurriculum>();
		allCurriculum.addAll(service.getAllCurriculums());
		
		assertNotNull(allCurriculum);
		assertFalse(allCurriculum.isEmpty());
	}

	@Test
	public void testCurriculumGetUnmapped() {
		
		assertNotNull(service.getUnmappedInfo(Integer.parseInt(props.getProperty("circUnmappedNum"))));
		assertFalse(service.getUnmappedInfo(Integer.parseInt(props.getProperty("circUnmappedNum"))).isEmpty());
	}
}