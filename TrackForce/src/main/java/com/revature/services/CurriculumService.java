package com.revature.services;

import java.util.List;

import com.revature.dao.CurriculumDao;
import com.revature.dao.CurriculumDaoImpl;
import com.revature.entity.TfCurriculum;

public class CurriculumService{

	private static CurriculumDao dao = new CurriculumDaoImpl();
	private CurriculumService() {};

	public static List<TfCurriculum> getAllCurriculums(){
		return dao.getAllCurriculums();
	}
}
