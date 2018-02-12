package com.revature.dao;

import java.util.Map;

import com.revature.model.CurriculumInfo;

public interface CurriculumDao {

	public Map<Integer, CurriculumInfo> getAllCurriculums();
}
