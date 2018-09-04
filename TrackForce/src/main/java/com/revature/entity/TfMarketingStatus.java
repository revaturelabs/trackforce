package com.revature.entity;

import java.util.HashSet;
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
@Table(name = "TF_MARKETING_STATUS", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="TrackForce")
public class TfMarketingStatus implements java.io.Serializable {

	private static final long serialVersionUID = -1638800519652509525L;
	
	@XmlElement
	@Id
	@Column(name = "TF_MARKETING_STATUS_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer id;
	
	@XmlElement
	@Column(name = "TF_MARKETING_STATUS_NAME", length = 30)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "marketingStatus")
	@JsonIgnore
	private Set<TfAssociate> associates = new HashSet<TfAssociate>(0);

	public TfMarketingStatus() {
	}

	public TfMarketingStatus(Integer id, String name, Set<TfAssociate> associates) {
		super();
		this.id = id;
		this.name = name;
		this.associates = associates;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public Set<TfAssociate> getAssociates() {
		return associates;
	}
	@JsonIgnore
	public void setAssociates(Set<TfAssociate> associates) {
		this.associates = associates;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associates == null) ? 0 : associates.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TfMarketingStatus other = (TfMarketingStatus) obj;
		if (associates == null) {
			if (other.associates != null)
				return false;
		} else if (!associates.equals(other.associates))
			return false;
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
		return true;
	}

	@Override
	public String toString() {
		return "TfMarketingStatus [id=" + id + ", name=" + name + "]";
	}
}
