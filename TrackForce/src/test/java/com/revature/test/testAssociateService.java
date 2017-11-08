package com.revature.test;

import java.math.BigDecimal;

import com.revature.model.AssociateInfo;
import com.revature.services.AssociateService;

public class testAssociateService {

	public void testgetAssociatePositive() {	
	AssociateService associateservice = new AssociateService();
	BigDecimal bigdecimal=new BigDecimal(25);
	AssociateInfo associate=associateservice.getAssociate(bigdecimal);
	System.out.println(associate);
}
	public void testgetAssociateNegative() {	
		AssociateService associateservice = new AssociateService();
		BigDecimal bigdecimal=new BigDecimal(-25);
		AssociateInfo associate=associateservice.getAssociate(bigdecimal);
		System.out.println(associate);
	
}
}