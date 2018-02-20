package com.revature.services;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.revature.dao.PredictionDao;
import com.revature.dao.PredictionDaoImpl;

public class PredictionService implements Service{

	
    private PredictionDao PredictionDao;

    public PredictionService() {
        this.PredictionDao = new PredictionDaoImpl();
    }

    public List getAvailableAssociatesByTech(Date afterMe,Date beforeMe) {
    	return this.PredictionDao.getTotalAssociatesByTechBetweenDates(afterMe, beforeMe);
    }
    
	@Override
	public void execute() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> Collection<T> read(String... args) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}

