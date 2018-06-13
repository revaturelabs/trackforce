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
@Table(name = "TF_INTERVIEW_TYPE", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TfInterviewType implements java.io.Serializable {

	private static final long serialVersionUID = -4949282863102956521L;
	
	@XmlElement
	@Id
	@Column(name = "TF_INTERVIEW_TYPE_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer tfInterviewTypeId;
	
	@XmlElement
	@Column(name = "TF_INTERVIEW_TYPE_NAME", length = 30)
	private String tfInterviewTypeName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfInterviewType")
	private Set<TfInterview> tfInterviews = new HashSet<TfInterview>(0);

	public TfInterviewType() {
	}

	public TfInterviewType(Integer tfInterviewTypeId) {
		this.tfInterviewTypeId = tfInterviewTypeId;
	}

	public TfInterviewType(Integer tfInterviewTypeId, String tfInterviewTypeName, Set<TfInterview> tfInterviews) {
		this.tfInterviewTypeId = tfInterviewTypeId;
		this.tfInterviewTypeName = tfInterviewTypeName;
		this.tfInterviews = tfInterviews;
	}

	
	public Integer getTfInterviewTypeId() {
		return this.tfInterviewTypeId;
	}

	public void setTfInterviewTypeId(Integer tfInterviewTypeId) {
		this.tfInterviewTypeId = tfInterviewTypeId;
	}

	
	public String getTfInterviewTypeName() {
		return this.tfInterviewTypeName;
	}

	public void setTfInterviewTypeName(String tfInterviewTypeName) {
		this.tfInterviewTypeName = tfInterviewTypeName;
	}

	public Set<TfInterview> getTfInterviews() {
		return this.tfInterviews;
	}

	public void setTfInterviews(Set<TfInterview> tfInterviews) {
		this.tfInterviews = tfInterviews;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tfInterviewTypeId == null) ? 0 : tfInterviewTypeId.hashCode());
		result = prime * result + ((tfInterviewTypeName == null) ? 0 : tfInterviewTypeName.hashCode());
		result = prime * result + ((tfInterviews == null) ? 0 : tfInterviews.hashCode());
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
		TfInterviewType other = (TfInterviewType) obj;
		if (tfInterviewTypeId == null) {
			if (other.tfInterviewTypeId != null)
				return false;
		} else if (!tfInterviewTypeId.equals(other.tfInterviewTypeId))
			return false;
		if (tfInterviewTypeName == null) {
			if (other.tfInterviewTypeName != null)
				return false;
		} else if (!tfInterviewTypeName.equals(other.tfInterviewTypeName))
			return false;
		if (tfInterviews == null) {
			if (other.tfInterviews != null)
				return false;
		} else if (!tfInterviews.equals(other.tfInterviews))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TfInterviewType [tfInterviewTypeId=" + tfInterviewTypeId + ", tfInterviewTypeName="
				+ tfInterviewTypeName + "]";
	}
	
}
