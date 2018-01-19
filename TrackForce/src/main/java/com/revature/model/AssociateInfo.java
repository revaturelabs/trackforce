package com.revature.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.revature.entity.TfMarketingStatus;
import com.revature.utils.LogUtil;

/**
 * Class to hold information about an associate for the batch details page.
 */
public class AssociateInfo implements Serializable, Comparable<AssociateInfo> {

	/**
	 * 
	 */

	private static final long serialVersionUID = 6047227506797717614L;
	private BigDecimal id;
	private String firstName;
	private String lastName;
	private static StatusInfo totals = new StatusInfo();

	private BigDecimal msid;
	private String marketingStatus;

	private BigDecimal clid;
	private String client;

	private BigDecimal ecid;
	private String endClient;

	private BigDecimal bid;
	private String batchName;

	public BigDecimal getMsid() {
		return msid;
	}

	public void setMsid(BigDecimal msid) {
		this.msid = msid;
	}

	public BigDecimal getClid() {
		return clid;
	}

	public void setClid(BigDecimal clid) {
		this.clid = clid;
	}

	public BigDecimal getEcid() {
		return ecid;
	}

	public void setEcid(BigDecimal ecid) {
		this.ecid = ecid;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	public BigDecimal getCurid() {
		return curid;
	}

	public void setCurid(BigDecimal curid) {
		this.curid = curid;
	}

	private BigDecimal curid;
	private String curriculumName;

	/**
	 * Constructs a newly allocated BatchInfo object that represents an associate.
	 * 
	 * @param id
	 *            - The id of the associate.
	 * @param firstName
	 *            - The associate's first name.
	 * @param lastName
	 *            - The associate's last name.
	 * @param marketingStatus
	 *            - The associate's marketing status (mapped, unmapped, etc.)
	 * @param clientid-The
	 *            clients's name.
	 */
	public AssociateInfo(BigDecimal id, String firstName, String lastName, String marketingStatus, String client) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.marketingStatus = marketingStatus;
		this.client = client;
	}

	public AssociateInfo(BigDecimal id, String firstName, String lastName, String marketingStatus, String client,
			String batchName, String curriculumName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.marketingStatus = marketingStatus;
		this.client = client;
		this.endClient = "";
		this.batchName = batchName;
		this.curriculumName = curriculumName;
	}

	public AssociateInfo() {
	}

	/**
	 * Retrieve the associate's ID
	 * 
	 * @return - String with the ID of the associate
	 */
	public BigDecimal getId() {
		return id;
	}

	/**
	 * Set's the associate's ID
	 * 
	 * @param id
	 *            - What to set the associate's ID to.
	 */
	public void setId(BigDecimal id) {
		this.id = id;
	}

	/**
	 * Retrieve the first name of the associate.
	 * 
	 * @return - A string with the first name of the associate.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set's the first name of the associate.
	 * 
	 * @param firstName
	 *            - What to set the associate's first name to.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Retrieve the last name of the associate.
	 * 
	 * @return - A string with the last name of the associate.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the associate
	 * 
	 * @param lastName
	 *            - What to set the associate's last name to.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Retrieve the associate's marketing status (mapped, unmapped, etc.)
	 * 
	 * @return - A string with the associate's marketing status
	 */
	public String getMarketingStatus() {
		return marketingStatus;
	}

	/**
	 * Set the associate's marketing status (mapped, unmapped, etc.)
	 * 
	 * @param marketingStatus
	 *            - What to set the associate's marketing status to.
	 */
	public void setMarketingStatus(String marketingStatus) {
		this.marketingStatus = marketingStatus;
	}

	/**
	 * Retrieve the client name
	 * 
	 * @return- A string with the client's name
	 */

	public String getClient() {
		return client;
	}

	/**
	 * Set the client's name
	 * 
	 * @param clientname
	 *            -What to set the associate's client name to.
	 */
	public void setClient(String client) {
		this.client = client == null ? "None" : client;
	}

	/**
	 * Retrieve the end client's name
	 * 
	 * @return - A string with the name of the end client
	 */
	public String getEndClient() {
		return endClient;
	}

	/**
	 * Set the end client's name
	 * 
	 * @param endClient
	 */
	public void setEndClient(String endClient) {
		this.endClient = endClient == null ? "None" : endClient;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName == null ? "None" : batchName;
	}

