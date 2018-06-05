package com.revature.dao;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfUser;
import com.revature.request.model.CreateAssociateModel;
import com.revature.request.model.CreateUserModel;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PasswordStorage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

import static com.revature.utils.LogUtil.logger;

public class UserDaoImpl implements UserDAO {

    private AssociateDao associateDao;

    public UserDaoImpl() {
        associateDao = AssociateDaoHibernate.getInstance();
    }

    public UserDaoImpl(AssociateDao dao) {		// testing purposes
        associateDao = dao;
    }

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
        logger.info("Create associate hit");
        String password;
        Session session = HibernateUtil.getSession();
        Transaction t1 = null;
        try {
            password = PasswordStorage.createHash(newAssociate.getPassword());
            t1 = session.beginTransaction();
            logger.info("Begin transaction");
            TfAssociate tfa = new TfAssociate();
            tfa.setTfAssociateFirstName(newAssociate.getFname());
            tfa.setTfAssociateLastName(newAssociate.getLname());
            tfa.setIsApproved(0);
            logger.info("approved process");
            String sql = "SELECT MAX(tf_associate_id) FROM admin.tf_associate";
            Query<?> q = session.createNativeQuery(sql);
            BigDecimal max = (BigDecimal) q.getSingleResult();
            Integer id = Integer.parseInt(max.toBigInteger().toString()) + 1;
            tfa.setTfAssociateId(id);
            session.saveOrUpdate(tfa);           //Saves current changes to not get null user in newUser creation.
            logger.info("session save");
            TfUser newUser = new TfUser(tfa, newAssociate.getUsername(), password);
            session.saveOrUpdate(newUser);
            t1.commit();

            associateDao.cacheAllAssociates();
            logger.info("Associate successfully created");

            return true;

        } catch (HibernateException e) {
            if (t1 != null){
                logger.info("Hibernate exception");
                t1.rollback();
            }
            LogUtil.logger.error(e);
        }catch(Exception e){
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
