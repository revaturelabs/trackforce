package com.revature.dao;
import java.util.List;
import com.revature.entity.TfMarketingStatus;

public interface MarketingStatusDao {
	List<TfMarketingStatus> getAllMarketingStatuses();
	TfMarketingStatus getMarketingStatusById(int id);
}