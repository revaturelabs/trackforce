package com.revature.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;

import com.revature.entity.TfClient;
import com.revature.entity.TfTech;
import com.revature.model.ClientInfo;

public interface PredictionDao {

	//@return a list of batch names and technologies for each batch
  List  GET_ALL_TECH_PER_BATCH() throws IOException;
	
	//@return a list of batch_name and a count of associates for a given technology 
	List GET_COUNT_OF_ALL_BATCH_PER_TECH(int tech_id);
	List GET_COUNT_OF_ALL_BATCH_PER_TECH(String techname);
	
	
	/*@return a list of batch names, and an account of associates. The associate account would
	 need to be added to get the correct sum of all associates within a given time frame that has a
	specific skill.
	This method takes two dates and the technology name  */
	
	List GET_COUNT_OF_ALL_BATCH_PER_DATE(Date date1, Date date2, String techname);
	
}
