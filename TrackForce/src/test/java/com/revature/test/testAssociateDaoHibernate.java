package com.revature.test;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;

public class testAssociateDaoHibernate {

    @Test
    public void testUpdateInfo() {
        AssociateDaoHibernate associateDaoHibernate = new AssociateDaoHibernate();
        ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
        MarketingStatusDaoHibernate marketingStatusDaoHibernate = new MarketingStatusDaoHibernate();

        TfClient client = clientDaoImpl.getClient("Pitney Bowes");
        TfMarketingStatus status = marketingStatusDaoHibernate.getMarketingStatus("MAPPED: TRAINING");

        System.out.println(status + " " + client);
        associateDaoHibernate.updateInfo(new BigDecimal(266), status, client);
    }

    @Test (enabled=false)
    public void testGetAssociate() {
        AssociateDaoHibernate associate = new AssociateDaoHibernate();
        BigDecimal d = new BigDecimal(266);
        TfAssociate associateA = associate.getAssociate(d);
        System.out.println(associateA.toString());
        Assert.assertNotNull(associateA);
    }
}