package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.revature.model.CurriculumInfo;

public interface CurriculumDao {

	public Map<BigDecimal, CurriculumInfo> fetchCurriculums(Session session) throws IOException;
}
