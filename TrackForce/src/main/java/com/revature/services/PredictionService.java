package com.revature.services;

import java.util.Date;
import java.util.List;

import com.revature.dao.PredictionDao;
import com.revature.dao.PredictionDaoImpl;

public class PredictionService{

	
    private PredictionDao PredictionDao;

    public PredictionService() {
        this.PredictionDao = new PredictionDaoImpl();
    }

    public List getAvailableAssociatesByTech(Date afterMe,Date beforeMe) {
    	return this.PredictionDao.getTotalAssociatesByTechBetweenDates(afterMe, beforeMe);
    }
    
}

