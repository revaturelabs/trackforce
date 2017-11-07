package com.revature.test;

import java.math.BigDecimal;

import org.testng.annotations.Test;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;

public class testAssociateDaoHibernate {

    @Test
    public void testUpdateInfo(){
        AssociateDaoHibernate associateDaoHibernate = new AssociateDaoHibernate();
        ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
        MarketingStatusDaoHibernate marketingStatusDaoHibernate = new MarketingStatusDaoHibernate();
        
        TfClient client = clientDaoImpl.getClient("Accenture");
        TfMarketingStatus status = marketingStatusDaoHibernate.getMarketingStatus("MAPPED: DEPLOYED");
        
        associateDaoHibernate.updateInfo(new BigDecimal(10), status, client);
    }
}
