package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the TF_INTERVIEW database table.
 * 
 */
@Entity
@Table(name="TF_INTERVIEW")
@NamedQuery(name="TfInterview.findAll", query="SELECT t FROM TfInterview t")
public class TfInterview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TF_INTERVIEW_ID")
	private long tfInterviewId;

	@Column(name="TF_ASSOCIATE_FEEDBACK")
	private String tfAssociateFeedback;

	@Column(name="TF_CLIENT_FEEDBACK")
	private String tfClientFeedback;

	@Column(name="TF_DATE_ASSOCIATE_ISSUED")
	private Timestamp tfDateAssociateIssued;

	@Column(name="TF_DATE_SALES_ISSUED")
	private Timestamp tfDateSalesIssued;

	@Column(name="TF_FLAG_REASON")
	private String tfFlagReason;

	@Column(name="TF_INTERVIEW_DATE")
	private Timestamp tfInterviewDate;

	@Column(name="TF_IS_CLIENT_FEEDBACK_VISIABLE")
	private BigDecimal tfIsClientFeedbackVisiable;

	@Column(name="TF_IS_INTERVIEW_FLAGGED")
	private BigDecimal tfIsInterviewFlagged;

	@Column(name="TF_JOB_DESCRIPTION")
	private String tfJobDescription;

	@Column(name="TF_QUESTION_GIVEN")
	private String tfQuestionGiven;

	@Column(name="TF_WAS_24HR_NOTICE")
	private BigDecimal tfWas24hrNotice;

	//bi-directional many-to-one association to TfAssociate
	@ManyToOne
	@JoinColumn(name="TF_ASSOCIATE_ID")
	private TfAssociate tfAssociate;

	//bi-directional many-to-one association to TfClient
	@ManyToOne
	@JoinColumn(name="TF_CLIENT_ID")
	private TfClient tfClient;

	//bi-directional many-to-one association to TfEndClient
	@ManyToOne
	@JoinColumn(name="TF_END_CLIENT_ID")
	private TfEndClient tfEndClient;

	//bi-directional many-to-one association to TfInterviewType
	@ManyToOne
	@JoinColumn(name="TF_INTERVIEW_TYPE_ID")
	private TfInterviewType tfInterviewType;

	public TfInterview() {
	}

	public long getTfInterviewId() {
		return this.tfInterviewId;
	}

	public void setTfInterviewId(long tfInterviewId) {
		this.tfInterviewId = tfInterviewId;
	}

	public String getTfAssociateFeedback() {
		return this.tfAssociateFeedback;
	}

	public void setTfAssociateFeedback(String tfAssociateFeedback) {
		this.tfAssociateFeedback = tfAssociateFeedback;
	}

	public String getTfClientFeedback() {
		return this.tfClientFeedback;
	}

	public void setTfClientFeedback(String tfClientFeedback) {
		this.tfClientFeedback = tfClientFeedback;
	}

	public Timestamp getTfDateAssociateIssued() {
		return this.tfDateAssociateIssued;
	}

	public void setTfDateAssociateIssued(Timestamp tfDateAssociateIssued) {
		this.tfDateAssociateIssued = tfDateAssociateIssued;
	}

	public Timestamp getTfDateSalesIssued() {
		return this.tfDateSalesIssued;
	}

	public void setTfDateSalesIssued(Timestamp tfDateSalesIssued) {
		this.tfDateSalesIssued = tfDateSalesIssued;
	}

	public String getTfFlagReason() {
		return this.tfFlagReason;
	}

	public void setTfFlagReason(String tfFlagReason) {
		this.tfFlagReason = tfFlagReason;
	}

	public Timestamp getTfInterviewDate() {
		return this.tfInterviewDate;
	}

	public void setTfInterviewDate(Timestamp tfInterviewDate) {
		this.tfInterviewDate = tfInterviewDate;
	}

	public BigDecimal getTfIsClientFeedbackVisiable() {
		return this.tfIsClientFeedbackVisiable;
	}

	public void setTfIsClientFeedbackVisiable(BigDecimal tfIsClientFeedbackVisiable) {
		this.tfIsClientFeedbackVisiable = tfIsClientFeedbackVisiable;
	}

	public BigDecimal getTfIsInterviewFlagged() {
		return this.tfIsInterviewFlagged;
	}

	public void setTfIsInterviewFlagged(BigDecimal tfIsInterviewFlagged) {
		this.tfIsInterviewFlagged = tfIsInterviewFlagged;
	}

	public String getTfJobDescription() {
		return this.tfJobDescription;
	}

	public void setTfJobDescription(String tfJobDescription) {
		this.tfJobDescription = tfJobDescription;
	}

	public String getTfQuestionGiven() {
		return this.tfQuestionGiven;
	}

	public void setTfQuestionGiven(String tfQuestionGiven) {
		this.tfQuestionGiven = tfQuestionGiven;
	}

	public BigDecimal getTfWas24hrNotice() {
		return this.tfWas24hrNotice;
	}

	public void setTfWas24hrNotice(BigDecimal tfWas24hrNotice) {
		this.tfWas24hrNotice = tfWas24hrNotice;
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

}