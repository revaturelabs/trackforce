package com.revature.test.services;

import com.revature.dao.ClientDao;
import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;
import com.revature.model.StatusInfo;
import com.revature.services.ClientResource;
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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClientResourceTest extends BaseTest {

    @Mock
    private ClientDao clientDao;

    private ClientResource clientResource;

    private Map<BigDecimal, ClientInfo> mockClientMap;
    private Set<ClientInfo> mockClients;
    private String status1Name = "status1";

    private int c1Id = 1, c2Id = 2, c3Id = 3;
    private String c1Name = "client 1", c2Name = "client 2", c3Name = "client 3";

    private void setupMocks() throws IOException {
        MockitoAnnotations.initMocks(this);

        ClientInfo cInfo1 = new ClientInfo();
        cInfo1.setId(new BigDecimal(c1Id));
        cInfo1.setTfClientId(new BigDecimal(c1Id));
        cInfo1.setTfClientName(c1Name);
        cInfo1.setStats(new StatusInfo(status1Name));

        ClientInfo cInfo2 = new ClientInfo();
        cInfo2.setId(new BigDecimal(c2Id));
        cInfo2.setTfClientId(new BigDecimal(c2Id));
        cInfo2.setTfClientName(c2Name);
        cInfo2.setStats(new StatusInfo("status2"));

        ClientInfo cInfo3 = new ClientInfo();
        cInfo3.setId(new BigDecimal(c3Id));
        cInfo3.setTfClientId(new BigDecimal(c3Id));
        cInfo3.setTfClientName(c2Name);
        cInfo3.setStats(new StatusInfo("status3"));

        mockClientMap = new HashMap<>();
        mockClientMap.put(cInfo1.getId(), cInfo1);
        mockClientMap.put(cInfo2.getId(), cInfo2);
        mockClientMap.put(cInfo3.getId(), cInfo3);

        mockClients = new HashSet<>();
        mockClients.add(cInfo1);
        mockClients.add(cInfo2);
        mockClients.add(cInfo3);

        Mockito.when(clientDao.getAllTfClients(Matchers.any(Session.class)))
                .thenReturn(mockClientMap);

        TfClient mockClient1 = new TfClient();
        mockClient1.setTfClientId(new BigDecimal(c1Id));
        mockClient1.setTfClientName(status1Name);

         Mockito.when(clientDao.getClient(Matchers.any(Session.class), Matchers.anyString()))
                .thenReturn(mockClient1);

        clientResource = new ClientResource(clientDao, sessionFactory);
        resetCaches();
    }


    @BeforeTest
    public void beforeAll() throws IOException {
        setupMocks();
    }

    /*  // todo why isn't the dao getting properly mocked!?
    @Test
    public void testGetClients() throws Exception {
        StatusInfo actualStatus1 = clientResource.getClientInfo(1);
        Assert.assertEquals(status1Name, actualStatus1.getName());
    }
    */

    @Test
    public void testGetClientInfo() throws Exception {
        Map<BigDecimal, ClientInfo> actualClientMap = clientResource.getClients();

        Assert.assertEquals(mockClientMap.size(), actualClientMap.size());
        for (BigDecimal id : mockClientMap.keySet()) {
            ClientInfo mockVal = mockClientMap.get(id);
            ClientInfo actualVal = actualClientMap.get(id);
            Assert.assertNotNull(mockVal);
            Assert.assertEquals(actualVal.getId(), mockVal.getId());
            Assert.assertEquals(actualVal.getStats().getName(), mockVal.getStats().getName());
        }
    }
}