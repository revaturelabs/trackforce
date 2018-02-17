package com.revature.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.revature.entity.TfBatchTechJunction;

@Entity
@Table(name = "TF_TECH", schema = "ADMIN")
public class TfTech implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2820002004770324793L;
	private int tfTechId;
	private String tfTechName;
	private Set<TfBatchTechJunction> batchTechJunctions = new HashSet<TfBatchTechJunction>();

	public TfTech() {
	}

	public TfTech(int tfTechId, String tfTechName, Set<TfBatchTechJunction> batchTechJunctions) {
		this.tfTechId = tfTechId;
		this.tfTechName = tfTechName;
		this.batchTechJunctions = batchTechJunctions;

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

	@OneToMany(fetch=FetchType.EAGER, mappedBy = "pk.tech")
	public Set<TfBatchTechJunction> getBatchTechJunctions() {
		return this.batchTechJunctions;
	}
  
	public void setBatchTechJunctions(Set<TfBatchTechJunction> batchTechJunctions) {
		this.batchTechJunctions = batchTechJunctions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchTechJunctions == null) ? 0 : batchTechJunctions.hashCode());
		result = prime * result + tfTechId;
		result = prime * result + ((tfTechName == null) ? 0 : tfTechName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TfTech other = (TfTech) obj;
		if (batchTechJunctions == null) {
			if (other.batchTechJunctions != null)
				return false;
		} else if (!batchTechJunctions.equals(other.batchTechJunctions))
			return false;
		if (tfTechId != other.tfTechId)
			return false;
		if (tfTechName == null) {
			if (other.tfTechName != null)
				return false;
		} else if (!tfTechName.equals(other.tfTechName))
			return false;
		return true;
	}
	
	
	
}
