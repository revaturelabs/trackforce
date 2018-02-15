package com.revature.test.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;
import com.revature.model.StatusInfo;
import com.revature.services.ClientService;

public class ClientServiceTest{

    @Mock
    private ClientDao clientDaoMock;

    private ClientService clientService;

    private Map<Integer, ClientInfo> mockClientMap;
    private Set<ClientInfo> mockClients;
    private String status1Name = "status1";
    private ClientInfo mockClient;

    private int c1Id = 1, c2Id = 2, c3Id = 3;
    private String c1Name = "client 1", c2Name = "client 2";//, c3Name = "client 3";

    private void setupMocks() throws IOException {
    	System.out.println("setupMocks");
        MockitoAnnotations.initMocks(this);
        
        clientDaoMock = Mockito.mock(ClientDao.class);
        
        // mock dao used by client resource to return these clients
        ClientInfo cInfo1 = new ClientInfo();
        cInfo1.setId(new Integer(c1Id));
        cInfo1.setTfClientId(new Integer(c1Id));
        cInfo1.setTfClientName(c1Name);
        cInfo1.setStats(new StatusInfo(status1Name));

        ClientInfo cInfo2 = new ClientInfo();
        cInfo2.setId(new Integer(c2Id));
        cInfo2.setTfClientId(new Integer(c2Id));
        cInfo2.setTfClientName(c2Name);
        cInfo2.setStats(new StatusInfo("status2"));

        ClientInfo cInfo3 = new ClientInfo();
        cInfo3.setId(new Integer(c3Id));
        cInfo3.setTfClientId(new Integer(c3Id));
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

        Mockito.when(clientDaoMock.getAllClientsFromCache()).thenReturn(mockClients);

        TfClient mockClient1 = new TfClient();
        mockClient1.setTfClientId(new Integer(c1Id));
        mockClient1.setTfClientName(status1Name);

        mockClient = cInfo1;
        
        Mockito.when(clientDaoMock.getClientFromCache(Matchers.anyInt())).thenReturn(mockClient);

         // use mocked client resource dao to reset the cache
        clientService = new ClientService();
//        resetCaches(null, clientService, null);    // only mocking the clientResource
    }


    @BeforeTest
    public void beforeAll() throws IOException {
    	System.out.println("Before test");
        setupMocks();
    }

    @Test(enabled = true)
    public void testGetClients() throws Exception {
    	System.out.println("testGetClients");
    	Set<ClientInfo> clients = clientService.getClients();
    	Set<ClientInfo> testSet = mockClients;
    	System.out.println("clients: "+clients.toString());
    	System.out.println("testSet: "+testSet.toString());
        Assert.assertEquals(testSet, clients);
    }

    @Test(enabled = false)
    public void testGetClientByID() throws Exception {
//        Map<Integer, ClientInfo> actualClientMap = clientService.getClients();

//        Assert.assertEquals(mockClientMap.size(), actualClientMap.size());
//        for (Integer id : mockClientMap.keySet()) {
//            ClientInfo mockVal = mockClientMap.get(id);
//            ClientInfo actualVal = actualClientMap.get(id);
//            Assert.assertNotNull(mockVal);
//            Assert.assertEquals(actualVal.getId(), mockVal.getId());
//            Assert.assertEquals(actualVal.getStats().getName(), mockVal.getStats().getName());
//        }
    }
     @Test(enabled = false)
     public void testGetTotals() throws Exception {
        	Assert.assertTrue(false);
     }
    
}