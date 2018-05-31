package com.revature.dao;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfUser;
import com.revature.request.model.CreateAssociateModel;
import com.revature.request.model.CreateUserModel;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.revature.utils.LogUtil.logger;

public class UserDaoImpl implements UserDAO {


    public TfUser getUser(String username) {
        TfUser user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TfUser> criteriaQuery = builder.createQuery(TfUser.class);
            Root<TfUser> root = criteriaQuery.from(TfUser.class);
            criteriaQuery.select(root).where(builder.equal(root.get("tfUserUsername"), username));
            Query<TfUser> query = session.createQuery(criteriaQuery);
            user = query.getSingleResult();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            session.close();
        }
        return user;
    }

    //Currently legacy code that createAssociate was modeled after.
    public boolean createUser(CreateUserModel newUser) {
        String password;
        Transaction t1 = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            t1 = session.beginTransaction();
            password = PasswordStorage.createHash(newUser.getPassword());
            TfUser user = new TfUser(newUser.getRole(), newUser.getUsername(), password);
            session.save(user);
            return true;
        } catch (NullPointerException e) {
            if (t1 != null) {
                t1.rollback();
            }
            logger.error(e);
        } catch (Exception e) {
            if (t1 != null) {
                t1.rollback();
            }
            logger.error(e);
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean createAssociate(CreateAssociateModel newAssociate) {
        String password;
        Session session = HibernateUtil.getSession();
        Transaction t1 = null;
        try {
            t1 = session.beginTransaction();
            password = PasswordStorage.createHash(newAssociate.getPassword());
            TfAssociate newGuy = new TfAssociate();
            session.saveOrUpdate(newGuy);           //Saves current changes to not get null user in newUser creation.
            /**
             * BRIAN
             */
            TfUser newUser = new TfUser(newGuy, newAssociate.getUsername(), password);
            t1.commit();
            session.saveOrUpdate(newUser);
            logger.info("Associate successfully created");
            return true;
        } catch (Exception e) {
            if (t1 != null){
                t1.rollback();
            }
            LogUtil.logger.error(e.getStackTrace());
        } finally {
            session.close();
        }


        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TfUser> getAllUsers() {
        List<TfUser> user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("from com.revature.entity.TfUser").list();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            session.close();
        }
        return user;
    }
}
