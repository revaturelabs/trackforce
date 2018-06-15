//package com.revature.test.services;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.mockito.Matchers;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.testng.Assert;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import com.revature.dao.ClientDao;
//import com.revature.model.ClientInfo;
//import com.revature.model.StatusInfo;
//import com.revature.services.ClientService;
//
//public class ClientServiceTest{
//
//    @Mock
//    private ClientDao clientDaoMock;
//
//    private ClientService clientService;
//
//    private Set<ClientInfo> mockClients;
//    private ClientInfo mockClient;
//
//    private int c1Id = 1, c2Id = 2, c3Id = 3;
//    private String c1Name = "client 1", c2Name = "client 2";
//
//    private void setupMocks() throws IOException {
//        MockitoAnnotations.initMocks(this);
//
//        // mock dao used by client resource to return these clients
//        ClientInfo cInfo1 = new ClientInfo();
//        cInfo1.setId(new Integer(c1Id));
//        cInfo1.setTfClientId(new Integer(c1Id));
//        cInfo1.setTfClientName(c1Name);
//        cInfo1.setStats(new StatusInfo("status1"));
//
//        ClientInfo cInfo2 = new ClientInfo();
//        cInfo2.setId(new Integer(c2Id));
//        cInfo2.setTfClientId(new Integer(c2Id));
//        cInfo2.setTfClientName(c2Name);
//        cInfo2.setStats(new StatusInfo("status2"));
//
//        ClientInfo cInfo3 = new ClientInfo();
//        cInfo3.setId(new Integer(c3Id));
//        cInfo3.setTfClientId(new Integer(c3Id));
//        cInfo3.setTfClientName(c2Name);
//        cInfo3.setStats(new StatusInfo("status3"));
//
//
//        mockClients = new HashSet<>();
//        mockClients.add(cInfo1);
//        mockClients.add(cInfo2);
//        mockClients.add(cInfo3);
//
//        Mockito.when(clientDaoMock.getAllClientsFromCache()).thenReturn(mockClients);
//
//        mockClient = cInfo1;
//
//        Mockito.when(clientDaoMock.getClientFromCache(Matchers.anyInt())).thenReturn(mockClient);
//
//        clientService = new ClientService(clientDaoMock);
//    }
//
//    @BeforeTest
//    public void beforeAll() throws IOException {
//        setupMocks();
//    }
//
//    @Test(enabled = true)
//    public void testGetClients() throws Exception {
//    	Set<ClientInfo> clients = clientService.getClients();
//    	Set<ClientInfo> testSet = mockClients;
//        Assert.assertEquals(testSet, clients);
//    }
//
//    @Test(enabled = true)
//    public void testGetClientByID() throws Exception {
//    	ClientInfo client = clientService.getClientByID(1);
//    	ClientInfo testClient = mockClient;
//        Assert.assertEquals(testClient, client);
//    }
//     @Test(enabled = false)
//     public void testGetTotals() throws Exception {
//        	Assert.assertTrue(false);
//     }
//
//}