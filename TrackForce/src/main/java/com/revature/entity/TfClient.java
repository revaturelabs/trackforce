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
 * <p>
 * </p>
 * 
 * @version v6.18.06.13
 */
@XmlRootElement
@Entity
@Table(name = "TF_CLIENT", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="TrackForce")
public class TfClient implements java.io.Serializable {

	private static final long serialVersionUID = 3153069785231904041L;

	@XmlElement
	@Id
	@Column(name = "TF_CLIENT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer id;

	@XmlElement
	@Column(name = "TF_CLIENT_NAME", length = 100)
	private String name;

	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	@JsonIgnore
	private Set<TfPlacement> placement = new HashSet<>(0);

	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	@JsonIgnore
	private Set<TfAssociate> associate = new HashSet<>(0);

	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	@JsonIgnore
	private Set<TfInterview> interview = new HashSet<>(0);

	public TfClient() {
		super();
	}

	public TfClient(Integer id, String name, Set<TfPlacement> placement, Set<TfAssociate> associate,
			Set<TfInterview> interview) {
		super();
		this.id = id;
		this.name = name;
		this.placement = placement;
		this.associate = associate;
		this.interview = interview;
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
	public Set<TfPlacement> getPlacement() {
		return placement;
	}
	@JsonIgnore
	public void setPlacement(Set<TfPlacement> placement) {
		this.placement = placement;
	}

	@JsonIgnore
	public Set<TfAssociate> getAssociate() {
		return associate;
	}
	@JsonIgnore
	public void setAssociate(Set<TfAssociate> associate) {
		this.associate = associate;
	}

	@JsonIgnore
	public Set<TfInterview> getInterview() {
		return interview;
	}
	@JsonIgnore
	public void setInterview(Set<TfInterview> interview) {
		this.interview = interview;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associate == null) ? 0 : associate.hashCode());
		result = prime * result + ((interview == null) ? 0 : interview.hashCode());
		result = prime * result + ((placement == null) ? 0 : placement.hashCode());
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
		TfClient other = (TfClient) obj;
		if (associate == null) {
			if (other.associate != null)
				return false;
		} else if (!associate.equals(other.associate)) {
			return false;
		}
		if (interview == null) {
			if (other.interview != null)
				return false;
		} else if (!interview.equals(other.interview)) {
			return false;
		}
		if (placement == null) {
			if (other.placement != null)
				return false;
		} else if (!placement.equals(other.placement)) {
			return false;
		}
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TfClient [id=" + id + ", name=" + name + "]";
	}
}
