package com.revature.services;
import com.revature.dao.MarketingStatusDao;
import com.revature.daoimpl.MarketingStatusDaoImpl;
import com.revature.entity.TfMarketingStatus;
import java.util.List;

public class MarketingStatusService {
	private static MarketingStatusDao dao = new MarketingStatusDaoImpl();

	public MarketingStatusService(){}

	public List<TfMarketingStatus> getAllMarketingStatuses() { return dao.getAllMarketingStatuses(); }

	public TfMarketingStatus getMarketingStatusById(int id) { return dao.getMarketingStatusById(id); }
}