package com.revature.services;

import java.io.IOException;
import java.util.Collection;
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