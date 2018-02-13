package com.revature.entity;

import java.util.HashSet;
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

import com.revature.model.StatusInfo;

@Entity
@Table(name = "TF_TECH", schema = "ADMIN")
public class TfTech implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2820002004770324793L;
	private int tfTechId;
	private String tfTechName;
	private Set<TfBatch> batches = new HashSet<TfBatch>(); //used for refference with manytomany
	
	
	public TfTech() {
	}

	public TfTech(int tfTechId, String tfTechName) {
		this.tfTechId = tfTechId;
		this.tfTechName = tfTechName;
	}
	
	//setters and getters needed below
	
	@Id
	@Column(name = "TF_TECH_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public int getTfTechId() {
		return this.tfTechId;
	}

	public void setTfTechId(int tfTechId) {
		this.tfTechId = tfTechId;
	}
	
	
	@Column
	public String getTechName() {
		return this.tfTechName;
	}
	
	public void setTechName(String tfTechName) {
		this.tfTechName = tfTechName;
	}
	
	
	public Set<TfBatch> getBatch() {
		return this.batches;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="techs")
	public void setBatch(HashSet<TfBatch> batches) {
			this.batches = batches;
	}
}
