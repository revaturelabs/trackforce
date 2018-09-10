package com.revature.test.dao;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.criteria.GraphedCriteriaResult;
import com.revature.daoimpl.CurriculumDaoImpl;
import com.revature.entity.TfCurriculum;
import com.revature.test.utils.Log;

public class CurriculumDAOTest {
	
	private CurriculumDaoImpl dao;
	private Properties props;
	
	@BeforeClass
	public void initialize() {
		dao = new CurriculumDaoImpl();
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
	public void testCurriculumDAOGetAll() {
		List<TfCurriculum> list = dao.getAllCurriculums();
		assertEquals(list.size(), Integer.parseInt(props.getProperty("curriculum_total")));
	}
	
	@Test
	public void testCurriculumDAOGetUnmapped() {
		List<GraphedCriteriaResult> list = dao.getUnmapped(Integer.parseInt(props.getProperty("curriculum_java")));
		assertEquals(list.get(0).getName(), "Java");
		assertEquals(list.get(0).getCount(), Long.parseLong(props.getProperty("curriculum_java_count")));
		
	}
}
