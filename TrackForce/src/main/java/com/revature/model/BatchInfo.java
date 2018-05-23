package com.revature.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Batch class to hold information about the batch for the batch listing page.
 */
public class BatchInfo implements Serializable, Comparable<BatchInfo> {
	private static final long serialVersionUID = 7437353955754399023L;
	private Integer id;
	private String batchName;
	private String curriculumName;
	private String location;
	private String startDate;
	private String endDate;
	private Long startLong;
	private Long endLong;
	@JsonIgnore
	private Timestamp startTs;
	private Set<AssociateInfo> associates = new TreeSet<AssociateInfo>();

	/**
	 * Constructs a newly allocated BatchInfo object that represents an empty batch.
	 */
	public BatchInfo() {
		super();
	}

	/**
	 * Constructs a newly allocated BatchInfo object that represents a batch of
	 * Revature assodiates without curriculum or location.
	 * 
	 * @param batchName
	 *            - The name of the batch.
	 * @param startDate
	 *            - The date that this batch started.
	 * @param endDate
	 *            - The date that this batch graduates.
	 */
	public BatchInfo(String batchName, String startDate, String endDate) {
		super();
		this.batchName = batchName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Constructs a newly allocated BatchInfo object that represents a batch of
	 * Revature associates.
	 * 
	 * @param batchName
	 *            - The name of the batch.
	 * @param curriculumName
	 *            - The name of the curriculum this batch is studying.
	 * @param location
	 *            - The location of this batch.
	 * @param startDate
	 *            - The date that this batch started.
	 * @param endDate
	 *            - The date that this batch graduates.
	 */
	public BatchInfo(Integer id, String batchName, String curriculumName, String location, String startDate,
			String endDate) {
		super();
		this.id = id;
		this.batchName = batchName;
		this.curriculumName = curriculumName;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer integer) {
		this.id = integer;
	}

	public Long getStartLong() {
		return startLong;
	}

	public void setStartLong(Long startLong) {
		this.startLong = startLong;
	}

	public Long getEndLong() {
		return endLong;
	}

	public void setEndLong(Long endLong) {
		this.endLong = endLong;
	}

	public Timestamp getStartTs() {
		return startTs;
	}

	public void setStartTs(Timestamp startTs) {
		this.startTs = startTs;
	}

	public Set<AssociateInfo> getAssociates() {
		return associates;
	}

	public void setAssociates(Set<AssociateInfo> associates) {
		this.associates = associates;
	}

	/**
	 * Retrieves the batch name
	 * 
	 * @return - A string with the batch name
	 */
	public String getBatchName() {
		return batchName;
	}

	/**
	 * Sets the batch name to the given value.
	 * 
	 * @param batchName
	 *            - What to set the batch name to.
	 */
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	/**
	 * Retrieve the name of the curriculum this batch is studying.
	 * 
	 * @return - A string with the curriculum name.
	 */
	public String getCurriculumName() {
		return curriculumName;
	}

	/**
	 * Sets the name of the curriculum this batch is studying.
	 * 
	 * @param curriculumName
	 *            - What to set the curriculum's name to.
	 */
	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}

	/**
	 * Retrieve the location where this batch is.
	 * 
	 * @return - A string with the batch's location.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Set the location where this batch is
	 * 
	 * @param location
	 *            - What to set the batch's location to
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Retrieve the date that this batch started.
	 * 
	 * @return - A string with the batch's start date
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Set the date that this batch started.
	 * 
	 * @param startDate
	 *            - What to set the start date to.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Retrieve the date that this batch graduates.
	 * 
	 * @return - A string with the batch's end date.
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Set the date that this batch graduates
	 * 
	 * @param endDate
	 *            - What to set the batch's start date to.
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchName == null) ? 0 : batchName.hashCode());
		result = prime * result + ((curriculumName == null) ? 0 : curriculumName.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BatchInfo other = (BatchInfo) obj;
		if (batchName == null) {
			if (other.batchName != null)
				return false;
		} else if (!batchName.equals(other.batchName))
			return false;
		if (curriculumName == null) {
			if (other.curriculumName != null)
				return false;
		} else if (!curriculumName.equals(other.curriculumName))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BatchInfo [batchName=" + batchName + ", curriculumName=" + curriculumName + ", location=" + location
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	@Override
	public int compareTo(BatchInfo o) {
		return this.id - o.getId();
	}
}