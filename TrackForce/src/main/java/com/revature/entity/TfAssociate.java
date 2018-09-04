package com.revature.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
 * @version v6.18.06.13
 */
@XmlRootElement
@Entity
@Table(name = "TF_ASSOCIATE", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="TrackForce")
public class TfAssociate implements java.io.Serializable {

	private static final long serialVersionUID = -2324082555924677252L;

	@XmlElement
	@Id
	@Column(name = "TF_ASSOCIATE_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer id;

	@XmlElement
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "TF_USER_ID")
	private TfUser user;
	
	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "TF_BATCH_ID")
	private TfBatch batch;

	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "TF_MARKETING_STATUS_ID")
	private TfMarketingStatus marketingStatus;

	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "TF_CLIENT_ID")
	private TfClient client;

	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "TF_END_CLIENT_ID")
	private TfEndClient endClient;

	@XmlElement
	@Column(name = "TF_ASSOCIATE_FIRST_NAME", length = 30)
	private String firstName;

	@XmlElement
	@Column(name = "TF_ASSOCIATE_LAST_NAME", length = 30)
	private String lastName;

	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "associate", cascade = {CascadeType.ALL})
	@JsonIgnore
	private Set<TfInterview> interview = new HashSet<>(0);

	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "associate", cascade = {CascadeType.ALL})
	@JsonIgnore
	private Set<TfPlacement> placement = new HashSet<>(0);
	
	@XmlElement
	@Column(name = "TF_CLIENT_START_DATE")
	private Timestamp clientStartDate;
	
	@XmlElement
	@Column(name = "TF_STAGING_FEEDBACK")
	private String stagingFeedback;
	
	public TfAssociate() {
		super();
	}

	public TfAssociate(Integer id, TfUser user, TfBatch batch, TfMarketingStatus marketingStatus, TfClient client,
			TfEndClient endClient, String firstName, String lastName, Set<TfInterview> interview,
			Set<TfPlacement> placement, Timestamp clientStartDate) {
		super();
		this.id = id;
		this.user = user;
		this.batch = batch;
		this.marketingStatus = marketingStatus;
		this.client = client;
		this.endClient = endClient;
		this.firstName = firstName;
		this.lastName = lastName;
		this.interview = interview;
		this.placement = placement;
		this.clientStartDate = clientStartDate;
	}

	public String getStagingFeedback() {
		return stagingFeedback;
	}
	public void setStagingFeedback(String stagingFeedback) {
		this.stagingFeedback = stagingFeedback;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public TfUser getUser() {
		return user;
	}
	public void setUser(TfUser user) {
		this.user = user;
	}

	public TfBatch getBatch() {
		return batch;
	}
	public void setBatch(TfBatch batch) {
		this.batch = batch;
	}

	public TfMarketingStatus getMarketingStatus() {
		return marketingStatus;
	}
	public void setMarketingStatus(TfMarketingStatus marketingStatus) {
		this.marketingStatus = marketingStatus;
	}

	public TfClient getClient() {
		return client;
	}
	public void setClient(TfClient client) {
		this.client = client;
	}

	public TfEndClient getEndClient() {
		return endClient;
	}
	public void setEndClient(TfEndClient endClient) {
		this.endClient = endClient;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonIgnore
	public Set<TfInterview> getInterview() {
		return interview;
	}

	@JsonIgnore
	public void setInterview(Set<TfInterview> interview) {
		this.interview = interview;
	}

	@JsonIgnore
	public Set<TfPlacement> getPlacement() {
		return placement;
	}
	@JsonIgnore
	public void setPlacement(Set<TfPlacement> placement) {
		this.placement = placement;
	}

	public Timestamp getClientStartDate() {
		return clientStartDate;
	}
	public void setClientStartDate(Timestamp clientStartDate) {
		this.clientStartDate = clientStartDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TfAssociate [id=" + id + ", user=" + user + ", marketingStatus=" + marketingStatus + ", client="
				+ client + ", endClient=" + endClient + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", clientStartDate=" + clientStartDate + ", stagingFeedback=" + stagingFeedback + "]";
	}
}
