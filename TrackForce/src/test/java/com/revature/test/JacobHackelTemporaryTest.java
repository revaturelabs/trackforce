package com.revature.test;

import java.util.List;

import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.revature.dao.HomeDao;
import com.revature.dao.HomeDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.model.StatusInfo;
import com.revature.services.ClientResource;
import com.revature.services.HomeResource;
import com.revature.utils.StatusInfoUtil;

public class JacobHackelTemporaryTest {
	@Test
	public void StatusInfoUtilTest() {
		HomeDao homeDaoImpl = new HomeDaoImpl();
		List<TfAssociate> associates = homeDaoImpl.getAllTfAssociates();
		StatusInfoUtil.updateStatusInfoFromAssociates(associates);
		System.out.println(StatusInfoUtil.getAllAssociatesStatusInfo());

		// List<StatusInfo> clientsInfo =
		// StatusInfoUtil.getSpecificClientStatusInfoAsList();
		// System.out.println("Clients' Status Info:");
		// for(StatusInfo clientInfo : clientsInfo) {
		// System.out.println(clientInfo);
		// }
		//
		// List<StatusInfo> curriculumsInfo =
		// StatusInfoUtil.getSpecificCurriculumStatusInfoAsList();
		// System.out.println("Curriculums' Status Info:");
		// for(StatusInfo curriculumInfo : curriculumsInfo) {
		// System.out.println(curriculumInfo);
		// }
	}

	@Test
	public void seeIfStaticStatusInfoStillAvailable() {
		// HomeDao homeDaoImpl = new HomeDaoImpl();
		// List<TfAssociate> associates = homeDaoImpl.getAllTfAssociates();
		// StatusInfoUtil.updateStatusInfoFromAssociates(associates);
		System.out.println(StatusInfoUtil.getAllAssociatesStatusInfo());

		List<StatusInfo> clientsInfo = StatusInfoUtil.getSpecificClientStatusInfoAsList();
		System.out.println("Clients' Status Info:");
		for (StatusInfo clientInfo : clientsInfo) {
			System.out.println(clientInfo);
		}

		List<StatusInfo> curriculumsInfo = StatusInfoUtil.getSpecificCurriculumStatusInfoAsList();
		System.out.println("Curriculums' Status Info:");
		for (StatusInfo curriculumInfo : curriculumsInfo) {
			System.out.println(curriculumInfo);
		}
	}
}
