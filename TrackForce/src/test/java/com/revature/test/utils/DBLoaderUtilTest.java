package com.revature.test.utils;

import com.revature.dao.*;
import com.revature.utils.DBLoaderUtil;
import com.revature.utils.DBUtil;
import com.revature.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class DBLoaderUtilTest {

    @Mock
    private AssociateDaoHibernate mockAssociateDao;
    @Mock
    private DBLoaderUtil loaderUtil;

    private Session session;
    private SessionFactory sessionFactory;

    @BeforeSuite
    public void setupHsqlDb() throws IOException, SQLException, ClassNotFoundException {
        sessionFactory = DBUtil.getTestSessionFactory();
        MockitoAnnotations.initMocks(this);
        Mockito.when(this.mockAssociateDao.getAssociates(Matchers.any(Session.class)))
                .thenReturn(new HashMap<>());

        /*
        Mockito.doNothing().when(loaderUtil).populateBatch(
                Matchers.any(Integer.class),
        );
        */

        /*
        public void populateBatch(Integer i, String string, LocalDate of, LocalDate of2, Integer j, Integer k,
			Session session) {
		*/

        Mockito.doNothing().when(loaderUtil).populateAssociate(
                Matchers.any(Integer.class), Matchers.anyString(), Matchers.anyString(), Matchers.any(Integer.class),
                Matchers.any(Integer.class), Matchers.any(Integer.class), Matchers.any(Integer.class),
                Matchers.any(Session.class)
        );
    }

    @AfterSuite
    public void after() {
        HibernateUtil.shutdown();
    }

    @BeforeTest
    public void beginTransactionToke () {
        this.session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterTest
    public void rollbackTransactionToke() {
        //this.session.getTransaction().rollback();
        this.session.close();
    }

    @Test
    public void testTruncateDB() throws Exception {
        mockAssociateDao.getAssociates(session);
    }

    @Test
    public void testPopulateDB() throws Exception {
    }

    @Test
    public void testPopulateDBSF() throws Exception {
    }

    @Test
    public void testPopulateBatch() throws Exception {
    }

    @Test
    public void testPopulatePlacement() throws Exception {
    }

    @Test
    public void testPopulateInterview() throws Exception {
    }

    @Test
    public void testPopulateAssociate() throws Exception {
    }

    @Test
    public void testPopulateBatchLocation() throws Exception {
    }

    @Test
    public void testPopulateMarketingStatus() throws Exception {
    }

    @Test
    public void testPopulateCurriculum() throws Exception {
    }

    @Test
    public void testPopulateInterviewType() throws Exception {
    }

    @Test
    public void testPopulateClient() throws Exception {
    }

    @Test
    public void testPopulateEndClient() throws Exception {
    }
}