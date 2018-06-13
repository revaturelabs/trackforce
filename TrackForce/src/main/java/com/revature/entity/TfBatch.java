package com.revature.entity;
// Generated Nov 7, 2017 9:24:46 PM by Hibernate Tools 5.2.5.Final

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



@XmlRootElement
@Entity
@Table(name = "TF_BATCH", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TfBatch implements java.io.Serializable, Comparable<TfBatch> {


	private static final long serialVersionUID = 1893469049852289417L;
	
	@XmlElement
	@Id
	@Column(name = "TF_BATCH_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer tfBatchId;
	
	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TF_BATCH_LOCATION_ID")
	private TfBatchLocation tfBatchLocation;
	
	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TF_CURRICULUM_ID")
	private TfCurriculum tfCurriculum;
	
	@XmlElement
	@Column(name = "TF_BATCH_NAME", length = 50)
	private String tfBatchName;
	
	@XmlElement
	@Column(name = "TF_BATCH_START_DATE")
	private Timestamp tfBatchStartDate;
	
	@XmlElement
	@Column(name = "TF_BATCH_END_DATE")	
	private Timestamp tfBatchEndDate;
	
	@XmlElement
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tfBatch")
	private Set<TfAssociate> tfAssociates = new HashSet<TfAssociate>(0);

	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRIMARY_TRAINER")
	private TfTrainer trainer;
	
	@ManyToMany(mappedBy="coTrainer")
	private List<TfTrainer> coTrainer = new ArrayList<>();
	
	public TfBatch() {
	}

	public TfBatch(Integer tfBatchId) {
		this.tfBatchId = tfBatchId;
	}

	public TfBatch(Integer tfBatchId, TfBatchLocation tfBatchLocation, TfCurriculum tfCurriculum, String tfBatchName,
			Timestamp tfBatchStartDate, Timestamp tfBatchEndDate, Set<TfAssociate> tfAssociates) {
		this.tfBatchId = tfBatchId;
		this.tfBatchLocation = tfBatchLocation;
		this.tfCurriculum = tfCurriculum;
		this.tfBatchName = tfBatchName;
		this.tfBatchStartDate = tfBatchStartDate;
		this.tfBatchEndDate = tfBatchEndDate;
		this.tfAssociates = tfAssociates;
	}
	
	

	
	public TfTrainer getTrainer() {
		return trainer;
	}

	public void setTrainer(TfTrainer trainer) {
		this.trainer = trainer;
	}

	public List<TfTrainer> getCoTrainer() {
		return coTrainer;
	}

	public void setCoTrainer(List<TfTrainer> coTrainer) {
		this.coTrainer = coTrainer;
	}

	public Integer getTfBatchId() {
		return this.tfBatchId;
	}

	public void setTfBatchId(Integer tfBatchId) {
		this.tfBatchId = tfBatchId;
	}

	
	public TfBatchLocation getTfBatchLocation() {
		return this.tfBatchLocation;
	}

	public void setTfBatchLocation(TfBatchLocation tfBatchLocation) {
		this.tfBatchLocation = tfBatchLocation;
	}

	
	public TfCurriculum getTfCurriculum() {
		return this.tfCurriculum;
	}

	public void setTfCurriculum(TfCurriculum tfCurriculum) {
		this.tfCurriculum = tfCurriculum;
	}
	
	public String getTfBatchName() {
		return this.tfBatchName;
	}

	public void setTfBatchName(String tfBatchName) {
		this.tfBatchName = tfBatchName;
	}

	
	public Timestamp getTfBatchStartDate() {
		return this.tfBatchStartDate;
	}

	public void setTfBatchStartDate(Timestamp tfBatchStartDate) {
		this.tfBatchStartDate = tfBatchStartDate;
	}


	public Timestamp getTfBatchEndDate() {
		return this.tfBatchEndDate;
	}

	public void setTfBatchEndDate(Timestamp tfBatchEndDate) {
		this.tfBatchEndDate = tfBatchEndDate;
	}

	
	public Set<TfAssociate> getTfAssociates() {
		return this.tfAssociates;
	}
    	public void setTfAssociates(Set<TfAssociate> tfAssociates) {
		this.tfAssociates = tfAssociates;
	}

    
    	
	@Override
	public String toString() {
		return "TfBatch [tfBatchId=" + tfBatchId + ", tfBatchLocation=" + tfBatchLocation + ", tfCurriculum="
				+ tfCurriculum + ", tfBatchName=" + tfBatchName + ", tfBatchStartDate=" + tfBatchStartDate
				+ ", tfBatchEndDate=" + tfBatchEndDate + ", tfAssociates=" + tfAssociates + ", trainer=" + trainer
				+ ", coTrainer=" + coTrainer + "]";
	}

	@Override
	public int compareTo(TfBatch o) {
		return this.tfBatchId-o.getTfBatchId();
	}
	
	
}
