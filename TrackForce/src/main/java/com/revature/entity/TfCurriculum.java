package com.revature.entity;
// Generated Nov 7, 2017 9:24:46 PM by Hibernate Tools 5.2.5.Final


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@XmlRootElement
@Entity
@Table(name = "TF_CURRICULUM", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TfCurriculum implements java.io.Serializable {

	private static final long serialVersionUID = 8213885869880424792L;
	
	@XmlElement
	@Id
	@Column(name = "TF_CURRICULUM_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer tfCurriculumId;
	
	@XmlElement
	@Column(name = "TF_CURRICULUM_NAME", length = 30)
	private String tfCurriculumName;
	
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfCurriculum")
	private Set<TfBatch> tfBatches = new HashSet<TfBatch>(0);

	public TfCurriculum() {
	}

	public TfCurriculum(Integer tfCurriculumId) {
		this.tfCurriculumId = tfCurriculumId;
	}
	
	public TfCurriculum(Integer tfCurriculumId, String tfCurriculumName, Set<TfBatch> tfBatches) {
		this.tfCurriculumId = tfCurriculumId;
		this.tfCurriculumName = tfCurriculumName;
		this.tfBatches = tfBatches;
	}

	
	public Integer getTfCurriculumId() {
		return this.tfCurriculumId;
	}

	public void setTfCurriculumId(Integer tfCurriculumId) {
		this.tfCurriculumId = tfCurriculumId;
	}

	
	public String getTfCurriculumName() {
		return this.tfCurriculumName;
	}

	public void setTfCurriculumName(String tfCurriculumName) {
		this.tfCurriculumName = tfCurriculumName;
	}

	
	public Set<TfBatch> getTfBatches() {
		return this.tfBatches;
	}

	public void setTfBatches(Set<TfBatch> tfBatches) {
		this.tfBatches = tfBatches;
	}
}
