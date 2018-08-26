package com.revature.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/** @version v6.18.06.13 */
@XmlRootElement
@Entity
@Table(name = "TF_CURRICULUM", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="TrackForce")
public class TfCurriculum implements java.io.Serializable 
{
	private static final long serialVersionUID = 8213885869880424792L;
	
	@XmlElement
	@Id
	@Column(name = "TF_CURRICULUM_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer id;
	
	@XmlElement
	@Column(name = "TF_CURRICULUM_NAME", length = 30)
	private String name;
	
	@JsonIgnore
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "curriculumName")
	private Set<TfBatch> batches = new HashSet<>(0);
	
	public TfCurriculum(Integer id, String name, Set<TfBatch> batches) {
		super();
		this.id = id;
		this.name = name;
		this.batches = batches;
	}

	public TfCurriculum() { super(); }

	public Integer getId() { return id; }

	public void setId(Integer id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	@JsonIgnore
	public Set<TfBatch> getBatches() { return batches; }

	@JsonIgnore
	public void setBatches(Set<TfBatch> batches) { this.batches = batches; }

	public static long getSerialversionuid() { return serialVersionUID; }

	@Override
	public String toString() { return "TfCurriculum [id=" + id + ", name=" + name + "]"; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batches == null) ? 0 : batches.hashCode());
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
		TfCurriculum other = (TfCurriculum) obj;
		if (batches == null) {
			if (other.batches != null)
				return false;
		} else if (!batches.equals(other.batches))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
            return other.name == null;
		} else return name.equals(other.name);
    }
}