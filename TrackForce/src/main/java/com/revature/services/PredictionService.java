package com.revature.services;

import java.util.Date;
import java.util.List;

import com.revature.dao.PredictionDao;
import com.revature.dao.PredictionDaoImpl;

public class PredictionService{

	
    private PredictionDao predictionDao;

    public PredictionService() {
        this.predictionDao = new PredictionDaoImpl();
    }

    public List getAvailableAssociatesByTech(Date afterMe,Date beforeMe) {
    	return this.predictionDao.getTotalAssociatesByTechBetweenDates(afterMe, beforeMe);
    }
    
}

