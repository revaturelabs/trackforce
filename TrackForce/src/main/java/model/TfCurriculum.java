package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TF_CURRICULUM database table.
 * 
 */
@Entity
@Table(name="TF_CURRICULUM")
@NamedQuery(name="TfCurriculum.findAll", query="SELECT t FROM TfCurriculum t")
public class TfCurriculum implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TF_CURRICULUM_ID")
	private long tfCurriculumId;

	@Column(name="TF_CURRICULUM_NAME")
	private String tfCurriculumName;

	//bi-directional many-to-one association to TfBatch
	@OneToMany(mappedBy="tfCurriculum")
	private List<TfBatch> tfBatches;

	public TfCurriculum() {
	}

	public long getTfCurriculumId() {
		return this.tfCurriculumId;
	}

	public void setTfCurriculumId(long tfCurriculumId) {
		this.tfCurriculumId = tfCurriculumId;
	}

	public String getTfCurriculumName() {
		return this.tfCurriculumName;
	}

	public void setTfCurriculumName(String tfCurriculumName) {
		this.tfCurriculumName = tfCurriculumName;
	}

	public List<TfBatch> getTfBatches() {
		return this.tfBatches;
	}

	public void setTfBatches(List<TfBatch> tfBatches) {
		this.tfBatches = tfBatches;
	}

	public TfBatch addTfBatch(TfBatch tfBatch) {
		getTfBatches().add(tfBatch);
		tfBatch.setTfCurriculum(this);

		return tfBatch;
	}

	public TfBatch removeTfBatch(TfBatch tfBatch) {
		getTfBatches().remove(tfBatch);
		tfBatch.setTfCurriculum(null);

		return tfBatch;
	}

}