	public String getCurriculumName() {
		return curriculumName;
	}

	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName == null ? "None" : curriculumName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchName == null) ? 0 : batchName.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((curriculumName == null) ? 0 : curriculumName.hashCode());
		result = prime * result + ((endClient == null) ? 0 : endClient.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((marketingStatus == null) ? 0 : marketingStatus.hashCode());
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
		AssociateInfo other = (AssociateInfo) obj;
		if (batchName == null) {
			if (other.batchName != null)
				return false;
		} else if (!batchName.equals(other.batchName))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (curriculumName == null) {
			if (other.curriculumName != null)
				return false;
		} else if (!curriculumName.equals(other.curriculumName))
			return false;
		if (endClient == null) {
			if (other.endClient != null)
				return false;
		} else if (!endClient.equals(other.endClient))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (marketingStatus == null) {
			if (other.marketingStatus != null)
				return false;
		} else if (!marketingStatus.equals(other.marketingStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AssociateInfo [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", marketingStatus="
				+ marketingStatus + ", client=" + client + ", endClient=" + endClient + ", batchName=" + batchName
				+ ", curriculumName=" + curriculumName + "]";
	}

	@Override
	public int compareTo(AssociateInfo o) {
		return this.id.subtract(o.id).intValueExact();
	}

	public void setBatchId(BigDecimal tfBatchId) {
		this.bid = tfBatchId == null ? new BigDecimal(-1) : tfBatchId;
	}

	public void setClientId(BigDecimal tfClientId) {
		this.clid = tfClientId == null ? new BigDecimal(-1) : tfClientId;
	}

	public void setCurriculumId(BigDecimal tfCurriculumId) {
		this.curid = tfCurriculumId == null ? new BigDecimal(-1) : tfCurriculumId;
	}

	public void setEndClientId(BigDecimal tfEndClientId) {
		this.ecid = tfEndClientId == null ? new BigDecimal(-1) : tfEndClientId;
	}

	public void setMarketingStatusId(BigDecimal tfMarketingStatusId) {
		this.msid = tfMarketingStatusId == null ? new BigDecimal(-1) : tfMarketingStatusId;
	}

	public static StatusInfo getTotals() {
		return totals;
	}

	public static void appendToMap(TfMarketingStatus tfMarketingStatus) {
		LogUtil.logger.info("Status: " + tfMarketingStatus.getTfMarketingStatusId());
		switch (tfMarketingStatus.getTfMarketingStatusId().intValueExact()) {
		case StatusInfo.MAPPED_TRAINING:
			totals.setTrainingMapped(totals.getTrainingMapped() + 1);
			break;
		case StatusInfo.MAPPED_RESERVED:
			totals.setReservedMapped(totals.getReservedMapped() + 1);
			break;
		case StatusInfo.MAPPED_SELECTED:
			totals.setSelectedMapped(totals.getSelectedMapped() + 1);
			break;
		case StatusInfo.MAPPED_CONFIRMED:
			totals.setConfirmedMapped(totals.getConfirmedMapped() + 1);
			break;
		case StatusInfo.MAPPED_DEPLOYED:
			totals.setDeployedMapped(totals.getDeployedMapped() + 1);
			break;
		case StatusInfo.UNMAPPED_TRAINING:
			totals.setTrainingUnmapped(totals.getTrainingUnmapped() + 1);
			break;
		case StatusInfo.UNMAPPED_OPEN:
			totals.setOpenUnmapped(totals.getOpenUnmapped() + 1);
			break;
		case StatusInfo.UNMAPPED_SELECTED:
			totals.setSelectedUnmapped(totals.getSelectedUnmapped() + 1);
			break;
		case StatusInfo.UNMAPPED_CONFIRMED:
			totals.setConfirmedUnmapped(totals.getConfirmedUnmapped() + 1);
			break;
		case StatusInfo.UNMAPPED_DEPLOYED:
			totals.setDeployedUnmapped(totals.getDeployedUnmapped() + 1);
			break;
		default:
			return;
		}
		totals.setName("Total Associates");
		LogUtil.logger.info(totals.getName() + ":\n" + totals.getTrainingMapped() + " " + totals.getReservedMapped() + " "
				+ totals.getSelectedMapped() + " " + totals.getConfirmedMapped() + " " + totals.getDeployedMapped() + "\n"
				+ totals.getTrainingUnmapped() + " " + totals.getOpenUnmapped() + " " + totals.getSelectedUnmapped() + " "
				+ totals.getConfirmedUnmapped() + " " + totals.getDeployedUnmapped());
	}
}

