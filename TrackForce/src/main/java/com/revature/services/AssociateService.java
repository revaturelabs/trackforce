package com.revature.services;

import java.util.List;

import com.revature.dao.AssociateDao;
import com.revature.dao.AssociateDaoHibernate;
import com.revature.entity.TfAssociate;

public class AssociateService {
	
	private static AssociateDao dao = new AssociateDaoHibernate();
	private AssociateService() {};


	public static TfAssociate getAssociate(int associateid) {
		return dao.getAssociate(associateid);
	}

	public static List<TfAssociate> getAllAssociates(){
		return dao.getAllAssociates();
	}
	
	public static boolean updateAssociate(TfAssociate associate) {
		return dao.updateAssociate(associate);
	}
	
	public static boolean updateAssociates(List<TfAssociate> associates) {
		return dao.updateAssociates(associates);
	}
	
	public static boolean createAssociate(TfAssociate newassociate) {
		return dao.createAssociate(newassociate);
	}


}
