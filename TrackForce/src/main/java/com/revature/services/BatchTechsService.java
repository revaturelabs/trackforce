package com.revature.services;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.revature.dao.JunctionDao;
import com.revature.dao.JunctionDaoImpl;

public class BatchTechsService implements Service{

	
    private JunctionDao JunctionDao;

    public BatchTechsService() {
        this.JunctionDao = new JunctionDaoImpl();
    }

    public List getAllBatchTechs() throws IOException {
    	return this.JunctionDao.GET_ALL_TECH_PER_BATCH();
    }
    
    public List getAssociateCountByTechId(int tech_id) {
    	return this.JunctionDao.GET_COUNT_OF_ALL_BATCH_PER_TECH(tech_id);
    }
    
    public List getAssociateCountByTechName(String tech_name) {
    	return this.JunctionDao.GET_COUNT_OF_ALL_BATCH_PER_TECH(tech_name);
    }    
    
    public List getAvailableAssociatesByTech(Date date1, Date date2, String techname) {
    	return this.JunctionDao.GET_COUNT_OF_ALL_BATCH_PER_DATE(date1, date2, techname);
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

