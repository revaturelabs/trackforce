package com.revature.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * <p> </p>
 * @version v6.18.06.13
 */
@XmlRootElement
@Entity
@Table(name = "TF_PLACEMENT", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="TrackForce")
public class TfPlacement implements java.io.Serializable {

	private static final long serialVersionUID = 6812378121809201089L;
	
	@XmlElement
	@Id
	@Column(name = "TF_PLACEMENT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TF_ASSOCIATE_ID")
	private TfAssociate associate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TF_CLIENT_ID")
	private TfClient client;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TF_END_CLIENT_ID")
	private TfEndClient endClient;
	
	@XmlElement
	@Column(name = "TF_PLACEMENT_START_DATE")
	private Timestamp start;
	
	@XmlElement
	@Column(name = "TF_PLACEMENT_END_DATE")
	private Timestamp end;

	public TfPlacement() {
		super();
	}

	public TfPlacement(Integer id, TfAssociate associate, TfClient client, TfEndClient endClient, Timestamp start,
			Timestamp end) {
		super();
		this.id = id;
		this.associate = associate;
		this.client = client;
		this.endClient = endClient;
		this.start = start;
		this.end = end;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public TfAssociate getAssociate() {
		return associate;
	}
	public void setAssociate(TfAssociate associate) {
		this.associate = associate;
	}

	public TfClient getClient() {
		return client;
	}
	public void setClient(TfClient client) {
		this.client = client;
	}

	public TfEndClient getEndClient() {
		return endClient;
	}
	public void setEndClient(TfEndClient endClient) {
		this.endClient = endClient;
	}

	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associate == null) ? 0 : associate.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((endClient == null) ? 0 : endClient.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		TfPlacement other = (TfPlacement) obj;
		if (associate == null) {
			if (other.associate != null)
				return false;
		} else if (!associate.equals(other.associate))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (endClient == null) {
			if (other.endClient != null)
				return false;
		} else if (!endClient.equals(other.endClient))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TfPlacement [id=" + id + ", client=" + client + ", endClient=" + endClient + ", start=" + start
				+ ", end=" + end + "]";
	}
	
}
