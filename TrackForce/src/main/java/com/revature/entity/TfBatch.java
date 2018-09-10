package com.revature.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p> </p>
 * @version v6.18.06.13
 */
@XmlRootElement
@Entity
@Table(name = "TF_BATCH", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="TrackForce")
public class TfBatch implements java.io.Serializable, Comparable<TfBatch> {

	private static final long serialVersionUID = 1893469049852289417L;
	
	@XmlElement
	@Id
	@Column(name = "TF_BATCH_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer id;
	
	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TF_BATCH_LOCATION_ID")
	private TfBatchLocation location;
	
	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TF_CURRICULUM_ID")
	private TfCurriculum curriculumName;
	
	@XmlElement
	@Column(name = "TF_BATCH_NAME", length = 50)
	private String batchName;
	
	@XmlElement
	@Column(name = "TF_BATCH_START_DATE")
	private Timestamp startDate;
	
	@XmlElement
	@Column(name = "TF_BATCH_END_DATE")	
	private Timestamp endDate;
	
	@XmlElement
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "batch")
	@JsonIgnore
	private Set<TfAssociate> associates = new HashSet<TfAssociate>(0);

	@XmlElement
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRIMARY_TRAINER")
	private TfTrainer trainer;
	
	@ManyToMany(mappedBy="coTrainer")
	@JsonIgnore
	private List<TfTrainer> coTrainer = new ArrayList<>();
	
	public TfBatch() {
		super();
	}
	public TfBatch(Integer id, TfBatchLocation location, TfCurriculum curriculumName, String batchName,
			Timestamp startDate, Timestamp endDate, Set<TfAssociate> associates, TfTrainer trainer,
			List<TfTrainer> coTrainer) {
		super();
		this.id = id;
		this.location = location;
		this.curriculumName = curriculumName;
		this.batchName = batchName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.associates = associates;
		this.trainer = trainer;
		this.coTrainer = coTrainer;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public TfBatchLocation getLocation() {
		return location;
	}
	public void setLocation(TfBatchLocation location) {
		this.location = location;
	}

	public TfCurriculum getCurriculumName() {
		return curriculumName;
	}
	public void setCurriculumName(TfCurriculum curriculumName) {
		this.curriculumName = curriculumName;
	}

	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	@JsonIgnore
	public Set<TfAssociate> getAssociates() {
		return associates;
	}
	@JsonIgnore
	public void setAssociates(Set<TfAssociate> associates) {
		this.associates = associates;
	}

	public TfTrainer getTrainer() {
		return trainer;
	}
	public void setTrainer(TfTrainer trainer) {
		this.trainer = trainer;
	}

	@JsonIgnore
	public List<TfTrainer> getCoTrainer() {
		return coTrainer;
	}
	@JsonIgnore
	public void setCoTrainer(List<TfTrainer> coTrainer) {
		this.coTrainer = coTrainer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TfBatch [id=" + id + ", location=" + location + ", curriculumName=" + curriculumName + ", batchName="
				+ batchName + ", startDate=" + startDate + ", endDate=" + endDate + ", associates=" + associates + "]";
	}

	@Override
	public int compareTo(TfBatch o) {
		return this.id-o.getId();
	}
	
	
}
