package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the TF_ASSOCIATE database table.
 * 
 */
@Entity
@Table(name="TF_ASSOCIATE")
@NamedQuery(name="TfAssociate.findAll", query="SELECT t FROM TfAssociate t")
public class TfAssociate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TF_ASSOCIATE_ID")
	private long tfAssociateId;

	@Column(name="TF_ASSOCIATE_FIRST_NAME")
	private String tfAssociateFirstName;

	@Column(name="TF_ASSOCIATE_LAST_NAME")
	private String tfAssociateLastName;

	@Column(name="TF_CLIENT_START_DATE")
	private Timestamp tfClientStartDate;

	@Column(name="TF_ISAPPROVED")
	private BigDecimal tfIsapproved;

	//bi-directional many-to-one association to TfBatch
	@ManyToOne
	@JoinColumn(name="TF_BATCH_ID")
	private TfBatch tfBatch;

	//bi-directional many-to-one association to TfClient
	@ManyToOne
	@JoinColumn(name="TF_CLIENT_ID")
	private TfClient tfClient;

	//bi-directional many-to-one association to TfEndClient
	@ManyToOne
	@JoinColumn(name="TF_END_CLIENT_ID")
	private TfEndClient tfEndClient;

	//bi-directional many-to-one association to TfMarketingStatus
	@ManyToOne
	@JoinColumn(name="TF_MARKETING_STATUS_ID")
	private TfMarketingStatus tfMarketingStatus;

	//bi-directional many-to-one association to TfInterview
	@OneToMany(mappedBy="tfAssociate")
	private List<TfInterview> tfInterviews;

	//bi-directional many-to-one association to TfPlacement
	@OneToMany(mappedBy="tfAssociate")
	private List<TfPlacement> tfPlacements;

	//bi-directional many-to-one association to TfUser
	@OneToMany(mappedBy="tfAssociate")
	private List<TfUser> tfUsers;

	public TfAssociate() {
	}

	public long getTfAssociateId() {
		return this.tfAssociateId;
	}

	public void setTfAssociateId(long tfAssociateId) {
		this.tfAssociateId = tfAssociateId;
	}

	public String getTfAssociateFirstName() {
		return this.tfAssociateFirstName;
	}

	public void setTfAssociateFirstName(String tfAssociateFirstName) {
		this.tfAssociateFirstName = tfAssociateFirstName;
	}

	public String getTfAssociateLastName() {
		return this.tfAssociateLastName;
	}

	public void setTfAssociateLastName(String tfAssociateLastName) {
		this.tfAssociateLastName = tfAssociateLastName;
	}

	public Timestamp getTfClientStartDate() {
		return this.tfClientStartDate;
	}

	public void setTfClientStartDate(Timestamp tfClientStartDate) {
		this.tfClientStartDate = tfClientStartDate;
	}

	public BigDecimal getTfIsapproved() {
		return this.tfIsapproved;
	}

	public void setTfIsapproved(BigDecimal tfIsapproved) {
		this.tfIsapproved = tfIsapproved;
	}

	public TfBatch getTfBatch() {
		return this.tfBatch;
	}

	public void setTfBatch(TfBatch tfBatch) {
		this.tfBatch = tfBatch;
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

	public TfMarketingStatus getTfMarketingStatus() {
		return this.tfMarketingStatus;
	}

	public void setTfMarketingStatus(TfMarketingStatus tfMarketingStatus) {
		this.tfMarketingStatus = tfMarketingStatus;
	}

	public List<TfInterview> getTfInterviews() {
		return this.tfInterviews;
	}

	public void setTfInterviews(List<TfInterview> tfInterviews) {
		this.tfInterviews = tfInterviews;
	}

	public TfInterview addTfInterview(TfInterview tfInterview) {
		getTfInterviews().add(tfInterview);
		tfInterview.setTfAssociate(this);

		return tfInterview;
	}

	public TfInterview removeTfInterview(TfInterview tfInterview) {
		getTfInterviews().remove(tfInterview);
		tfInterview.setTfAssociate(null);

		return tfInterview;
	}

	public List<TfPlacement> getTfPlacements() {
		return this.tfPlacements;
	}

	public void setTfPlacements(List<TfPlacement> tfPlacements) {
		this.tfPlacements = tfPlacements;
	}

	public TfPlacement addTfPlacement(TfPlacement tfPlacement) {
		getTfPlacements().add(tfPlacement);
		tfPlacement.setTfAssociate(this);

		return tfPlacement;
	}

	public TfPlacement removeTfPlacement(TfPlacement tfPlacement) {
		getTfPlacements().remove(tfPlacement);
		tfPlacement.setTfAssociate(null);

		return tfPlacement;
	}

	public List<TfUser> getTfUsers() {
		return this.tfUsers;
	}

	public void setTfUsers(List<TfUser> tfUsers) {
		this.tfUsers = tfUsers;
	}

	public TfUser addTfUser(TfUser tfUser) {
		getTfUsers().add(tfUser);
		tfUser.setTfAssociate(this);

		return tfUser;
	}

	public TfUser removeTfUser(TfUser tfUser) {
		getTfUsers().remove(tfUser);
		tfUser.setTfAssociate(null);

		return tfUser;
	}

}