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


/**
 * <p> </p>
 * @version.date v06.2018.06.13
 */
@XmlRootElement
@Entity
@Table(name = "TF_CLIENT", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfClient")
	private Set<TfPlacement> Placement = new HashSet<TfPlacement>(0);
	
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfClient")
	private Set<TfAssociate> Associate = new HashSet<TfAssociate>(0);
	
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfClient")
	private Set<TfInterview> Interview = new HashSet<TfInterview>(0);

	public TfClient() {
	}

	public TfClient(Integer id, String name, Set<TfPlacement> placement, Set<TfAssociate> associate,
			Set<TfInterview> interview) {
		super();
		this.id = id;
		this.name = name;
		Placement = placement;
		Associate = associate;
		Interview = interview;
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

	public Set<TfPlacement> getPlacement() {
		return Placement;
	}

	public void setPlacement(Set<TfPlacement> placement) {
		Placement = placement;
	}

	public Set<TfAssociate> getAssociate() {
		return Associate;
	}

	public void setAssociate(Set<TfAssociate> associate) {
		Associate = associate;
	}

	public Set<TfInterview> getInterview() {
		return Interview;
	}

	public void setInterview(Set<TfInterview> interview) {
		Interview = interview;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Associate == null) ? 0 : Associate.hashCode());
		result = prime * result + ((Interview == null) ? 0 : Interview.hashCode());
		result = prime * result + ((Placement == null) ? 0 : Placement.hashCode());
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
		if (Associate == null) {
			if (other.Associate != null)
				return false;
		} else if (!Associate.equals(other.Associate))
			return false;
		if (Interview == null) {
			if (other.Interview != null)
				return false;
		} else if (!Interview.equals(other.Interview))
			return false;
		if (Placement == null) {
			if (other.Placement != null)
				return false;
		} else if (!Placement.equals(other.Placement))
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


	
}
