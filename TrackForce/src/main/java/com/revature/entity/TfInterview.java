package com.revature.entity;
// Generated Nov 7, 2017 9:24:46 PM by Hibernate Tools 5.2.5.Final

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@XmlRootElement
@Entity
@Table(name = "TF_INTERVIEW", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TfInterview implements java.io.Serializable {

	private static final long serialVersionUID = -4148475604579144144L;

	@XmlElement
	@Id
	@Column(name = "TF_INTERVIEW_ID", unique = true)
	private Integer tfInterviewId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TF_ASSOCIATE_ID")
	private TfAssociate tfAssociate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TF_CLIENT_ID")
	private TfClient tfClient;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TF_END_CLIENT_ID")
	private TfEndClient tfEndClient;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TF_INTERVIEW_TYPE_ID")
	private TfInterviewType tfInterviewType;

	@XmlElement
	@Column(name = "TF_INTERVIEW_DATE")
	private Timestamp tfInterviewDate;

	@XmlElement
	@Column(name = "TF_ASSOCIATE_FEEDBACK", length = 2000)
	private String tfAssociateFeedback;
	
	@XmlElement
	@Column(name = "TF_QUESTION_GIVEN", length = 3500)
	private String tfQuestionGiven;
	
	@XmlElement
	@Column(name = "TF_CLIENT_FEEDBACK", length = 2500)
	private String tfClientFeedback;
	
	@XmlElement
	@Column(name = "TF_JOB_DESCRIPTION", length = 2000)
	private String tfJobDescription;
	
	@XmlElement
	@Column(name = "TF_DATE_SALES_ISSUED")
	private Timestamp tfDateSalesIssued;
	
	@XmlElement
	@Column(name = "TF_DATE_ASSOCIATE_ISSUED")
	private Timestamp tfDateAssociateIssued;
	
	@XmlElement
	@Column(name = "TF_WAS_24HR_NOTICE")
	private Integer tfWas24HRNotice;	
	
	@XmlElement
	@Column(name = "TF_IS_INTERVIEW_FLAGGED")
	private Integer tfIsInterviewFlagged = 0;
	
	@XmlElement
	@Column(name = "TF_FLAG_REASON", length = 300)
	private String tfFlagReason;
	
	@XmlElement
	@Column(name = "TF_IS_CLIENT_FEEDBACK_VISIABLE")
	private Integer tfIsClientFeedbackVisible = 0;
	
	

	public TfInterview() {
	}
	/**
	 * @param tfInterviewId
	 * @param tfAssociate
	 * @param tfClient
	 * @param tfEndClient
	 * @param tfInterviewType
	 * @param tfInterviewDate
	 * @param tfAssociateFeedback
	 * @param tfQuestionGiven
	 * @param tfClientFeedback
	 * @param tfJobDescription
	 * @param tfDateSalesIssued
	 * @param tfDateAssociateIssued
	 * @param tfWas24HRNotice
	 * @param tfIsInterviewFlagged
	 * @param tfFlagReason
	 * @param tfIsClientFeedbackVisible
	 */
	public TfInterview(Integer tfInterviewId, TfAssociate tfAssociate, TfClient tfClient, TfEndClient tfEndClient,
			TfInterviewType tfInterviewType, Timestamp tfInterviewDate, String tfAssociateFeedback,
			String tfQuestionGiven, String tfClientFeedback, String tfJobDescription, Timestamp tfDateSalesIssued,
			Timestamp tfDateAssociateIssued, Integer tfWas24HRNotice, Integer tfIsInterviewFlagged, String tfFlagReason,
			Integer tfIsClientFeedbackVisible) {
		super();
		this.tfInterviewId = tfInterviewId;
		this.tfAssociate = tfAssociate;
		this.tfClient = tfClient;
		this.tfEndClient = tfEndClient;
		this.tfInterviewType = tfInterviewType;
		this.tfInterviewDate = tfInterviewDate;
		this.tfAssociateFeedback = tfAssociateFeedback;
		this.tfQuestionGiven = tfQuestionGiven;
		this.tfClientFeedback = tfClientFeedback;
		this.tfJobDescription = tfJobDescription;
		this.tfDateSalesIssued = tfDateSalesIssued;
		this.tfDateAssociateIssued = tfDateAssociateIssued;
		this.tfWas24HRNotice = tfWas24HRNotice;
		this.tfIsInterviewFlagged = tfIsInterviewFlagged;
		this.tfFlagReason = tfFlagReason;
		this.tfIsClientFeedbackVisible = tfIsClientFeedbackVisible;
	}


	public TfInterview(Integer tfInterviewId) {
		this.tfInterviewId = tfInterviewId;
	}


	public Integer getTfInterviewId() {
		return this.tfInterviewId;
	}

	
	public void setTfInterviewId(Integer tfInterviewId) {
		this.tfInterviewId = tfInterviewId;
	}

	public TfAssociate getTfAssociate() {
		return this.tfAssociate;
	}

	public void setTfAssociate(TfAssociate tfAssociate) {
		this.tfAssociate = tfAssociate;
	}

	public TfClient getTfClient() {
		return this.tfClient;
	}

	public void setTfClient(TfClient tfClient) {
		this.tfClient = tfClient;
	}

	public TfEndClient getTfEndClient() {
		return this.tfEndClient;
	}

	public void setTfEndClient(TfEndClient tfEndClient) {
		this.tfEndClient = tfEndClient;
	}

	public TfInterviewType getTfInterviewType() {
		return this.tfInterviewType;
	}

	public void setTfInterviewType(TfInterviewType tfInterviewType) {
		this.tfInterviewType = tfInterviewType;
	}

	public Timestamp getTfInterviewDate() {
		return this.tfInterviewDate;
	}

	public void setTfInterviewDate(Timestamp tfInterviewDate) {
		this.tfInterviewDate = tfInterviewDate;
	}

	/**
	 * @return the tfAssociateFeedback
	 */
	public String getTfAssociateFeedback() {
		return tfAssociateFeedback;
	}

	/**
	 * @param tfAssociateFeedback the tfAssociateFeedback to set
	 */
	public void setTfAssociateFeedback(String tfAssociateFeedback) {
		this.tfAssociateFeedback = tfAssociateFeedback;
	}

	/**
	 * @return the tfClientFeedback
	 */
	public String getTfClientFeedback() {
		return tfClientFeedback;
	}

	/**
	 * @param tfClientFeedback the tfClientFeedback to set
	 */
	public void setTfClientFeedback(String tfClientFeedback) {
		this.tfClientFeedback = tfClientFeedback;
	}

	/**
	 * @return the tfJobDescription
	 */
	public String getTfJobDescription() {
		return tfJobDescription;
	}

	/**
	 * @param tfJobDescription the tfJobDescription to set
	 */
	public void setTfJobDescription(String tfJobDescription) {
		this.tfJobDescription = tfJobDescription;
	}

	/**
	 * @return the tfDateSalesIssued
	 */
	public Timestamp getTfDateSalesIssued() {
		return tfDateSalesIssued;
	}

	/**
	 * @param tfDateSalesIssued the tfDateSalesIssued to set
	 */
	public void setTfDateSalesIssued(Timestamp tfDateSalesIssued) {
		this.tfDateSalesIssued = tfDateSalesIssued;
	}

	/**
	 * @return the tfDateAssociateIssued
	 */
	public Timestamp getTfDateAssociateIssued() {
		return tfDateAssociateIssued;
	}

	/**
	 * @param tfDateAssociateIssued the tfDateAssociateIssued to set
	 */
	public void setTfDateAssociateIssued(Timestamp tfDateAssociateIssued) {
		this.tfDateAssociateIssued = tfDateAssociateIssued;
	}

	/**
	 * @return the tfIsInterviewFlagged
	 */
	public Integer getTfIsInterviewFlagged() {
		return tfIsInterviewFlagged;
	}

	/**
	 * @param tfIsInterviewFlagged the tfIsInterviewFlagged to set
	 */
	public void setTfIsInterviewFlagged(Integer tfIsInterviewFlagged) {
		this.tfIsInterviewFlagged = tfIsInterviewFlagged;
	}

	/**
	 * @return the tfFlagReason
	 */
	public String getTfFlagReason() {
		return tfFlagReason;
	}

	/**
	 * @param tfFlagReason the tfFlagReason to set
	 */
	public void setTfFlagReason(String tfFlagReason) {
		this.tfFlagReason = tfFlagReason;
	}

	/**
	 * @return the tfIsClientFeedbackVisiable
	 */
	public Integer getTfIsClientFeedbackVisible() {
		return tfIsClientFeedbackVisible;
	}

	/**
	 * @param tfIsClientFeedbackVisible the tfIsClientFeedbackVisible to set
	 */
	public void setTfIsClientFeedbackVisible(Integer tfIsClientFeedbackVisible) {
		this.tfIsClientFeedbackVisible = tfIsClientFeedbackVisible;
	}
	
	/**
	 * @return the tfQuestionGiven
	 */
	public String getTfQuestionGiven() {
		return tfQuestionGiven;
	}
	/**
	 * @param tfQuestionGiven the tfQuestionGiven to set
	 */
	public void setTfQuestionGiven(String tfQuestionGiven) {
		this.tfQuestionGiven = tfQuestionGiven;
	}
	/**
	 * @return the tfWas24HRNotice
	 */
	public Integer getTfWas24HRNotice() {
		return tfWas24HRNotice;
	}
	/**
	 * @param tfWas24HRNotice the tfWas24HRNotice to set
	 */
	public void setTfWas24HRNotice(Integer tfWas24HRNotice) {
		this.tfWas24HRNotice = tfWas24HRNotice;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tfAssociate == null) ? 0 : tfAssociate.hashCode());
		result = prime * result + ((tfAssociateFeedback == null) ? 0 : tfAssociateFeedback.hashCode());
		result = prime * result + ((tfClient == null) ? 0 : tfClient.hashCode());
		result = prime * result + ((tfClientFeedback == null) ? 0 : tfClientFeedback.hashCode());
		result = prime * result + ((tfDateAssociateIssued == null) ? 0 : tfDateAssociateIssued.hashCode());
		result = prime * result + ((tfDateSalesIssued == null) ? 0 : tfDateSalesIssued.hashCode());
		result = prime * result + ((tfEndClient == null) ? 0 : tfEndClient.hashCode());
		result = prime * result + ((tfFlagReason == null) ? 0 : tfFlagReason.hashCode());
		result = prime * result + ((tfInterviewDate == null) ? 0 : tfInterviewDate.hashCode());
		result = prime * result + ((tfInterviewId == null) ? 0 : tfInterviewId.hashCode());
		result = prime * result + ((tfInterviewType == null) ? 0 : tfInterviewType.hashCode());
		result = prime * result + ((tfIsClientFeedbackVisible == null) ? 0 : tfIsClientFeedbackVisible.hashCode());
		result = prime * result + ((tfIsInterviewFlagged == null) ? 0 : tfIsInterviewFlagged.hashCode());
		result = prime * result + ((tfJobDescription == null) ? 0 : tfJobDescription.hashCode());
		result = prime * result + ((tfQuestionGiven == null) ? 0 : tfQuestionGiven.hashCode());
		result = prime * result + ((tfWas24HRNotice == null) ? 0 : tfWas24HRNotice.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TfInterview other = (TfInterview) obj;
		if (tfAssociate == null) {
			if (other.tfAssociate != null)
				return false;
		} else if (!tfAssociate.equals(other.tfAssociate))
			return false;
		if (tfAssociateFeedback == null) {
			if (other.tfAssociateFeedback != null)
				return false;
		} else if (!tfAssociateFeedback.equals(other.tfAssociateFeedback))
			return false;
		if (tfClient == null) {
			if (other.tfClient != null)
				return false;
		} else if (!tfClient.equals(other.tfClient))
			return false;
		if (tfClientFeedback == null) {
			if (other.tfClientFeedback != null)
				return false;
		} else if (!tfClientFeedback.equals(other.tfClientFeedback))
			return false;
		if (tfDateAssociateIssued == null) {
			if (other.tfDateAssociateIssued != null)
				return false;
		} else if (!tfDateAssociateIssued.equals(other.tfDateAssociateIssued))
			return false;
		if (tfDateSalesIssued == null) {
			if (other.tfDateSalesIssued != null)
				return false;
		} else if (!tfDateSalesIssued.equals(other.tfDateSalesIssued))
			return false;
		if (tfEndClient == null) {
			if (other.tfEndClient != null)
				return false;
		} else if (!tfEndClient.equals(other.tfEndClient))
			return false;
		if (tfFlagReason == null) {
			if (other.tfFlagReason != null)
				return false;
		} else if (!tfFlagReason.equals(other.tfFlagReason))
			return false;
		if (tfInterviewDate == null) {
			if (other.tfInterviewDate != null)
				return false;
		} else if (!tfInterviewDate.equals(other.tfInterviewDate))
			return false;
		if (tfInterviewId == null) {
			if (other.tfInterviewId != null)
				return false;
		} else if (!tfInterviewId.equals(other.tfInterviewId))
			return false;
		if (tfInterviewType == null) {
			if (other.tfInterviewType != null)
				return false;
		} else if (!tfInterviewType.equals(other.tfInterviewType))
			return false;
		if (tfIsClientFeedbackVisible == null) {
			if (other.tfIsClientFeedbackVisible != null)
				return false;
		} else if (!tfIsClientFeedbackVisible.equals(other.tfIsClientFeedbackVisible))
			return false;
		if (tfIsInterviewFlagged == null) {
			if (other.tfIsInterviewFlagged != null)
				return false;
		} else if (!tfIsInterviewFlagged.equals(other.tfIsInterviewFlagged))
			return false;
		if (tfJobDescription == null) {
			if (other.tfJobDescription != null)
				return false;
		} else if (!tfJobDescription.equals(other.tfJobDescription))
			return false;
		if (tfQuestionGiven == null) {
			if (other.tfQuestionGiven != null)
				return false;
		} else if (!tfQuestionGiven.equals(other.tfQuestionGiven))
			return false;
		if (tfWas24HRNotice == null) {
			if (other.tfWas24HRNotice != null)
				return false;
		} else if (!tfWas24HRNotice.equals(other.tfWas24HRNotice))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TfInterview [tfInterviewId=" + tfInterviewId + ", tfClient=" + tfClient + ", tfEndClient=" + tfEndClient
				+ ", tfInterviewType=" + tfInterviewType + ", tfInterviewDate=" + tfInterviewDate
				+ ", tfAssociateFeedback=" + tfAssociateFeedback + ", tfQuestionGiven=" + tfQuestionGiven
				+ ", tfClientFeedback=" + tfClientFeedback + ", tfJobDescription=" + tfJobDescription
				+ ", tfDateSalesIssued=" + tfDateSalesIssued + ", tfDateAssociateIssued=" + tfDateAssociateIssued
				+ ", tfWas24HRNotice=" + tfWas24HRNotice + ", tfIsInterviewFlagged=" + tfIsInterviewFlagged
				+ ", tfFlagReason=" + tfFlagReason + ", tfIsClientFeedbackVisible=" + tfIsClientFeedbackVisible + "]";
	}
	
	

}
