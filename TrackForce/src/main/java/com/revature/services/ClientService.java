package com.revature.services;

import java.io.IOException;
import java.util.Set;


import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.model.ClientInfo;

public class ClientService {
    private ClientDao clientDao;

    public ClientService() {
        this.clientDao = new ClientDaoImpl();
    }
    
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    /**
     * same as getAllClients but does not use cache mechanism
     *
     * @return
     * @throws IOException
     */
    public Set<ClientInfo> getClients() throws IOException {
    	try {
    		return clientDao.getAllClientsFromCache();
        } catch (Exception e) {
            throw new IOException("could not get clients", e);
        }
    }
    
    public ClientInfo getClientByID(int id) throws IOException{
    	try {
    		return clientDao.getClientFromCache(id);
    	} catch (Exception e) {
    		throw new IOException("could not get client by ID", e);
    	}
    }
}
