package com.revature.services;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.math.BigDecimal;
import javax.ws.rs.core.Response;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.model.AssociateInfo;

public class AssociateServiceTest {

	AssociateService aServ = new AssociateService();
	
	@DataProvider(name = "associateId")
	public BigDecimal[] associateIds() {
		BigDecimal[] ids = new BigDecimal[] { new BigDecimal(212), new BigDecimal(213), new BigDecimal(215) };
		return ids;
	}

	@Test(dataProvider="associateId")
	public void getAssociate(BigDecimal associateid) throws IOException {
		AssociateInfo result = aServ.getAssociate(associateid);
		assertNotNull(result);
	}
	
	@Test
	public void getAllAssociates() throws IOException {
		Response result = aServ.getAllAssociates();
		assertNotNull(result);
	}
}
