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
	private Integer interviewId;
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
	private long dateSalesTeamIssued;
	@XmlElement
	private long dateAssociateIssued;
	@XmlElement
	private String flagAlert;
	@XmlElement
	private String reasonForFlag;
	@XmlElement
	private String clientFeedback;
	@XmlElement
	private String endClientId;
	@XmlElement
	private String questions;
	@XmlElement
	private Integer was24HRNotice;
	
	
	public InterviewFromClient() {
	}
	
	public InterviewFromClient(Integer interviewId, Integer clientId, Integer typeId) {
		super();
		this.interviewId = interviewId;
		this.clientId = clientId;
		this.typeId = typeId;
	}
	public Integer getInterviewId() {
		return interviewId;
	}
	public void setIntervieweId(Integer interviewId) {
		this.interviewId = interviewId;
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
		result = prime * result + ((interviewId == null) ? 0 : interviewId.hashCode());
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
		if (interviewId == null) {
			if (other.interviewId != null)
				return false;
		} else if (!interviewId.equals(other.interviewId))
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
		return "InterviewFromClient [associateId=" + interviewId + ", clientId=" + clientId + ", typeId="
				+ typeId + ", interviewDate=" + interviewDate + ", interviewFeedback=" + interviewFeedback + "]";
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getAssociateFeedback() {
		return associateFeedback;
	}

	public void setAssociateFeedback(String associateFeedback) {
		this.associateFeedback = associateFeedback;
	}

	public long getDateSalesTeamIssued() {
		return dateSalesTeamIssued;
	}

	public void setDateSalesTeamIssued(long dateSalesTeamIssued) {
		this.dateSalesTeamIssued = dateSalesTeamIssued;
	}

	public long getDateAssociateIssued() {
		return dateAssociateIssued;
	}

	public void setDateAssociateIssued(long dateAssociateIssued) {
		this.dateAssociateIssued = dateAssociateIssued;
	}

	public String getFlagAlert() {
		return flagAlert;
	}

	public void setFlagAlert(String flagAlert) {
		this.flagAlert = flagAlert;
	}

	public String getReasonForFlag() {
		return reasonForFlag;
	}

	public void setReasonForFlag(String reasonForFlag) {
		this.reasonForFlag = reasonForFlag;
	}

	public String getClientFeedback() {
		return clientFeedback;
	}

	public void setClientFeedback(String clientFeedback) {
		this.clientFeedback = clientFeedback;
	}

	public String getEndClientId() {
		return endClientId;
	}

	public void setEndClientId(String endClientId) {
		this.endClientId = endClientId;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public Integer getWas24HRNotice() {
		return was24HRNotice;
	}

	public void setWas24HRNotice(Integer was24HRNotice) {
		this.was24HRNotice = was24HRNotice;
	}
}
