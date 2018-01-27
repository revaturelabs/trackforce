package com.revature.test.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.services.ClientResource;
import com.revature.services.MarketingStatusService;
import com.revature.utils.HibernateUtil;
import com.revature.utils.TestDBLoaderUtil;
import com.revature.utils.TestHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.testng.Assert;
import org.testng.annotations.*;

import com.revature.model.AssociateInfo;
import com.revature.services.AssociateService;

public class AssociateServiceTest {
    private AssociateService associateService;
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @BeforeTest
    public void initCaches() throws IOException, SQLException {
        sessionFactory = TestHibernateUtil.getSessionFactory();
        new TestDBLoaderUtil().populate();
        new AssociateService().execute();
        new ClientResource().execute();
        new MarketingStatusService().execute();
    }

    @AfterTest
    public void quitHibernate() {
        HibernateUtil.shutdown();
    }

    @BeforeMethod
    public void openSessionInitAssocService() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        associateService = new AssociateService(new AssociateDaoHibernate(), sessionFactory);
    }

    @AfterMethod
    public void rollbackChanges() {
        try {
            transaction.rollback();
        }
        catch(Exception ignored){}

        try {
            session.close();
        }
        catch(Exception ignored){}
    }

    @Test
    public void testGetAssociatePositive() throws IOException {
        BigDecimal id = new BigDecimal(1);
        AssociateInfo associate = associateService.getAssociate(id);
        Assert.assertNotNull(associate);
    }

    @Test(expectedExceptions = IOException.class)
    public void testgetAssociateNegative() throws IOException {
        BigDecimal bigdecimal = new BigDecimal(-25);
        associateService.getAssociate(bigdecimal);
    }

    @Test
    public void testUpdateAssociatePositive() throws IOException {
        associateService.updateAssociate("16", "3", "2");

        AssociateInfo associateInfo = associateService.getAssociate(new BigDecimal(16));

        Assert.assertEquals(associateInfo.getMsid().intValueExact(), 3);
        Assert.assertEquals(associateInfo.getMarketingStatus(), "MAPPED: SELECTED");
        Assert.assertEquals(associateInfo.getClid(), new BigDecimal(2));
        Assert.assertEquals(associateInfo.getClient(), "Infosys");
    }

    @Test
    public void testUpdateAssociateNegative() throws IOException {
        associateService.updateAssociate("15", "-1", "-1");
        AssociateInfo associateInfo = associateService.getAssociate(new BigDecimal(15));
        Assert.assertEquals(associateInfo.getId().intValueExact(), 15);
        Assert.assertTrue(associateInfo.getClid().intValueExact() > -1);
        Assert.assertNotNull(associateInfo.getMarketingStatus());
        Assert.assertNotEquals(associateInfo.getClient(), "None");
    }
}