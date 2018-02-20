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

	
	List getTotalAssociatesByTechBetweenDates(Date afterMe, Date beforeMe);
}
