package com.revature.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class TestDBLoaderUtil {
    private DBPopulaterUtil populater;

    /**
     * injectable constructor for testing
     *
     * @param populater
     */
    public TestDBLoaderUtil(DBPopulaterUtil populater) {
        this.populater = populater;
    }

    public TestDBLoaderUtil() {
        this.populater = new DBPopulaterUtil();
    }

    /**
     * load test database with some values to test with, so we don't
     * have to run the much larger DBLoaderUtil (in main)
     *
     * @throws HibernateException
     * @throws IOException
     */
    public void populate() throws HibernateException, SQLException {

        Session session = TestHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // session.createNativeQuery("ALTER SCHEMA PUBLIC RENAME TO ADMIN").executeUpdate();
        // session.createNativeQuery("SET DATABASE SQL SYNTAX ORA TRUE").executeUpdate();
        for (Object o : session.createNativeQuery("select * from INFORMATION_SCHEMA.SYSTEM_TABLES WHERE TABLE_SCHEM = 'ADMIN'").list()) {
            Object[] arr = (Object[]) o;
            System.out.print("[ ");
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i]);
                if (i + 1 < arr.length) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }

        populater.populateEndClient(1, "Accenture", session);
        populater.populateEndClient(2, "Infosys", session);

        populater.populateClient(1, "Accenture", session);
        populater.populateClient(2, "Infosys", session);

        populater.populateInterviewType(1, "Phone", session);
        populater.populateInterviewType(2, "Online", session);

        populater.populateCurriculum(1, "JTA", session);
        populater.populateCurriculum(2, "Java", session);
        populater.populateCurriculum(3, ".Net", session);
        populater.populateCurriculum(4, "PEGA", session);

        // INSERT DUMMY VALUES IntegerO TF_MARKETING_STATUS
        populater.populateMarketingStatus(1, "MAPPED: TRAINING", session);
        populater.populateMarketingStatus(2, "MAPPED: RESERVED", session);
        populater.populateMarketingStatus(3, "MAPPED: SELECTED", session);
        populater.populateMarketingStatus(4, "MAPPED: CONFIRMED", session);
        populater.populateMarketingStatus(5, "MAPPED: DEPLOYED", session);
        populater.populateMarketingStatus(6, "UNMAPPED: TRAINING", session);
        populater.populateMarketingStatus(7, "UNMAPPED: OPEN", session);
        populater.populateMarketingStatus(8, "UNMAPPED: SELECTED", session);
        populater.populateMarketingStatus(9, "UNMAPPED: CONFIRMED", session);
        populater.populateMarketingStatus(10, "UNMAPPED: DEPLOYED", session);
        populater.populateMarketingStatus(11, "DIRECTLY PLACED", session);
        populater.populateMarketingStatus(12, "TERMINATED", session);

        populater.populateBatchLocation(1, "Revature LLC, 11730 Plaza America Drive, 2nd Floor | Reston, VA 20190", session);
        populater.populateBatchLocation(2, "UMUC", session);
        populater.populateBatchLocation(3, "USF", session);

        populater.populateBatch(1, "1712 Dec04 AP, USF", LocalDate.of(2017, 12, 4), LocalDate.of(2018, 2, 16), 2, 3, session);
        populater.populateBatch(2, "1710 Oct09 PEGA", LocalDate.of(2017, 10, 9), LocalDate.of(2017, 12, 15), 4, 1, session);

        populater.populateAssociate(1, "Frank", "Hind", 1, 1, 1, 1, session);
        populater.populateAssociate(2, "Thomas", "Page", 1, 1, 1, 1, session);
        populater.populateAssociate(3, "Lucas", "Normand", 1, 1, 1, 1, session);
        populater.populateAssociate(4, "Jhonnie", "Cole", 1, 1, 1, 1, session);
        populater.populateAssociate(5, "Ramona", "Reyes", 1, 1, 1, 1, session);

        populater.populateAssociate(13, "Trevor", "Hampton", 1, 2, 2, 2, session);
        populater.populateAssociate(14, "Jennie", "Hudson", 1, 2, 2, 2, session);
        populater.populateAssociate(15, "David", "Haynes", 1, 2, 2, 2, session);
        populater.populateAssociate(16, "Ira", "Mullins", 1, 2, 2, 2, session);
        populater.populateAssociate(17, "Alexandra", "Mitchell", 1, 2, 2, 2, session);

        populater.populateRole(1, "Admin", session);
        populater.populateRole(2, "Manager", session);
        populater.populateRole(3, "Vice President", session);
        populater.populateRole(4, "Associate", session);

        populater.populateUser("sha1:64000:18:zBfcx3rxxYev6SuYjw/EoTzwwhDW0+5I:TE/5QDShUo2DpVtwM1wfpnmD", "TestAdmin", 1, session);
        populater.populateUser("sha1:64000:18:/fW6R/5plhg/bmBGJHwZ706zkWS3+gu2:G1DIcP3u0KXQnzXBsL/6zdjj", "TestManager", 2, session);
        populater.populateUser("sha1:64000:18:BId387PD/2CaEcCaISnf6GNHcfdSFM9B:nkKS54PqDi5hSoJa+6sjxW85", "TestVicePresident", 3, session);

        session.flush();
        tx.commit();
        session.close();
    }
}
