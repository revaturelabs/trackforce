package com.revature.dao;

import java.util.List;

import com.revature.entity.TfCurriculum;

public interface CurriculumDao {
    
    String getCurriculumName(int curriculumnID);
    List<TfCurriculum> getAllCurriculums();
}
