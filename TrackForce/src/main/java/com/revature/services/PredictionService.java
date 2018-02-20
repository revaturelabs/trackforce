package com.revature.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.revature.dao.PredictionDao;
import com.revature.dao.PredictionDaoImpl;

public class PredictionService{

	
    private PredictionDao PredictionDao;

    public PredictionService() {
        this.PredictionDao = new PredictionDaoImpl();
    }

    public List getAllBatchTechs() throws IOException {
    	return this.PredictionDao.GET_ALL_TECH_PER_BATCH();
    }
    
    public List getAssociateCountByTechId(int tech_id) {
    	return this.PredictionDao.GET_COUNT_OF_ALL_BATCH_PER_TECH(tech_id);
    }
    
    public List getAssociateCountByTechName(String tech_name) {
    	return this.PredictionDao.GET_COUNT_OF_ALL_BATCH_PER_TECH(tech_name);
    }    
    
    public List getAvailableAssociatesByTech(Date date1, Date date2, String techname) {
    	return this.PredictionDao.GET_COUNT_OF_ALL_BATCH_PER_DATE(date1, date2, techname);
    }
    
}

