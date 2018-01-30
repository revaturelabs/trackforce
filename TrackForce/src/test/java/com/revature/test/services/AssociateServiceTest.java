package com.revature.test.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.test.BaseTest;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.*;

import com.revature.model.AssociateInfo;
import com.revature.services.AssociateService;

public class AssociateServiceTest extends BaseTest {
    private AssociateService associateService;
    private Session session;

    @BeforeTest
    public void initDb() throws SQLException, IOException {
        resetCaches();
    }

    @BeforeMethod
    public void openSessionInitAssocService() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        associateService = new AssociateService(new AssociateDaoHibernate(), sessionFactory);
    }

    @AfterMethod
    public void rollbackChanges() {
        rollbackAndClose(session);
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