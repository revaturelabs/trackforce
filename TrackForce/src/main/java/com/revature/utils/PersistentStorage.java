package com.revature.utils;

import java.math.BigDecimal;
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

	private Map<BigDecimal, AssociateInfo> associates = new HashMap<>();
	private Map<BigDecimal, BatchInfo> batches = new HashMap<>();
	private Map<BigDecimal, BatchInfo> batchesByDate = new HashMap<>();
	private Map<BigDecimal, ClientInfo> clients = new HashMap<>();
	private Map<BigDecimal, CurriculumInfo> curriculums = new HashMap<>();
	private Map<BigDecimal, MarketingStatusInfo> marketingStatuses = new HashMap<>();
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

	public void setAssociates(Map<BigDecimal, AssociateInfo> associates) {
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

	public void setBatches(Map<BigDecimal, BatchInfo> batches) {
		if (batches == null)
			this.batches = batches;
		else {
			this.batches.putAll(batches);
		}
		setBatchesByDate(this.batches);
	}

	public Set<ClientInfo> getClients() {
		return new TreeSet<ClientInfo>(clients.values());
	}

	public void setClients(Map<BigDecimal, ClientInfo> clients) {
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

	public void setCurriculums(Map<BigDecimal, CurriculumInfo> curriculums) {
		if (this.curriculums == null)
			this.curriculums = curriculums;
		else {
			this.curriculums.putAll(curriculums);
		}
	}

	public Set<MarketingStatusInfo> getMarketingStatuses() {
		return new TreeSet<MarketingStatusInfo>(marketingStatuses.values());
	}

	public void setMarketingStatuses(Map<BigDecimal, MarketingStatusInfo> marketingStatuses) {
		if (this.marketingStatuses == null)
			this.marketingStatuses = marketingStatuses;
		else {
			this.marketingStatuses.putAll(marketingStatuses);
		}
	}

	private void setBatchesByDate(Map<BigDecimal, BatchInfo> batches) {
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

	public Map<BigDecimal, AssociateInfo> getAssociateAsMap() {   //track this method in Associate Services
		return this.associates;     //returns a Hashmap tht is then stored in an AssociatesInfo variable in the Service Class
	}
	
	public Map<BigDecimal, BatchInfo> getBatchAsMap() {
		return this.batches;
	}
	
	public Map<BigDecimal, ClientInfo> getClientAsMap() {
		return this.clients;
	}
	
	public Map<BigDecimal, CurriculumInfo> getCurriculumAsMap() {
		return this.curriculums;
	}
	
	public Map<BigDecimal, MarketingStatusInfo> getMarketingAsMap() {
		return this.marketingStatuses;
	}

	public void updateAssociates(Map<BigDecimal, AssociateInfo> associates) {   //this too
		this.associates.putAll(associates);
	}
}
