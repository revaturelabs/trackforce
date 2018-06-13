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
@Table(name = "TF_END_CLIENT", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TfEndClient implements java.io.Serializable {

	private static final long serialVersionUID = -8077675564245631804L;
	
	@XmlElement
	@Id
	@Column(name = "TF_END_CLIENT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer tfEndClientId;
	
	@XmlElement
	@Column(name = "TF_END_CLIENT_NAME", length = 100)
	private String tfEndClientName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfEndClient")
	private Set<TfAssociate> tfAssociates = new HashSet<TfAssociate>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfEndClient")
	private Set<TfPlacement> tfPlacements = new HashSet<TfPlacement>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfEndClient")
	private Set<TfInterview> tfInterviews = new HashSet<TfInterview>(0);

	public TfEndClient() {
	}

	public TfEndClient(Integer tfEndClientId) {
		this.tfEndClientId = tfEndClientId;
	}

	public TfEndClient(Integer tfEndClientId, String tfEndClientName, Set<TfAssociate> tfAssociates,
			Set<TfPlacement> tfPlacements, Set<TfInterview> tfInterviews) {
		this.tfEndClientId = tfEndClientId;
		this.tfEndClientName = tfEndClientName;
		this.tfAssociates = tfAssociates;
		this.tfPlacements = tfPlacements;
		this.tfInterviews = tfInterviews;
	}

	
	public Integer getTfEndClientId() {
		return this.tfEndClientId;
	}

	public void setTfEndClientId(Integer tfEndClientId) {
		this.tfEndClientId = tfEndClientId;
	}

	
	public String getTfEndClientName() {
		return this.tfEndClientName;
	}

	public void setTfEndClientName(String tfEndClientName) {
		this.tfEndClientName = tfEndClientName;
	}

	
	public Set<TfAssociate> getTfAssociates() {
		return this.tfAssociates;
	}

	public void setTfAssociates(Set<TfAssociate> tfAssociates) {
		this.tfAssociates = tfAssociates;
	}

	
	public Set<TfPlacement> getTfPlacements() {
		return this.tfPlacements;
	}

	public void setTfPlacements(Set<TfPlacement> tfPlacements) {
		this.tfPlacements = tfPlacements;
	}

	
	public Set<TfInterview> getTfInterviews() {
		return this.tfInterviews;
	}

	public void setTfInterviews(Set<TfInterview> tfInterviews) {
		this.tfInterviews = tfInterviews;
	}

    @Override
    public String toString() {
        return "TfEndClient [tfEndClientId=" + tfEndClientId + ", tfEndClientName=" + tfEndClientName
                + ", tfAssociates=" + tfAssociates + ", tfPlacements=" + tfPlacements + ", tfInterviews=" + tfInterviews
                + "]";
    }

}
