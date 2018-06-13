package com.revature.dao;


import java.util.Date;
import java.util.List;

import com.revature.request.model.AssociatesWithTech;


public interface PredictionDao {

	
	public List<AssociatesWithTech> getTotalAssociatesByTechBetweenDates(Date afterMe, Date beforeMe);
}
