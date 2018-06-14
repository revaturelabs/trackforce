//package com.revature.test.dao;
//
//import com.revature.dao.MarketingStatusDao;
//import com.revature.model.MarketingStatusInfo;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class MarketingStatusDAOTest {//extends BaseTest {
//
//    @Mock
//    MarketingStatusDao msDao;
//
//
//    Map<Integer, MarketingStatusInfo> mockMsMap;
//
//    @BeforeTest
//    public void setupMocks() throws IOException {
//        MockitoAnnotations.initMocks(this);
//
//        mockMsMap = new HashMap<>();
//
//        MarketingStatusInfo ms1 = new MarketingStatusInfo();
//        ms1.setId(new Integer(1));
//        ms1.setName("ms1");
//
//        MarketingStatusInfo ms2 = new MarketingStatusInfo();
//        ms2.setId(new Integer(2));
//        ms2.setName("ms2");
//
//        mockMsMap.put(ms1.getId(), ms1);
//        mockMsMap.put(ms2.getId(), ms2);
//        Mockito.when(msDao.getMarketingStatus())
//                .thenReturn(mockMsMap);
//    }
//
//    @Test
//    public void testGetMarketingStatuses() throws Exception {
//
//    }
//
//    @Test
//    public void testGetAllMarketingStatuses() throws Exception {
//
//    }
//}