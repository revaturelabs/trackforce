package com.revature.test.utils;

import com.revature.dao.*;
import com.revature.utils.DBLoaderUtil;
import com.revature.utils.DBPopulaterUtil;
import com.revature.utils.TestDBUtil;
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
    private DBPopulaterUtil populaterUtilMock;

    @Mock
    private DBLoaderUtil loaderUtil;

    private Session session;
    private SessionFactory sessionFactory;

    @BeforeTest // once before any @test
    public void setupHsqlDb() throws IOException, SQLException, ClassNotFoundException {
        sessionFactory = TestDBUtil.getSessionFactory();
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

        loaderUtil = new DBLoaderUtil(populaterUtilMock);
        Mockito.doNothing().when(populaterUtilMock).populateAssociate(
                Matchers.any(Integer.class), Matchers.anyString(), Matchers.anyString(), Matchers.any(Integer.class),
                Matchers.any(Integer.class), Matchers.any(Integer.class), Matchers.any(Integer.class),
                Matchers.any(Session.class)
        );
    }

    @AfterTest
    public void after() {
        HibernateUtil.shutdown();
    }

    @BeforeMethod   // before each @test
    public void beginTransactionToke () {
        this.session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterMethod
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