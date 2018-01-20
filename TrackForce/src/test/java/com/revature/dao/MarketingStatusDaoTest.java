package com.revature.dao;

import static org.testng.Assert.assertNotNull;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.*;

import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;

public class MarketingStatusDaoTest {


    MarketingStatusDaoHibernate msdao;
    Session session = null;

    /*
    Session session;
    Transaction tx;

    @BeforeClass
    public void beforeClass() throws HibernateException, IOException {
        session = HibernateUtil.getSession().openSession();
        tx = session.beginTransaction();
    }

    @AfterClass
    public void afterClass() {
        session.flush();
        tx.rollback();
        session.close();
    }
    */

    @BeforeTest
    public void beforeTest() {
        msdao = Mockito.mock(MarketingStatusDaoHibernate.class);
    }

    @DataProvider(name = "MarketingStatus")
    public static String[] marketingStatus() {
        String[] dp = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        return dp;
    }

    @Test(dataProvider = "MarketingStatus")
    public void getMarketingStatus(String marketingStatus) throws IOException {
        TfMarketingStatus mockResult = new TfMarketingStatus(
                new BigDecimal(marketingStatus),
                "MAPPED: DEPLOYED",
                new HashSet<>()
        );
        Mockito.mock(MarketingStatusDaoHibernate.class);
        Mockito.when(msdao.getMarketingStatus(session, marketingStatus))
                .thenReturn(mockResult);


        TfMarketingStatus tfms = msdao.getMarketingStatus(session, marketingStatus);

        System.out.println(tfms);
        Assert.assertEquals(mockResult, tfms);
        assertNotNull(tfms);
    }
}
