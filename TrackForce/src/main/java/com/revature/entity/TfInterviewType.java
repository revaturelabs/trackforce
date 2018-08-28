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
@Table(name = "TF_INTERVIEW_TYPE", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="TrackForce")
public class TfInterviewType implements java.io.Serializable 
{
	private static final long serialVersionUID = -4949282863102956521L;
	
	@XmlElement
	@Id
	@Column(name = "TF_INTERVIEW_TYPE_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer id;
	
	@XmlElement
	@Column(name = "TF_INTERVIEW_TYPE_NAME", length = 30)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "interviewType")
	@JsonIgnore
	private Set<TfInterview> interviews = new HashSet<>(0);

	//---------------------------

	public TfInterviewType() { super(); }

	public TfInterviewType(Integer id, String name, Set<TfInterview> interviews) {
		super();
		this.id = id;
		this.name = name;
		this.interviews = interviews;
	}

	//---------------------------

	public Integer getId() { return id; }

	public void setId(Integer id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	@JsonIgnore
	public Set<TfInterview> getInterviews() { return interviews; }

	@JsonIgnore
	public void setInterviews(Set<TfInterview> interviews) { this.interviews = interviews; }

	public static long getSerialversionuid() { return serialVersionUID; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interviews == null) ? 0 : interviews.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/** @param obj the reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj
	 * argument; {@code false} otherwise. */
	@Override
	public boolean equals(Object obj) { return super.equals(obj); }

	@Override
	public String toString() { return "TfInterviewType [id=" + id + ", name=" + name + "]"; }	
}