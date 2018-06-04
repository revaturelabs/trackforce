package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the TF_PLACEMENT database table.
 * 
 */
@Entity
@Table(name="TF_PLACEMENT")
@NamedQuery(name="TfPlacement.findAll", query="SELECT t FROM TfPlacement t")
public class TfPlacement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TF_PLACEMENT_ID")
	private long tfPlacementId;

	@Column(name="TF_PLACEMENT_END_DATE")
	private Timestamp tfPlacementEndDate;

	@Column(name="TF_PLACEMENT_START_DATE")
	private Timestamp tfPlacementStartDate;

	//bi-directional many-to-one association to TfAssociate
	@ManyToOne
	@JoinColumn(name="TF_ASSOCIATE_ID")
	private TfAssociate tfAssociate;

	//bi-directional many-to-one association to TfClient
	@ManyToOne
	@JoinColumn(name="TF_CLIENT_ID")
	private TfClient tfClient;

	//bi-directional many-to-one association to TfEndClient
	@ManyToOne
	@JoinColumn(name="TF_END_CLIENT_ID")
	private TfEndClient tfEndClient;

	public TfPlacement() {
	}

	public long getTfPlacementId() {
		return this.tfPlacementId;
	}

	public void setTfPlacementId(long tfPlacementId) {
		this.tfPlacementId = tfPlacementId;
	}

	public Timestamp getTfPlacementEndDate() {
		return this.tfPlacementEndDate;
	}

	public void setTfPlacementEndDate(Timestamp tfPlacementEndDate) {
		this.tfPlacementEndDate = tfPlacementEndDate;
	}

	public Timestamp getTfPlacementStartDate() {
		return this.tfPlacementStartDate;
	}

	public void setTfPlacementStartDate(Timestamp tfPlacementStartDate) {
		this.tfPlacementStartDate = tfPlacementStartDate;
	}

	public TfAssociate getTfAssociate() {
		return this.tfAssociate;
	}

	public void setTfAssociate(TfAssociate tfAssociate) {
		this.tfAssociate = tfAssociate;
	}

	public TfClient getTfClient() {
		return this.tfClient;
	}

	public void setTfClient(TfClient tfClient) {
		this.tfClient = tfClient;
	}

	public TfEndClient getTfEndClient() {
		return this.tfEndClient;
	}

	public void setTfEndClient(TfEndClient tfEndClient) {
		this.tfEndClient = tfEndClient;
	}

}