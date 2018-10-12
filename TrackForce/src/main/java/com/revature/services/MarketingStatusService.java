package com.revature.services;
import com.revature.dao.MarketingStatusDao;
import com.revature.daoimpl.MarketingStatusDaoImpl;
import com.revature.entity.TfMarketingStatus;
import java.util.List;

public class MarketingStatusService {
	private MarketingStatusDao dao; 
	public MarketingStatusService(){dao = new MarketingStatusDaoImpl();}
	
	public MarketingStatusService(MarketingStatusDao dao) {this.dao = dao;}

	public List<TfMarketingStatus> getAllMarketingStatuses() {
		return dao.getAllMarketingStatuses();
	}

	public TfMarketingStatus getMarketingStatusById(int id) {
		return dao.getMarketingStatusById(id);
	}
}