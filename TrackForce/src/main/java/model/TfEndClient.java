package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TF_END_CLIENT database table.
 * 
 */
@Entity
@Table(name="TF_END_CLIENT")
@NamedQuery(name="TfEndClient.findAll", query="SELECT t FROM TfEndClient t")
public class TfEndClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TF_END_CLIENT_ID")
	private long tfEndClientId;

	@Column(name="TF_END_CLIENT_NAME")
	private String tfEndClientName;

	//bi-directional many-to-one association to TfAssociate
	@OneToMany(mappedBy="tfEndClient")
	private List<TfAssociate> tfAssociates;

	//bi-directional many-to-one association to TfInterview
	@OneToMany(mappedBy="tfEndClient")
	private List<TfInterview> tfInterviews;

	//bi-directional many-to-one association to TfPlacement
	@OneToMany(mappedBy="tfEndClient")
	private List<TfPlacement> tfPlacements;

	public TfEndClient() {
	}

	public long getTfEndClientId() {
		return this.tfEndClientId;
	}

	public void setTfEndClientId(long tfEndClientId) {
		this.tfEndClientId = tfEndClientId;
	}

	public String getTfEndClientName() {
		return this.tfEndClientName;
	}

	public void setTfEndClientName(String tfEndClientName) {
		this.tfEndClientName = tfEndClientName;
	}

	public List<TfAssociate> getTfAssociates() {
		return this.tfAssociates;
	}

	public void setTfAssociates(List<TfAssociate> tfAssociates) {
		this.tfAssociates = tfAssociates;
	}

	public TfAssociate addTfAssociate(TfAssociate tfAssociate) {
		getTfAssociates().add(tfAssociate);
		tfAssociate.setTfEndClient(this);

		return tfAssociate;
	}

	public TfAssociate removeTfAssociate(TfAssociate tfAssociate) {
		getTfAssociates().remove(tfAssociate);
		tfAssociate.setTfEndClient(null);

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
		tfInterview.setTfEndClient(this);

		return tfInterview;
	}

	public TfInterview removeTfInterview(TfInterview tfInterview) {
		getTfInterviews().remove(tfInterview);
		tfInterview.setTfEndClient(null);

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
		tfPlacement.setTfEndClient(this);

		return tfPlacement;
	}

	public TfPlacement removeTfPlacement(TfPlacement tfPlacement) {
		getTfPlacements().remove(tfPlacement);
		tfPlacement.setTfEndClient(null);

		return tfPlacement;
	}

}