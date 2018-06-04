package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the TF_BATCH database table.
 * 
 */
@Entity
@Table(name="TF_BATCH")
@NamedQuery(name="TfBatch.findAll", query="SELECT t FROM TfBatch t")
public class TfBatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TF_BATCH_ID")
	private long tfBatchId;

	@Column(name="TF_BATCH_END_DATE")
	private Timestamp tfBatchEndDate;

	@Column(name="TF_BATCH_NAME")
	private String tfBatchName;

	@Column(name="TF_BATCH_START_DATE")
	private Timestamp tfBatchStartDate;

	//bi-directional many-to-one association to TfAssociate
	@OneToMany(mappedBy="tfBatch")
	private List<TfAssociate> tfAssociates;

	//bi-directional many-to-one association to TfBatchLocation
	@ManyToOne
	@JoinColumn(name="TF_BATCH_LOCATION_ID")
	private TfBatchLocation tfBatchLocation;

	//bi-directional many-to-one association to TfCurriculum
	@ManyToOne
	@JoinColumn(name="TF_CURRICULUM_ID")
	private TfCurriculum tfCurriculum;

	public TfBatch() {
	}

	public long getTfBatchId() {
		return this.tfBatchId;
	}

	public void setTfBatchId(long tfBatchId) {
		this.tfBatchId = tfBatchId;
	}

	public Timestamp getTfBatchEndDate() {
		return this.tfBatchEndDate;
	}

	public void setTfBatchEndDate(Timestamp tfBatchEndDate) {
		this.tfBatchEndDate = tfBatchEndDate;
	}

	public String getTfBatchName() {
		return this.tfBatchName;
	}

	public void setTfBatchName(String tfBatchName) {
		this.tfBatchName = tfBatchName;
	}

	public Timestamp getTfBatchStartDate() {
		return this.tfBatchStartDate;
	}

	public void setTfBatchStartDate(Timestamp tfBatchStartDate) {
		this.tfBatchStartDate = tfBatchStartDate;
	}

	public List<TfAssociate> getTfAssociates() {
		return this.tfAssociates;
	}

	public void setTfAssociates(List<TfAssociate> tfAssociates) {
		this.tfAssociates = tfAssociates;
	}

	public TfAssociate addTfAssociate(TfAssociate tfAssociate) {
		getTfAssociates().add(tfAssociate);
		tfAssociate.setTfBatch(this);

		return tfAssociate;
	}

	public TfAssociate removeTfAssociate(TfAssociate tfAssociate) {
		getTfAssociates().remove(tfAssociate);
		tfAssociate.setTfBatch(null);

		return tfAssociate;
	}

	public TfBatchLocation getTfBatchLocation() {
		return this.tfBatchLocation;
	}

	public void setTfBatchLocation(TfBatchLocation tfBatchLocation) {
		this.tfBatchLocation = tfBatchLocation;
	}

	public TfCurriculum getTfCurriculum() {
		return this.tfCurriculum;
	}

	public void setTfCurriculum(TfCurriculum tfCurriculum) {
		this.tfCurriculum = tfCurriculum;
	}

}