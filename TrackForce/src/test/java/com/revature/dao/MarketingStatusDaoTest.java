package com.revature.dao;

import static org.testng.Assert.assertNotNull;

import org.hibernate.Session;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.*;

import com.revature.entity.TfMarketingStatus;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;

public class MarketingStatusDaoTest {


    @Mock
    MarketingStatusDaoHibernate msdao;
    Session session = null;

    @BeforeClass
    public void beforeClass() {
        MockitoAnnotations.initMocks(this);
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
        Assert.assertEquals(mockResult, tfms);
        assertNotNull(tfms);
    }
}
