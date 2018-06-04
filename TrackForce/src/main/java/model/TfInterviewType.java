package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TF_INTERVIEW_TYPE database table.
 * 
 */
@Entity
@Table(name="TF_INTERVIEW_TYPE")
@NamedQuery(name="TfInterviewType.findAll", query="SELECT t FROM TfInterviewType t")
public class TfInterviewType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TF_INTERVIEW_TYPE_ID")
	private long tfInterviewTypeId;

	@Column(name="TF_INTERVIEW_TYPE_NAME")
	private String tfInterviewTypeName;

	//bi-directional many-to-one association to TfInterview
	@OneToMany(mappedBy="tfInterviewType")
	private List<TfInterview> tfInterviews;

	public TfInterviewType() {
	}

	public long getTfInterviewTypeId() {
		return this.tfInterviewTypeId;
	}

	public void setTfInterviewTypeId(long tfInterviewTypeId) {
		this.tfInterviewTypeId = tfInterviewTypeId;
	}

	public String getTfInterviewTypeName() {
		return this.tfInterviewTypeName;
	}

	public void setTfInterviewTypeName(String tfInterviewTypeName) {
		this.tfInterviewTypeName = tfInterviewTypeName;
	}

	public List<TfInterview> getTfInterviews() {
		return this.tfInterviews;
	}

	public void setTfInterviews(List<TfInterview> tfInterviews) {
		this.tfInterviews = tfInterviews;
	}

	public TfInterview addTfInterview(TfInterview tfInterview) {
		getTfInterviews().add(tfInterview);
		tfInterview.setTfInterviewType(this);

		return tfInterview;
	}

	public TfInterview removeTfInterview(TfInterview tfInterview) {
		getTfInterviews().remove(tfInterview);
		tfInterview.setTfInterviewType(null);

		return tfInterview;
	}

}