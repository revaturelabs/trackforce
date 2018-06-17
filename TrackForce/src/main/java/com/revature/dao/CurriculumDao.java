package com.revature.dao;

import java.util.List;

import com.revature.criteria.GraphedCriteriaResult;
import com.revature.entity.TfCurriculum;

public interface CurriculumDao {
	public List<TfCurriculum> getAllCurriculums();

	List<GraphedCriteriaResult> getUnmapped(int id);
}
