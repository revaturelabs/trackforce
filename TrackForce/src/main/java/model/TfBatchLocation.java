package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TF_BATCH_LOCATION database table.
 * 
 */
@Entity
@Table(name="TF_BATCH_LOCATION")
@NamedQuery(name="TfBatchLocation.findAll", query="SELECT t FROM TfBatchLocation t")
public class TfBatchLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TF_BATCH_LOCATION_ID")
	private long tfBatchLocationId;

	@Column(name="TF_BATCH_LOCATION_NAME")
	private String tfBatchLocationName;

	//bi-directional many-to-one association to TfBatch
	@OneToMany(mappedBy="tfBatchLocation")
	private List<TfBatch> tfBatches;

	public TfBatchLocation() {
	}

	public long getTfBatchLocationId() {
		return this.tfBatchLocationId;
	}

	public void setTfBatchLocationId(long tfBatchLocationId) {
		this.tfBatchLocationId = tfBatchLocationId;
	}

	public String getTfBatchLocationName() {
		return this.tfBatchLocationName;
	}

	public void setTfBatchLocationName(String tfBatchLocationName) {
		this.tfBatchLocationName = tfBatchLocationName;
	}

	public List<TfBatch> getTfBatches() {
		return this.tfBatches;
	}

	public void setTfBatches(List<TfBatch> tfBatches) {
		this.tfBatches = tfBatches;
	}

	public TfBatch addTfBatch(TfBatch tfBatch) {
		getTfBatches().add(tfBatch);
		tfBatch.setTfBatchLocation(this);

		return tfBatch;
	}

	public TfBatch removeTfBatch(TfBatch tfBatch) {
		getTfBatches().remove(tfBatch);
		tfBatch.setTfBatchLocation(null);

		return tfBatch;
	}

}