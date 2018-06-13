package com.revature.entity;
// Generated Nov 7, 2017 9:24:46 PM by Hibernate Tools 5.2.5.Final

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



/**
 * @category Integer tfAssociateId
 * @category TfUser tfUser
 * @category TfBatch tfBatch
 * @category TfMarketingStatus tfMarketingStatus
 * @category TfClient tfClient
 * @category TfEndClient tfEndClient
 * @category String tfAssociateFirstName
 * @category String tfAssociateLastName
 * @category Set<TfInterview> tfInterviews
 * @category Set<TfPlacement> tfPlacements
 * @category Timestamp clientStartDate
 * 
 * @author Adam L. 
 * @version.date v06.2018.06.13
 */
@XmlRootElement
@Entity
@Table(name = "TF_ASSOCIATE", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TfAssociate implements java.io.Serializable {


	private static final long serialVersionUID = -2324082555924677252L;
	
	public static final int APPROVED = 1;
	public static final int NOTAPPROVED = 0;

	@XmlElement
	@Id
	@Column(name = "TF_ASSOCIATE_ID", unique = true, nullable = false, precision = 22, scale = 0)
	@SequenceGenerator(sequenceName = "AssociateId_seq2", name ="AssociateIdSeq2", initialValue=1000)
	@GeneratedValue(generator ="AssociateIdSeq2", strategy = GenerationType.SEQUENCE)
	private Integer tfAssociateId;

	@XmlElement
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "TF_USER_ID")
	private TfUser tfUser;
	
	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TF_BATCH_ID")
	private TfBatch tfBatch;

	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TF_MARKETING_STATUS_ID")
	private TfMarketingStatus tfMarketingStatus;

	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TF_CLIENT_ID")
	private TfClient tfClient;

	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TF_END_CLIENT_ID")
	private TfEndClient tfEndClient;

	@XmlElement
	@Column(name = "TF_ASSOCIATE_FIRST_NAME", length = 30)
	private String tfAssociateFirstName;

	@XmlElement
	@Column(name = "TF_ASSOCIATE_LAST_NAME", length = 30)
	private String tfAssociateLastName;

	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfAssociate")
	private Set<TfInterview> tfInterviews = new HashSet<TfInterview>(0);

	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfAssociate")
	private Set<TfPlacement> tfPlacements = new HashSet<TfPlacement>(0);
	
	@XmlElement
	@Column(name = "TF_CLIENT_START_DATE")
	private Timestamp clientStartDate;

	@XmlElement
	@Column(name = "TF_ISAPPROVED")
	private Integer isApproved = NOTAPPROVED; // default is Zero not approved

	

	public TfAssociate() {
	}

	public TfAssociate(Integer tfAssociateId) {
		this.tfAssociateId = tfAssociateId;
	}

	public TfAssociate(Integer tfAssociateId, TfBatch tfBatch, TfMarketingStatus tfMarketingStatus,
			TfClient tfClient, TfEndClient tfEndClient, String tfAssociateFirstName, String tfAssociateLastName,
			Set<TfInterview> tfInterviews, Set<TfPlacement> tfPlacements, Timestamp clientStartDate, Integer isApproved) {
		this.tfAssociateId = tfAssociateId;
		this.tfBatch = tfBatch;
		this.tfMarketingStatus = tfMarketingStatus;
		this.tfClient = tfClient;
		this.tfEndClient = tfEndClient;
		this.tfAssociateFirstName = tfAssociateFirstName;
		this.tfAssociateLastName = tfAssociateLastName;
		this.tfInterviews = tfInterviews;
		this.tfPlacements = tfPlacements;
		this.clientStartDate = clientStartDate;
		this.isApproved = isApproved;
		
	}


	public TfUser getTfUser() {
		return tfUser;
	}

	public void setTfUser(TfUser tfUser) {
		this.tfUser = tfUser;
	}
	
	
	/**
	 * @return the isApproved
	 */
	public Integer getIsApproved() {
		return isApproved;
	}

	/**
	 * @param isApproved the isApproved to set
	 */
	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
	}

	public Integer getTfAssociateId() {
		return this.tfAssociateId;
	}

	public void setTfAssociateId(Integer tfAssociateId) {
		this.tfAssociateId = tfAssociateId;
	}


	public TfBatch getTfBatch() {
		return this.tfBatch;
	}

	public void setTfBatch(TfBatch tfBatch) {
		this.tfBatch = tfBatch;
	}


	public TfMarketingStatus getTfMarketingStatus() {
		return this.tfMarketingStatus;
	}

	public void setTfMarketingStatus(TfMarketingStatus tfMarketingStatus) {
		this.tfMarketingStatus = tfMarketingStatus;
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

	public Set<TfInterview> getTfInterviews() {
		return this.tfInterviews;
	}

	public void setTfInterviews(Set<TfInterview> tfInterviews) {
		this.tfInterviews = tfInterviews;
	}

	public Set<TfPlacement> getTfPlacements() {
		return this.tfPlacements;
	}

	public void setTfPlacements(Set<TfPlacement> tfPlacements) {
		this.tfPlacements = tfPlacements;
	}
	
	public Timestamp getTfClientStartDate() {
		return this.clientStartDate;
	}

	public void setTfClientStartDate(Timestamp clientStartDate) {
		this.clientStartDate = clientStartDate;
	}
	
	

	@Override
	public String toString() {
		return "TfAssociate [tfAssociateId=" + tfAssociateId + ", tfUser=" + tfUser + ", tfMarketingStatus="
				+ tfMarketingStatus + ", tfClient=" + tfClient + ", tfEndClient=" + tfEndClient
				+ ", tfAssociateFirstName=" + tfAssociateFirstName + ", tfAssociateLastName=" + tfAssociateLastName
				+ ", tfInterviews=" + tfInterviews + ", tfPlacements=" + tfPlacements + ", clientStartDate="
				+ clientStartDate + "]";
	}
	
	
}
