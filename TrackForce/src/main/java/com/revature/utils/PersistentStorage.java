package com.revature.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.revature.model.*;

// Cached data for faster fetching
// This does not violate restful service architectures. A restful service does
// not maintain state with the session, allowing it to handle requests from anywhere
// It can (and should) however maintain state with the database via caching and any 
// external services that it is dependent on, such as Salesforce in this case. 

// Eviction strategy: information is overwritten with incoming data by id
public class PersistentStorage {

	private static PersistentStorage storage;

	private Map<Integer, AssociateInfo> associates = new HashMap<>();
	private Map<Integer, BatchInfo> batches = new HashMap<>();
	private Map<Integer, BatchInfo> batchesByDate = new HashMap<>();
	private Map<Integer, ClientInfo> clients = new HashMap<>();
	private Map<Integer, CurriculumInfo> curriculums = new HashMap<>();
	private Map<Integer, MarketingStatusInfo> marketingStatuses = new HashMap<>();
	private StatusInfo totalStats;

	private PersistentStorage() {
	}

	public static PersistentStorage getStorage() {
		if (storage == null)
			storage = new PersistentStorage();
		return storage;
	}

	public Set<AssociateInfo> getAssociates() {
		return new TreeSet<AssociateInfo>(associates.values());
	}
	
	//added method to actually get values from the cache
	public AssociateInfo getAssociate(Integer id) {
		return associates.get(id);
	}

	public void setAssociates(Map<Integer, AssociateInfo> associates) {
		if (this.associates == null)
			this.associates = associates;
		else {
			this.associates.putAll(associates);
		}
		this.totalStats = AssociateInfo.getTotals();
	}

	public TreeSet<BatchInfo> getBatches() {
		return new TreeSet<BatchInfo>(batches.values());
	}

	public void setBatches(Map<Integer, BatchInfo> batches) {
		if (batches == null)
			this.batches = batches;
		else {
			this.batches.putAll(batches);
		}
		setBatchesByDate(this.batches);
	}

	//added method to actually get values from the cache
	public ClientInfo getClient(Integer id) {
		return clients.get(id);
	}
		
	public Set<ClientInfo> getClients() {
		return new TreeSet<ClientInfo>(clients.values());
	}

	public void setClients(Map<Integer, ClientInfo> clients) {
		if (this.clients == null)
			this.clients = clients;
		else {
			this.clients.putAll(clients);
		}
	}

	public StatusInfo getTotals() {
		return this.totalStats;
	}

	public Set<CurriculumInfo> getCurriculums() {
		return new TreeSet<CurriculumInfo>(curriculums.values());
	}

	public void setCurriculums(Map<Integer, CurriculumInfo> map) {
		if (this.curriculums == null)
			this.curriculums = map;
		else {
			this.curriculums.putAll(map);
		}
	}

	public Set<MarketingStatusInfo> getMarketingStatuses() {
		return new TreeSet<MarketingStatusInfo>(marketingStatuses.values());
	}

	public void setMarketingStatuses(Map<Integer, MarketingStatusInfo> marketingStatuses) {
		if (this.marketingStatuses == null)
			this.marketingStatuses = marketingStatuses;
		else {
			this.marketingStatuses.putAll(marketingStatuses);
		}
	}

	private void setBatchesByDate(Map<Integer, BatchInfo> batches) {
		if (this.batchesByDate == null) {
			this.batchesByDate = batches;
		} else {
			this.batchesByDate.putAll(batches);
		}
	}

	public List<BatchInfo> getBatchesByDate() {
		List<BatchInfo> batches = new LinkedList<BatchInfo>(this.batches.values());
		Collections.sort(batches, new DateComparator());
		return batches;
	}

	private class DateComparator implements Comparator<BatchInfo> {

		@Override
		public int compare(BatchInfo bi1, BatchInfo bi2) {
			if (bi1.getStartTs() == null)
				return bi2.getStartTs() == null ? 0 : -1;
			if (bi2.getStartTs() == null)
				return 1;
			return bi1.getStartTs().compareTo(bi2.getStartTs());
		}
	}

	public void evictAssociates() {
		this.associates.clear();
		this.totalStats.clear();
		// just in case...
		this.getAssociates().clear();
	}

	public void evictBatches() {
		this.batches.clear();
		this.batchesByDate.clear();
	}

	public void evictClients() {
		this.clients.clear();
		totalStats = null;
	}

	public void evictCurriculums() {
		this.curriculums.clear();
	}

	public void evictMarketingStatuses() {
		this.marketingStatuses.clear();
	}

	public void setTotals(StatusInfo totals) {
		this.totalStats = totals;
	}


	public Map<Integer, AssociateInfo> getAssociateAsMap() {
		return this.associates;
	}
	
	public Map<Integer, BatchInfo> getBatchAsMap() {
		return this.batches;
	}
	
	public Map<Integer, ClientInfo> getClientAsMap() {
		return this.clients;
	}
	
	public Map<Integer, CurriculumInfo> getCurriculumAsMap() {
		return this.curriculums;
	}
	
	public Map<Integer, MarketingStatusInfo> getMarketingAsMap() {
		return this.marketingStatuses;
	}

	public void updateAssociates(Map<Integer, AssociateInfo> associates) {
		this.associates.putAll(associates);
	}
}
