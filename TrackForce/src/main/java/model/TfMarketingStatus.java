package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TF_MARKETING_STATUS database table.
 * 
 */
@Entity
@Table(name="TF_MARKETING_STATUS")
@NamedQuery(name="TfMarketingStatus.findAll", query="SELECT t FROM TfMarketingStatus t")
public class TfMarketingStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TF_MARKETING_STATUS_ID")
	private long tfMarketingStatusId;

	@Column(name="TF_MARKETING_STATUS_NAME")
	private String tfMarketingStatusName;

	//bi-directional many-to-one association to TfAssociate
	@OneToMany(mappedBy="tfMarketingStatus")
	private List<TfAssociate> tfAssociates;

	public TfMarketingStatus() {
	}

	public long getTfMarketingStatusId() {
		return this.tfMarketingStatusId;
	}

	public void setTfMarketingStatusId(long tfMarketingStatusId) {
		this.tfMarketingStatusId = tfMarketingStatusId;
	}

	public String getTfMarketingStatusName() {
		return this.tfMarketingStatusName;
	}

	public void setTfMarketingStatusName(String tfMarketingStatusName) {
		this.tfMarketingStatusName = tfMarketingStatusName;
	}

	public List<TfAssociate> getTfAssociates() {
		return this.tfAssociates;
	}

	public void setTfAssociates(List<TfAssociate> tfAssociates) {
		this.tfAssociates = tfAssociates;
	}

	public TfAssociate addTfAssociate(TfAssociate tfAssociate) {
		getTfAssociates().add(tfAssociate);
		tfAssociate.setTfMarketingStatus(this);

		return tfAssociate;
	}

	public TfAssociate removeTfAssociate(TfAssociate tfAssociate) {
		getTfAssociates().remove(tfAssociate);
		tfAssociate.setTfMarketingStatus(null);

		return tfAssociate;
	}

}