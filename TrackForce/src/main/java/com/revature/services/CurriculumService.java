package com.revature.services;

import java.io.IOException;
import java.util.Set;


import org.hibernate.HibernateException;

import com.revature.dao.CurriculumDao;
import com.revature.dao.CurriculumDaoImpl;
import com.revature.model.CurriculumInfo;

public class CurriculumService{

    private CurriculumDao curriculumDao;

    public CurriculumService() {
        this.curriculumDao = new CurriculumDaoImpl();
    }

    /**
     * injectable dao for easier testing
     *
     * @param curriculumDao
     */
    public CurriculumService(CurriculumDao curriculumDao) {
        this.curriculumDao = curriculumDao;
    }

	public Set<CurriculumInfo> getCurriculums() throws HibernateException, IOException{
		return curriculumDao.getCurriculaFromCache();
	}
}
