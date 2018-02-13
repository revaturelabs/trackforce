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
	private Set<TfBatch> batches; //used for refference with manytomany
	
	
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
	
	
	@Column(name = "TF_TECH_NAME", length = 30)
	public String getTechName() {
		return this.tfTechName;
	}
	
	public void setTechName(String tfTechName) {
		this.tfTechName = tfTechName;
	}
	
	//@ManyToMany(fetch = FetchType.LAZY, targetEntity=TfBatch.class ,mappedBy="techs")
	public Set<TfBatch> getBatch() {
		return this.batches;
	}
	
	public void setBatch(Set<TfBatch> batches) {
			this.batches = batches;
	}
}
