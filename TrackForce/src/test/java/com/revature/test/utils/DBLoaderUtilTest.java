package com.revature.test.utils;

import com.revature.utils.DBLoaderUtil;
import com.revature.utils.DBPopulaterUtil;
import com.revature.utils.TestHibernateUtil;
import com.revature.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

public class DBLoaderUtilTest {

    private Session session;

    private SessionFactory sessionFactory;

    private DBLoaderUtil loaderUtil;

    @Mock
    private DBPopulaterUtil populaterUtilMock;

    @BeforeTest // once before any @test
    public void setupHsqlDb() throws SQLException {
        sessionFactory = TestHibernateUtil.getSessionFactory();

        MockitoAnnotations.initMocks(this);
        loaderUtil = new DBLoaderUtil(populaterUtilMock);
        // mock the populate methods to do nothing
        Mockito.doNothing().when(populaterUtilMock).truncateDB(any(Session.class));
        Mockito.doNothing().when(populaterUtilMock).populateUser(
                anyString(), anyString(), any(Integer.class), any(Session.class)
        );
        Mockito.doNothing().when(populaterUtilMock).populateRole(
                any(Integer.class), anyString(), any(Session.class)
        );
        Mockito.doNothing().when(populaterUtilMock).populateBatch(
                any(Integer.class), anyString(), any(LocalDate.class), any(LocalDate.class),
                any(Integer.class), any(Integer.class), any(Session.class)
        );
        Mockito.doNothing().when(populaterUtilMock).populatePlacement(
                any(Integer.class), any(LocalDate.class), any(LocalDate.class),
                any(Integer.class), any(Integer.class), any(Integer.class), any(Session.class)
        );
        Mockito.doNothing().when(populaterUtilMock).populateInterview(
                any(Integer.class), any(LocalDateTime.class), anyString(), any(Integer.class),
                any(Integer.class), any(Integer.class), any(Integer.class), any(Session.class)
        );
        Mockito.doNothing().when(populaterUtilMock).populateAssociate(
                any(Integer.class), anyString(), anyString(), any(Integer.class),
                any(Integer.class), any(Integer.class), any(Integer.class),
                any(Session.class)
        );
        Mockito.doNothing().when(populaterUtilMock).populateBatchLocation(
                any(Integer.class), anyString(), any(Session.class)
        );
        Mockito.doNothing().when(populaterUtilMock).populateMarketingStatus(
                any(Integer.class), anyString(), any(Session.class)
        );
        Mockito.doNothing().when(populaterUtilMock).populateCurriculum(
                any(Integer.class), anyString(), any(Session.class)
        );
        Mockito.doNothing().when(populaterUtilMock).populateInterviewType(
                any(Integer.class), anyString(), any(Session.class)
        );
        Mockito.doNothing().when(populaterUtilMock).populateClient(
                any(Integer.class), anyString(), any(Session.class)
        );
        Mockito.doNothing().when(populaterUtilMock).populateEndClient(
                any(Integer.class), anyString(), any(Session.class)
        );
    }

    @AfterTest
    public void afterAll() {
        HibernateUtil.shutdown();
    }

    @BeforeMethod   // before each @test
    public void beginTransactionToke() {
        this.session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterMethod
    public void rollbackTransactionToke() {
        this.session.getTransaction().rollback();
        this.session.close();
    }

    @Test
    public void testPopulateDB() throws IOException {
        loaderUtil.populateDB();
    }

    @Test
    public void testPopulateDBSF() throws IOException {
        loaderUtil.populateDBSF();
    }

     @Test
    public void testTruncateDB() throws IOException {
        loaderUtil.truncateDB();
    }
}