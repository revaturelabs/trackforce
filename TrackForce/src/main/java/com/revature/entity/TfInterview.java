package com.revature.entity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

/** @version v6.18.06.13 */
@XmlRootElement
@Entity
@Table(name = "TF_INTERVIEW", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="TrackForce")
public class TfInterview implements java.io.Serializable 
{
	private static final long serialVersionUID = -4148475604579144144L;

	@XmlElement
	@Id
	@Column(name = "TF_INTERVIEW_ID", unique = true)
	@SequenceGenerator(sequenceName = "InterviewId_Seq2", name ="InterviewIdSeq2", initialValue=500, schema= "ADMIN")
	@GeneratedValue(generator ="InterviewIdSeq2", strategy = GenerationType.SEQUENCE)
	private Integer id;

	//@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TF_ASSOCIATE_ID")
	private TfAssociate associate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TF_CLIENT_ID")
	private TfClient client;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TF_END_CLIENT_ID")
	private TfEndClient endClient;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TF_INTERVIEW_TYPE_ID")
	private TfInterviewType interviewType;

	@XmlElement
	@Column(name = "TF_INTERVIEW_DATE")
	private Timestamp interviewDate;

	@XmlElement
	@Column(name = "TF_ASSOCIATE_FEEDBACK", length = 2000)
	private String associateFeedback;
	
	@XmlElement
	@Column(name = "TF_QUESTION_GIVEN", length = 3500)
	private String questionGiven;
	
	@XmlElement
	@Column(name = "TF_CLIENT_FEEDBACK", length = 2500)
	private String clientFeedback;
	
	@XmlElement
	@Column(name = "TF_JOB_DESCRIPTION", length = 2000)
	private String jobDescription;
	
	@XmlElement
	@Column(name = "TF_DATE_SALES_ISSUED")
	private Timestamp dateSalesIssued;
	
	@XmlElement
	@Column(name = "TF_DATE_ASSOCIATE_ISSUED")
	private Timestamp dateAssociateIssued;
	
	@XmlElement
	@Column(name = "TF_WAS_24HR_NOTICE")
	private Integer was24HRNotice;	
	
	@XmlElement
	@Column(name = "TF_IS_INTERVIEW_FLAGGED")
	private Integer isInterviewFlagged = 0;
	
	@XmlElement
	@Column(name = "TF_FLAG_REASON", length = 300)
	private String flagReason;
	
	@XmlElement
	@Column(name = "TF_IS_CLIENT_FEEDBACK_VISIABLE")
	private Integer isClientFeedbackVisible = 0;
	
	//----------------------------------
	
	public TfInterview() { super(); }

	public TfInterview(Integer id, TfAssociate associate, TfClient client, TfEndClient endClient,
			TfInterviewType interviewType, Timestamp interviewDate, String associateFeedback, String questionGiven,
			String clientFeedback, String jobDescription, Timestamp dateSalesIssued, Timestamp dateAssociateIssued,
			Integer was24hrNotice, Integer isInterviewFlagged, String flagReason, Integer isClientFeedbackVisible) {
		super();
		this.id = id;
		this.associate = associate;
		this.client = client;
		this.endClient = endClient;
		this.interviewType = interviewType;
		this.interviewDate = interviewDate;
		this.associateFeedback = associateFeedback;
		this.questionGiven = questionGiven;
		this.clientFeedback = clientFeedback;
		this.jobDescription = jobDescription;
		this.dateSalesIssued = dateSalesIssued;
		this.dateAssociateIssued = dateAssociateIssued;
		this.was24HRNotice = was24hrNotice;
		this.isInterviewFlagged = isInterviewFlagged;
		this.flagReason = flagReason;
		this.isClientFeedbackVisible = isClientFeedbackVisible;
	}

	//----------------------------------

	public Integer getId() { return id; }

	public TfAssociate getAssociate() { return associate; }

	public void setAssociate(TfAssociate associate) { this.associate = associate; }

	public TfClient getClient() { return client; }

	public void setClient(TfClient client) { this.client = client; }

	public TfEndClient getEndClient() { return endClient; }

	public void setEndClient(TfEndClient endClient) { this.endClient = endClient; }

	public TfInterviewType getInterviewType() { return interviewType; }

