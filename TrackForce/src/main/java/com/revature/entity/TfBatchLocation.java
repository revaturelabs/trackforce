package com.revature.entity;

import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * <p> </p>
 * @version v6.18.06.13
 */
@XmlRootElement
@Entity
@Table(name = "TF_BATCH_LOCATION", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="TrackForce")
public class TfBatchLocation implements java.io.Serializable {

	private static final long serialVersionUID = -213863298550515723L;
	
	@XmlElement
	@Id
	@Column(name = "TF_BATCH_LOCATION_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private BigDecimal id;

	@XmlElement
	@Column(name = "TF_BATCH_LOCATION_NAME", length = 500)
	private String name;
	
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	@JsonIgnore
	private Set<TfBatch> batches = new HashSet<TfBatch>(0);

	public TfBatchLocation() {
		super();
	}
	
	public TfBatchLocation(BigDecimal tfBatchLocationId) {
		this.id = tfBatchLocationId;
	}

	public TfBatchLocation(BigDecimal tfBatchLocationId, String tfBatchLocationName, Set<TfBatch> tfBatches) {
		this.id = tfBatchLocationId;
		this.name = tfBatchLocationName;
		this.batches = tfBatches;
	}

	public BigDecimal getTfBatchLocationId() {
		return this.id;
	}
	public void setTfBatchLocationId(BigDecimal tfBatchLocationId) {
		this.id = tfBatchLocationId;
	}

	public String getTfBatchLocationName() {
		return this.name;
	}
	public void setTfBatchLocationName(String tfBatchLocationName) {
		this.name = tfBatchLocationName;
	}

	@JsonIgnore
	public Set<TfBatch> getTfBatches() {
		return this.batches;
	}
	@JsonIgnore
	public void setTfBatches(Set<TfBatch> tfBatches) {
		this.batches = tfBatches;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((batches == null) ? 0 : batches.hashCode());
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
		TfBatchLocation other = (TfBatchLocation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (batches == null) {
			if (other.batches != null)
				return false;
		} else if (!batches.equals(other.batches))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TfBatchLocation [tfBatchLocationId=" + id + ", tfBatchLocationName="
				+ name + "]";
	}
	
}
