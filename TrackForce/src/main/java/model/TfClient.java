package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TF_CLIENT database table.
 * 
 */
@Entity
@Table(name="TF_CLIENT")
@NamedQuery(name="TfClient.findAll", query="SELECT t FROM TfClient t")
public class TfClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TF_CLIENT_ID")
	private long tfClientId;

	@Column(name="TF_CLIENT_NAME")
	private String tfClientName;

	//bi-directional many-to-one association to TfAssociate
	@OneToMany(mappedBy="tfClient")
	private List<TfAssociate> tfAssociates;

	//bi-directional many-to-one association to TfInterview
	@OneToMany(mappedBy="tfClient")
	private List<TfInterview> tfInterviews;

	//bi-directional many-to-one association to TfPlacement
	@OneToMany(mappedBy="tfClient")
	private List<TfPlacement> tfPlacements;

	public TfClient() {
	}

	public long getTfClientId() {
		return this.tfClientId;
	}

	public void setTfClientId(long tfClientId) {
		this.tfClientId = tfClientId;
	}

	public String getTfClientName() {
		return this.tfClientName;
	}

	public void setTfClientName(String tfClientName) {
		this.tfClientName = tfClientName;
	}

	public List<TfAssociate> getTfAssociates() {
		return this.tfAssociates;
	}

	public void setTfAssociates(List<TfAssociate> tfAssociates) {
		this.tfAssociates = tfAssociates;
	}

	public TfAssociate addTfAssociate(TfAssociate tfAssociate) {
		getTfAssociates().add(tfAssociate);
		tfAssociate.setTfClient(this);

		return tfAssociate;
	}

	public TfAssociate removeTfAssociate(TfAssociate tfAssociate) {
		getTfAssociates().remove(tfAssociate);
		tfAssociate.setTfClient(null);

		return tfAssociate;
	}

	public List<TfInterview> getTfInterviews() {
		return this.tfInterviews;
	}

	public void setTfInterviews(List<TfInterview> tfInterviews) {
		this.tfInterviews = tfInterviews;
	}

	public TfInterview addTfInterview(TfInterview tfInterview) {
		getTfInterviews().add(tfInterview);
		tfInterview.setTfClient(this);

		return tfInterview;
	}

	public TfInterview removeTfInterview(TfInterview tfInterview) {
		getTfInterviews().remove(tfInterview);
		tfInterview.setTfClient(null);

		return tfInterview;
	}

	public List<TfPlacement> getTfPlacements() {
		return this.tfPlacements;
	}

	public void setTfPlacements(List<TfPlacement> tfPlacements) {
		this.tfPlacements = tfPlacements;
	}

	public TfPlacement addTfPlacement(TfPlacement tfPlacement) {
		getTfPlacements().add(tfPlacement);
		tfPlacement.setTfClient(this);

		return tfPlacement;
	}

	public TfPlacement removeTfPlacement(TfPlacement tfPlacement) {
		getTfPlacements().remove(tfPlacement);
		tfPlacement.setTfClient(null);

		return tfPlacement;
	}

}