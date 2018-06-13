package com.revature.entity;
// Generated Nov 7, 2017 9:24:46 PM by Hibernate Tools 5.2.5.Final

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

@XmlRootElement
@Entity
@Table(name = "TF_BATCH_LOCATION", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TfBatchLocation implements java.io.Serializable {

	private static final long serialVersionUID = -213863298550515723L;
	
	@XmlElement
	@Id
	@Column(name = "TF_BATCH_LOCATION_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private BigDecimal tfBatchLocationId;

	@XmlElement
	@Column(name = "TF_BATCH_LOCATION_NAME", length = 500)
	private String tfBatchLocationName;
	
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfBatchLocation")
	private Set<TfBatch> tfBatches = new HashSet<TfBatch>(0);

	public TfBatchLocation() {
	}
	
	public TfBatchLocation(BigDecimal tfBatchLocationId) {
		this.tfBatchLocationId = tfBatchLocationId;
	}

	public TfBatchLocation(BigDecimal tfBatchLocationId, String tfBatchLocationName, Set<TfBatch> tfBatches) {
		this.tfBatchLocationId = tfBatchLocationId;
		this.tfBatchLocationName = tfBatchLocationName;
		this.tfBatches = tfBatches;
	}

	
	public BigDecimal getTfBatchLocationId() {
		return this.tfBatchLocationId;
	}

	public void setTfBatchLocationId(BigDecimal tfBatchLocationId) {
		this.tfBatchLocationId = tfBatchLocationId;
	}

	
	public String getTfBatchLocationName() {
		return this.tfBatchLocationName;
	}

	public void setTfBatchLocationName(String tfBatchLocationName) {
		this.tfBatchLocationName = tfBatchLocationName;
	}

	
	public Set<TfBatch> getTfBatches() {
		return this.tfBatches;
	}

	public void setTfBatches(Set<TfBatch> tfBatches) {
		this.tfBatches = tfBatches;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tfBatchLocationId == null) ? 0 : tfBatchLocationId.hashCode());
		result = prime * result + ((tfBatchLocationName == null) ? 0 : tfBatchLocationName.hashCode());
		result = prime * result + ((tfBatches == null) ? 0 : tfBatches.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TfBatchLocation other = (TfBatchLocation) obj;
		if (tfBatchLocationId == null) {
			if (other.tfBatchLocationId != null)
				return false;
		} else if (!tfBatchLocationId.equals(other.tfBatchLocationId))
			return false;
		if (tfBatchLocationName == null) {
			if (other.tfBatchLocationName != null)
				return false;
		} else if (!tfBatchLocationName.equals(other.tfBatchLocationName))
			return false;
		if (tfBatches == null) {
			if (other.tfBatches != null)
				return false;
		} else if (!tfBatches.equals(other.tfBatches))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TfBatchLocation [tfBatchLocationId=" + tfBatchLocationId + ", tfBatchLocationName="
				+ tfBatchLocationName + ", tfBatches=" + tfBatches + "]";
	}
	

}
