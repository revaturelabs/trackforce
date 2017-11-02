package com.revature.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.entity.TfCurriculum;
import com.revature.utils.HibernateUtil;

public class CurriculumDaoHibernate implements CurriculumDao {

    @Override
    public String getCurriculumName(int curriculumID) {
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.openSession();
        
        String hql = "select cirriculum.name FROM com.revature.model.Cirriculum cirriculum WHERE cirriculum.cirriculumid = :curriculumID";
        TypedQuery<TfCurriculum> query = session.createQuery(hql);
        query.setParameter("curriculumID", curriculumID);
        TfCurriculum curriculum = query.getSingleResult();
        
        return curriculum.getTfCurriculumName();
    }

    @Override
    public List<TfCurriculum> getAllCurriculums(){
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.openSession();
        
        String hql = "FROM com.revature.model.Cirriculum cirriculum";
        Query query = session.createQuery(hql);
        List<TfCurriculum> cirriculums = query.list();
        
        return cirriculums;
        
    }
}
