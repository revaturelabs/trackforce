package com.revature.dao;
import com.revature.criteria.GraphedCriteriaResult;
import com.revature.entity.TfCurriculum;
import java.util.List;

public interface CurriculumDao {
	List<TfCurriculum> getAllCurriculums();
	List<GraphedCriteriaResult> getUnmapped(int id);
}