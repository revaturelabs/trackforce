package com.revature.dao;

import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;

import com.revature.utils.HibernateUtil;

public class DatabaseDAOImpl {
    public String deleteAll() {
        String message;
        Session session = HibernateUtil.getSession().openSession();

        try {
            StoredProcedureQuery query2 = session.createStoredProcedureQuery("admin.truncateAllDevTeam");
            query2.execute();
            message = "Database Emptied Successfully";
            return message;
        } catch (Exception e) {
            message = "Database Empty Error";
            return message;
        } finally {
            session.close();
        }
    }

    public String populate() {
        Session session = HibernateUtil.getSession().openSession();
        String message;
        try {
            StoredProcedureQuery query2 = session.createStoredProcedureQuery("admin.populateAllTables_PROC");
            query2.execute();
            message = "Database Population Successfull";
            return message;
        } catch (Exception e) {
            message = "Error: Data Exists";
            return message;
        } finally {
            session.close();
        }
    }

    public String populateSF() {
        Session session = HibernateUtil.getSession().openSession();
        String message;
        try {
            StoredProcedureQuery query2 = session.createStoredProcedureQuery("admin.populateAllTablesSF_PROC");
            query2.execute();
            message = "SF - Database Population Successfull";
            return message;
        } catch (Exception e) {
            message = "Error: Data Exists";
            return message;
        } finally {
            session.close();
        }
    }
}
