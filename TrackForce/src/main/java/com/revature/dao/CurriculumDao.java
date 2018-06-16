package com.revature.dao;

import java.util.List;

import com.revature.criteria.UnmappedCriteriaResult;
import com.revature.entity.TfCurriculum;

public interface CurriculumDao {
	public List<TfCurriculum> getAllCurriculums();

	List<UnmappedCriteriaResult> getUnmapped(int id);
}
