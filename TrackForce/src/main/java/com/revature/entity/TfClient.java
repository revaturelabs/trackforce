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
@Table(name = "TF_CLIENT", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TfClient implements java.io.Serializable {

	private static final long serialVersionUID = 3153069785231904041L;
	
	@XmlElement
	@Id
	@Column(name = "TF_CLIENT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer tfClientId;
	
	@XmlElement
	@Column(name = "TF_CLIENT_NAME", length = 100)
	private String tfClientName;
	
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfClient")
	private Set<TfPlacement> tfPlacements = new HashSet<TfPlacement>(0);
	
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfClient")
	private Set<TfAssociate> tfAssociates = new HashSet<TfAssociate>(0);
	
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfClient")
	private Set<TfInterview> tfInterviews = new HashSet<TfInterview>(0);

	public TfClient() {
	}

	public TfClient(Integer tfClientId) {
		this.tfClientId = tfClientId;
	}

	public TfClient(Integer tfClientId, String tfClientName, Set<TfPlacement> tfPlacements,
			Set<TfAssociate> tfAssociates, Set<TfInterview> tfInterviews) {
		this.tfClientId = tfClientId;
		this.tfClientName = tfClientName;
		this.tfPlacements = tfPlacements;
		this.tfAssociates = tfAssociates;
		this.tfInterviews = tfInterviews;
	}

	
	public Integer getTfClientId() {
		return this.tfClientId;
	}

	public void setTfClientId(Integer tfClientId) {
		this.tfClientId = tfClientId;
	}

	
	public String getTfClientName() {
		return this.tfClientName;
	}

	public void setTfClientName(String tfClientName) {
		this.tfClientName = tfClientName;
	}


	public Set<TfPlacement> getTfPlacements() {
		return this.tfPlacements;
	}

	public void setTfPlacements(Set<TfPlacement> tfPlacements) {
		this.tfPlacements = tfPlacements;
	}

	
	public Set<TfAssociate> getTfAssociates() {
		return this.tfAssociates;
	}

	public void setTfAssociates(Set<TfAssociate> tfAssociates) {
		this.tfAssociates = tfAssociates;
	}

	
	public Set<TfInterview> getTfInterviews() {
		return this.tfInterviews;
	}

	public void setTfInterviews(Set<TfInterview> tfInterviews) {
		this.tfInterviews = tfInterviews;
	}

    @Override
    public String toString() {
        return "TfClient [tfClientId=" + tfClientId + ", tfClientName=" + tfClientName + "]";
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tfAssociates == null) ? 0 : tfAssociates.hashCode());
		result = prime * result + ((tfClientId == null) ? 0 : tfClientId.hashCode());
		result = prime * result + ((tfClientName == null) ? 0 : tfClientName.hashCode());
		result = prime * result + ((tfInterviews == null) ? 0 : tfInterviews.hashCode());
		result = prime * result + ((tfPlacements == null) ? 0 : tfPlacements.hashCode());
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
		TfClient other = (TfClient) obj;
		if (tfAssociates == null) {
			if (other.tfAssociates != null)
				return false;
		} else if (!tfAssociates.equals(other.tfAssociates))
			return false;
		if (tfClientId == null) {
			if (other.tfClientId != null)
				return false;
		} else if (!tfClientId.equals(other.tfClientId))
			return false;
		if (tfClientName == null) {
			if (other.tfClientName != null)
				return false;
		} else if (!tfClientName.equals(other.tfClientName))
			return false;
		if (tfInterviews == null) {
			if (other.tfInterviews != null)
				return false;
		} else if (!tfInterviews.equals(other.tfInterviews))
			return false;
		if (tfPlacements == null) {
			if (other.tfPlacements != null)
				return false;
		} else if (!tfPlacements.equals(other.tfPlacements))
			return false;
		return true;
	}
	
}
