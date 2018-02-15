package com.revature.test.services;

import com.revature.dao.MarketingStatusDao;
import com.revature.model.MarketingStatusInfo;
import com.revature.services.MarketingStatusService;
import com.revature.test.BaseTest;
import org.hibernate.Session;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class MarketingStatusServiceTest extends BaseTest {

    @Mock
    MarketingStatusDao msDao;

    MarketingStatusService msService;

    Map<BigDecimal, MarketingStatusInfo> mockMsMap;

    @BeforeTest
    public void setupMocks() throws IOException {
        MockitoAnnotations.initMocks(this);

        mockMsMap = new HashMap<>();

        MarketingStatusInfo ms1 = new MarketingStatusInfo();
        ms1.setId(new BigDecimal(1));
        ms1.setName("ms1");

        MarketingStatusInfo ms2 = new MarketingStatusInfo();
        ms2.setId(new BigDecimal(2));
        ms2.setName("ms2");

        mockMsMap.put(ms1.getId(), ms1);
        mockMsMap.put(ms2.getId(), ms2);
        Mockito.when(msDao.getMarketingStatuses(Matchers.any(Session.class)))
                .thenReturn(mockMsMap);

        msService = new MarketingStatusService(msDao, sessionFactory);
    }

    @Test
    public void testGetMarketingStatuses() throws Exception {
        Map<BigDecimal, MarketingStatusInfo> actualMsMap =  msService.getMarketingStatuses();
        Assert.assertEquals(mockMsMap.size(), actualMsMap.size());
        for (BigDecimal id : mockMsMap.keySet()) {
            MarketingStatusInfo actualVal = actualMsMap.get(id);
            MarketingStatusInfo mockVal = mockMsMap.get(id);
            Assert.assertNotNull(mockVal);
            Assert.assertEquals(actualVal.getId(), mockVal.getId());
            Assert.assertEquals(actualVal.getName(), mockVal.getName());
        }
    }

    @Test
    public void testGetAllMarketingStatuses() throws Exception {
        Map<BigDecimal, MarketingStatusInfo> actualMsMap =  msService.getMarketingStatuses();
        Assert.assertEquals(mockMsMap.size(), actualMsMap.size());
        for (BigDecimal id : mockMsMap.keySet()) {
            MarketingStatusInfo actualVal = actualMsMap.get(id);
            MarketingStatusInfo mockVal = mockMsMap.get(id);
            Assert.assertNotNull(mockVal);
            Assert.assertEquals(actualVal.getId(), mockVal.getId());
            Assert.assertEquals(actualVal.getName(), mockVal.getName());
        }
    }
}