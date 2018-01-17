package com.revature.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class EndClientInfo  implements Serializable, Comparable<EndClientInfo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8948358262222400399L;
	private BigDecimal id;
	private String tfEndClientName;
	private Set<AssociateInfo> tfAssociates = new HashSet<>();
	private Set<PlacementInfo> tfPlacements = new HashSet<>();
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getTfEndClientName() {
		return tfEndClientName;
	}
	public void setTfEndClientName(String tfEndClientName) {
		this.tfEndClientName = tfEndClientName;
	}
	public Set<AssociateInfo> getTfAssociates() {
		return tfAssociates;
	}
	public void setTfAssociates(Set<AssociateInfo> tfAssociates) {
		this.tfAssociates = tfAssociates;
	}
	public Set<PlacementInfo> getTfPlacements() {
		return tfPlacements;
	}
	public void setTfPlacements(Set<PlacementInfo> tfPlacements) {
		this.tfPlacements = tfPlacements;
	}
	@Override
	public int compareTo(EndClientInfo o) {
		return this.id.subtract(o.getId()).intValueExact();
	}
}
