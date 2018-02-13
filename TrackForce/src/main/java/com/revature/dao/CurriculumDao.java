package com.revature.dao;

import java.util.Map;
import java.util.Set;

import com.revature.model.CurriculumInfo;

public interface CurriculumDao {

	public Map<Integer, CurriculumInfo> getAllCurriculums();
	public Set<CurriculumInfo> getCurriculaFromCache();
}
