package com.revature.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;
import com.revature.utils.jdbcUtil;

public class UserDaoImpl implements UserDAO {

    @Override
    public TfUser getUser(String username) {
        TfUser user;
        SessionFactory sessionFactory = HibernateUtil.getSession();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TfUser> criteriaQuery = builder.createQuery(TfUser.class);

            Root<TfUser> root = criteriaQuery.from(TfUser.class);

            criteriaQuery.select(root).where(builder.equal(root.get("tfUserUsername"), username));

            Query<TfUser> query = session.createQuery(criteriaQuery);

            try {
                user = query.getSingleResult();
                if (user.getTfRole() != null) {
                    if (user.getTfRole().getTfRoleName() != null) {
                        Hibernate.initialize(user.getTfRole().getTfRoleName());
                    }
                }

            } catch (NoResultException nre) {
                user = new TfUser();
            }
        }
        return user;
    }

    @Override
    public String getUserHash(TfUser user) {

        String hashpass = new String();

        Connection connection = null;
        Statement stmt = null;

        try {
            connection = jdbcUtil.getConnection();

            stmt = connection.createStatement();

            String sql = "SELECT * FROM TF_USER WHERE TF_USER_ID=" + user.getTfUserId();

            try (ResultSet rs = stmt.executeQuery(sql)) {
                if (rs.next()) {
                    hashpass = rs.getString("TF_HASHPASSWORD");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return hashpass;
    }
}
