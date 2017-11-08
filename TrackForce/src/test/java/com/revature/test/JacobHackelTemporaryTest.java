package com.revature.test;

import java.util.List;

import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.revature.dao.HomeDao;
import com.revature.dao.HomeDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.services.ClientResource;
import com.revature.services.HomeResource;

public class JacobHackelTemporaryTest {
	@Test(enabled = false)
	public void f() {
		Response response = new ClientResource().getAStatusInfo();
		System.out.println(response.getEntity());
		response.close();
	}

	@Test(enabled = false)
	public void allClients() {
		HomeResource homeResource = new HomeResource();
		homeResource.init();
		System.out.println(homeResource.getMappedAndUnmappedInfo());
	}

	@Test(enabled = false)
	public void aClient() {
		HomeResource homeResource = new HomeResource();
		homeResource.init();
		System.out.println(homeResource.getMappedAndUnmappedInfo());
	}

	@Test
	public void associateBatchCurriculum() {
		HomeDao homeDaoImpl = new HomeDaoImpl();
		List<TfAssociate> associates = homeDaoImpl.getAllTfAssociates();
		System.out.println(associates.get(0).getTfBatch().getTfCurriculum().getTfCurriculumId());
		System.out.println(associates.get(0).getTfBatch().getTfCurriculum().getTfCurriculumName());
	}
}
