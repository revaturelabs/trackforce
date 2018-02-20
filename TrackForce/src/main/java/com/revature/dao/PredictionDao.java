package com.revature.dao;


import java.util.Date;
import java.util.List;


public interface PredictionDao {

	
	List getTotalAssociatesByTechBetweenDates(Date afterMe, Date beforeMe);
}
