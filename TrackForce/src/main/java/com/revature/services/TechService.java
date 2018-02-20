package com.revature.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Set;

import org.hibernate.HibernateException;

import com.revature.dao.TechDao;
import com.revature.dao.TechDaoHibernate;
import com.revature.model.TechInfo;

public class TechService{

    private TechDao TechDao;

    public TechService() {
        this.TechDao = new TechDaoHibernate();
    }

    /**
     * injectable dao for easier testing
     *
     * @param TechDao
     */
    public TechService(TechDao TechDao) {
        this.TechDao = TechDao;
    }

	public List<TechInfo> getTechs() throws HibernateException, IOException{
		return new ArrayList<>(TechDao.getAllTechs().values());
	}

}