	public void setInterviewType(TfInterviewType interviewType) { this.interviewType = interviewType; }

	public Timestamp getInterviewDate() { return interviewDate; }

	public void setInterviewDate(Timestamp interviewDate) { this.interviewDate = interviewDate; }

	public String getAssociateFeedback() { return associateFeedback; }

	public void setAssociateFeedback(String associateFeedback) { this.associateFeedback = associateFeedback; }

	public String getQuestionGiven() { return questionGiven; }

	public void setQuestionGiven(String questionGiven) { this.questionGiven = questionGiven; }

	public String getClientFeedback() { return clientFeedback; }

	public void setClientFeedback(String clientFeedback) { this.clientFeedback = clientFeedback; }

	public String getJobDescription() { return jobDescription; }

	public void setJobDescription(String jobDescription) { this.jobDescription = jobDescription; }

	public Timestamp getDateSalesIssued() { return dateSalesIssued; }

	public void setDateSalesIssued(Timestamp dateSalesIssued) { this.dateSalesIssued = dateSalesIssued; }

	public Timestamp getDateAssociateIssued() { return dateAssociateIssued; }

	public void setDateAssociateIssued(Timestamp dateAssociateIssued) { this.dateAssociateIssued = dateAssociateIssued; }

	public Integer getWas24HRNotice() { return was24HRNotice; }

	public void setWas24HRNotice(Integer was24hrNotice) { was24HRNotice = was24hrNotice; }

	public Integer getIsInterviewFlagged() { return isInterviewFlagged; }

	public void setIsInterviewFlagged(Integer isInterviewFlagged) { this.isInterviewFlagged = isInterviewFlagged; }

	public String getFlagReason() { return flagReason; }

	public void setFlagReason(String flagReason) { this.flagReason = flagReason; }

	public Integer getIsClientFeedbackVisible() { return isClientFeedbackVisible; }

	public void setIsClientFeedbackVisible(Integer isClientFeedbackVisible) 
	{ this.isClientFeedbackVisible = isClientFeedbackVisible; }

	public static long getSerialversionuid() { return serialVersionUID; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associate == null) ? 0 : associate.hashCode());
		result = prime * result + ((associateFeedback == null) ? 0 : associateFeedback.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((clientFeedback == null) ? 0 : clientFeedback.hashCode());
		result = prime * result + ((dateAssociateIssued == null) ? 0 : dateAssociateIssued.hashCode());
		result = prime * result + ((dateSalesIssued == null) ? 0 : dateSalesIssued.hashCode());
		result = prime * result + ((endClient == null) ? 0 : endClient.hashCode());
		result = prime * result + ((flagReason == null) ? 0 : flagReason.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interviewDate == null) ? 0 : interviewDate.hashCode());
		result = prime * result + ((interviewType == null) ? 0 : interviewType.hashCode());
		result = prime * result + ((isClientFeedbackVisible == null) ? 0 : isClientFeedbackVisible.hashCode());
		result = prime * result + ((isInterviewFlagged == null) ? 0 : isInterviewFlagged.hashCode());
		result = prime * result + ((jobDescription == null) ? 0 : jobDescription.hashCode());
		result = prime * result + ((questionGiven == null) ? 0 : questionGiven.hashCode());
		result = prime * result + ((was24HRNotice == null) ? 0 : was24HRNotice.hashCode());
		return result;
	}

	/** @param obj the reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj
	 * argument; {@code false} otherwise. */
	@Override
	public boolean equals(Object obj) { return super.equals(obj); }

	@Override
	public String toString() {
		return "TfInterview [id=" + id + ", associate=" + associate + ", client=" + client + ", endClient=" + endClient
				+ ", interviewType=" + interviewType + ", interviewDate=" + interviewDate + ", associateFeedback="
				+ associateFeedback + ", questionGiven=" + questionGiven + ", clientFeedback=" + clientFeedback
				+ ", jobDescription=" + jobDescription + ", dateSalesIssued=" + dateSalesIssued
				+ ", dateAssociateIssued=" + dateAssociateIssued + ", was24HRNotice=" + was24HRNotice
				+ ", isInterviewFlagged=" + isInterviewFlagged + ", flagReason=" + flagReason
				+ ", isClientFeedbackVisible=" + isClientFeedbackVisible + "]";
	}
}