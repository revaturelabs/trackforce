package com.revature.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PlacementInfo  implements Serializable, Comparable<PlacementInfo> {

	private static final long serialVersionUID = -7105939432349410685L;
	private Integer id;
	private AssociateInfo tfAssociate;
	private Timestamp tfPlacementStartDate;
	private Timestamp tfPlacementEndDate;
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public AssociateInfo getTfAssociate() {
		return tfAssociate;
	}



	public void setTfAssociate(AssociateInfo tfAssociate) {
		this.tfAssociate = tfAssociate;
	}



	public Timestamp getTfPlacementStartDate() {
		return tfPlacementStartDate;
	}



	public void setTfPlacementStartDate(Timestamp tfPlacementStartDate) {
		this.tfPlacementStartDate = tfPlacementStartDate;
	}



	public Timestamp getTfPlacementEndDate() {
		return tfPlacementEndDate;
	}



	public void setTfPlacementEndDate(Timestamp tfPlacementEndDate) {
		this.tfPlacementEndDate = tfPlacementEndDate;
	}



	@Override
	public int compareTo(PlacementInfo o) {
		return this.id-o.getId();
	}

}
