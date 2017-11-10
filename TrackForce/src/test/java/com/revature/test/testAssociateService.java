package com.revature.test;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.revature.model.AssociateInfo;
import com.revature.services.AssociateService;

public class testAssociateService {

    @Test
    public void testgetAssociatePositive() {
        AssociateService associateservice = new AssociateService();
        BigDecimal bigdecimal = new BigDecimal(266);
        AssociateInfo associate = associateservice.getAssociate(bigdecimal);
        System.out.println(associate);
    }

    @Test
    public void testgetAssociateNegative() {
        AssociateService associateservice = new AssociateService();
        BigDecimal bigdecimal = new BigDecimal(-25);
        AssociateInfo associate = associateservice.getAssociate(bigdecimal);
        System.out.println(associate);
    }

    @Test
    public void testUpdateAssociatePositive()
    {
        AssociateService associateService = new AssociateService();
        associateService.updateAssociate("266", "MAPPED: RESERVED", "Corner Bakery");
        
        AssociateInfo associateInfo = associateService.getAssociate(new BigDecimal(266));
        
        Assert.assertEquals(associateInfo.getMarketingStatus(), "MAPPED: RESERVED");
        Assert.assertEquals(associateInfo.getClient(), "Corner Bakery");
    }
    
    @Test
    public void testUpdateAssociateNegative()
    {
        AssociateService associateService = new AssociateService();
        associateService.updateAssociate("266", "Placed Tomorrow", "Petsmart");
        
        AssociateInfo associateInfo = associateService.getAssociate(new BigDecimal(266));
        
        Assert.assertNotNull(associateInfo.getMarketingStatus());
        Assert.assertEquals(associateInfo.getClient(), "None");
    }
}