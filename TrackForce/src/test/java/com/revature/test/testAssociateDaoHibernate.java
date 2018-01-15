package com.revature.test;

import java.io.IOException;
import java.math.BigDecimal;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;

public class testAssociateDaoHibernate {

    @Test
    public void testUpdateInfo() throws IOException {
        AssociateDaoHibernate associateDaoHibernate = new AssociateDaoHibernate();
        ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
        MarketingStatusDaoHibernate marketingStatusDaoHibernate = new MarketingStatusDaoHibernate();
        Session session = HibernateUtil.getSession().openSession();
        
        TfClient client = clientDaoImpl.getClient(session, "Pitney Bowes");
        TfMarketingStatus status = marketingStatusDaoHibernate.getMarketingStatus(session, "MAPPED: TRAINING");

        associateDaoHibernate.updateInfo(session, new BigDecimal(266), status, client);
    }

    @Test (enabled=false)
    public void testGetAssociate() throws IOException {
        AssociateDaoHibernate associate = new AssociateDaoHibernate();
        BigDecimal d = new BigDecimal(266);
        TfAssociate associateA = associate.getAssociate(d);

        Assert.assertNotNull(associateA);
    }
}