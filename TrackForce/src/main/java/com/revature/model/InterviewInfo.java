package com.revature.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class InterviewInfo  implements Serializable, Comparable<InterviewInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4737517688902246944L;
	private Integer id;
	private AssociateInfo tfAssociate;
	private String tfClientName;
	private Integer typeId;
	private String typeName;
	private Timestamp tfInterviewDate;
	private String tfInterviewFeedback;
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public AssociateInfo getTfAssociate() {
		return tfAssociate;
	}



	public void setTfAssociate(AssociateInfo tfAssociate) {
		this.tfAssociate = tfAssociate;
	}



	public String getTfClientName() {
		return tfClientName;
	}



	public void setTfClientName(String tfEndClient) {
		this.tfClientName = tfEndClient;
	}
	
	public void setTfEndClientName(String name) {
		this.tfClientName = name;
	}



	public Integer getTypeId() {
		return typeId;
	}



	public void setTypeId(Integer typeId) {
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
		return this.id-o.getId();
	}

	
}
