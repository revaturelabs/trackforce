package com.revature.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class InterviewInfo implements Serializable, Comparable<InterviewInfo> {

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

    // 1804
    private String associateFeedback;
    private String clientFeedback;
    private String jobDescription;
    private Timestamp dateSalesIssued;
    private Timestamp dateAssociateIssued;
    private Integer isInterviewFlagged;
    private String flagReason;
    private Integer isClientFeedbackVisible;
	private Integer tfWas24HRNotice;

    public Integer getTfWas24HRNotice() {
		return tfWas24HRNotice;
	}


	public void setTfWas24HRNotice(Integer tfWas24HRNotice) {
		this.tfWas24HRNotice = tfWas24HRNotice;
	}


	public String getAssociateFeedback() {
		return associateFeedback;
	}


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
        return this.id - o.getId();
    }

    public void setAssociateFeedback(String associateFeedback) {
        this.associateFeedback = associateFeedback;
    }

    public String getClientFeedback() {
        return clientFeedback;
    }

    public void setClientFeedback(String clientFeedback) {
        this.clientFeedback = clientFeedback;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Timestamp getDateSalesIssued() {
        return dateSalesIssued;
    }

    public void setDateSalesIssued(Timestamp dateSalesIssued) {
        this.dateSalesIssued = dateSalesIssued;
    }

    public Timestamp getDateAssociateIssued() {
        return dateAssociateIssued;
    }

    public void setDateAssociateIssued(Timestamp dateAssociateIssued) {
        this.dateAssociateIssued = dateAssociateIssued;
    }

    public Integer getIsInterviewFlagged() {
        return isInterviewFlagged;
    }

    public void setIsInterviewFlagged(Integer isInterviewFlagged) {
        this.isInterviewFlagged = isInterviewFlagged;
    }

    public String getFlagReason() {
        return flagReason;
    }

    public void setFlagReason(String flagReason) {
        this.flagReason = flagReason;
    }

    public Integer getIsClientFeedbackVisible() {
        return isClientFeedbackVisible;
    }

    public void setIsClientFeedbackVisible(Integer isClientFeedbackVisible) {
        this.isClientFeedbackVisible = isClientFeedbackVisible;
    }
}
