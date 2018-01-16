package com.revature.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class InterviewInfo  implements Serializable, Comparable<InterviewInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4737517688902246944L;
	private BigDecimal id;
	private AssociateInfo tfAssociate;
	private EndClientInfo tfEndClient;
	private BigDecimal typeId;
	private String typeName;
	private Timestamp tfInterviewDate;
	private String tfInterviewFeedback;
	
	public BigDecimal getId() {
		return id;
	}



	public void setId(BigDecimal id) {
		this.id = id;
	}



	public AssociateInfo getTfAssociate() {
		return tfAssociate;
	}



	public void setTfAssociate(AssociateInfo tfAssociate) {
		this.tfAssociate = tfAssociate;
	}



	public EndClientInfo getTfEndClient() {
		return tfEndClient;
	}



	public void setTfEndClient(EndClientInfo tfEndClient) {
		this.tfEndClient = tfEndClient;
	}



	public BigDecimal getTypeId() {
		return typeId;
	}



	public void setTypeId(BigDecimal typeId) {
		this.typeId = typeId;
	}



	public String getTypeName() {
		return typeName;
	}



	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}



	public Timestamp getTfInterviewDate() {
		return tfInterviewDate;
	}



	public void setTfInterviewDate(Timestamp tfInterviewDate) {
		this.tfInterviewDate = tfInterviewDate;
	}



	public String getTfInterviewFeedback() {
		return tfInterviewFeedback;
	}



	public void setTfInterviewFeedback(String tfInterviewFeedback) {
		this.tfInterviewFeedback = tfInterviewFeedback;
	}



	@Override
	public int compareTo(InterviewInfo o) {
		return this.id.subtract(o.getId()).intValueExact();
	}

	
}
