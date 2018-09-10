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
@Table(name = "TF_END_CLIENT", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="TrackForce")
public class TfEndClient implements java.io.Serializable {

	private static final long serialVersionUID = -8077675564245631804L;
	
	@XmlElement
	@Id
	@Column(name = "TF_END_CLIENT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer id;
	
	@XmlElement
	@Column(name = "TF_END_CLIENT_NAME", length = 100)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "endClient")
	@JsonIgnore
	private Set<TfAssociate> associates = new HashSet<TfAssociate>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "endClient")
	@JsonIgnore
	private Set<TfPlacement> placements = new HashSet<TfPlacement>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "endClient")
	@JsonIgnore
	private Set<TfInterview> interviews = new HashSet<TfInterview>(0);

	public TfEndClient() {
		super();
	}

	public TfEndClient(Integer id, String name, Set<TfAssociate> associates, Set<TfPlacement> placements,
			Set<TfInterview> interviews) {
		super();
		this.id = id;
		this.name = name;
		this.associates = associates;
		this.placements = placements;
		this.interviews = interviews;
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

	@JsonIgnore
	public Set<TfPlacement> getPlacements() {
		return placements;
	}
	@JsonIgnore
	public void setPlacements(Set<TfPlacement> placements) {
		this.placements = placements;
	}

	@JsonIgnore
	public Set<TfInterview> getInterviews() {
		return interviews;
	}
	@JsonIgnore
	public void setInterviews(Set<TfInterview> interviews) {
		this.interviews = interviews;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "TfEndClient [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associates == null) ? 0 : associates.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interviews == null) ? 0 : interviews.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((placements == null) ? 0 : placements.hashCode());
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
		TfEndClient other = (TfEndClient) obj;
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
		if (interviews == null) {
			if (other.interviews != null)
				return false;
		} else if (!interviews.equals(other.interviews))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (placements == null) {
			if (other.placements != null)
				return false;
		} else if (!placements.equals(other.placements))
			return false;
		return true;
	}
	
}
