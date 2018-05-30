package com.revature.request.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class InterviewFromClient implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5177141649099174290L;
	
	@XmlElement
	private Integer associateId;
	@XmlElement
	private Integer clientId;
	@XmlElement
	private Integer typeId;
	@XmlElement
	private long interviewDate;
	@XmlElement
	private String associateFeedback;
	@XmlElement
	private String interviewFeedback;
	@XmlElement
	private String jobDescription;
	@XmlElement
	private String dateSalesTeamIssued;
	@XmlElement
	private String dateAssociateIssued;
	@XmlElement
	private String flagAlert;
	@XmlElement
	private String reasonForFlag;
	@XmlElement
	private String clientFeedback;
	@XmlElement
	private String endClientId;
	
	
	public InterviewFromClient() {
	}
	
	public InterviewFromClient(Integer associateId, Integer clientId, Integer typeId) {
		super();
		this.associateId = associateId;
		this.clientId = clientId;
		this.typeId = typeId;
	}
	public Integer getAssociateId() {
		return associateId;
	}
	public void setAssociateId(Integer associateId) {
		this.associateId = associateId;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public long getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(long interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getInterviewFeedback() {
		return interviewFeedback;
	}
	public void setInterviewFeedback(String interviewFeedback) {
		this.interviewFeedback = interviewFeedback;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associateId == null) ? 0 : associateId.hashCode());
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + (int) (interviewDate ^ (interviewDate >>> 32));
		result = prime * result + ((interviewFeedback == null) ? 0 : interviewFeedback.hashCode());
		result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
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
		InterviewFromClient other = (InterviewFromClient) obj;
		if (associateId == null) {
			if (other.associateId != null)
				return false;
		} else if (!associateId.equals(other.associateId))
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (interviewDate != other.interviewDate)
			return false;
		if (interviewFeedback == null) {
			if (other.interviewFeedback != null)
				return false;
		} else if (!interviewFeedback.equals(other.interviewFeedback))
			return false;
		if (typeId == null) {
			if (other.typeId != null)
				return false;
		} else if (!typeId.equals(other.typeId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InterviewFromClient [associateId=" + associateId + ", clientId=" + clientId + ", typeId="
				+ typeId + ", interviewDate=" + interviewDate + ", interviewFeedback=" + interviewFeedback + "]";
	}
	
}
