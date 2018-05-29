package com.revature.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.entity.TfMarketingStatus;

public class ClientInfo  implements Serializable, Comparable<ClientInfo> {
	static final Logger logger = Logger.getLogger(ClientInfo.class);

	private static final long serialVersionUID = 1718849365915804177L;
	private Integer id;
	private String tfClientName;
	private Set<PlacementInfo> tfPlacements = new HashSet<>();
	private Set<AssociateInfo> tfAssociates = new HashSet<>();
	private Set<InterviewInfo> tfInterviews = new HashSet<>();
	private StatusInfo stats = new StatusInfo();

	public Integer getId() {
		return id;
	}

	public void setTfClientId(Integer tfClientId) {
		this.id = tfClientId;
	}

	public String getTfClientName() {
		return tfClientName;
	}

	public void setTfClientName(String tfClientName) {
		this.tfClientName = tfClientName;
	}

	public Set<PlacementInfo> getTfPlacements() {
		return tfPlacements;
	}

	public void setTfPlacements(Set<PlacementInfo> tfPlacements) {
		this.tfPlacements = tfPlacements;
	}

	public Set<AssociateInfo> getTfAssociates() {
		return tfAssociates;
	}

	public void setTfAssociates(Set<AssociateInfo> tfAssociates) {
		this.tfAssociates = tfAssociates;
	}

	public Set<InterviewInfo> getTfInterviews() {
		return tfInterviews;
	}

	public void setTfInterviews(Set<InterviewInfo> tfInterviews) {
		this.tfInterviews = tfInterviews;
	}

	@Override
	public int compareTo(ClientInfo o) {
		return this.id-o.getId();
	}

	public void appendToMap(TfMarketingStatus tfMarketingStatus) {
		switch (tfMarketingStatus.getTfMarketingStatusId()) {
		case StatusInfo.MAPPED_TRAINING:
			stats.setTrainingMapped(stats.getTrainingMapped() + 1);
			break;
		case StatusInfo.MAPPED_RESERVED:
			stats.setReservedMapped(stats.getReservedMapped() + 1);
			break;
		case StatusInfo.MAPPED_SELECTED:
			stats.setSelectedMapped(stats.getSelectedMapped() + 1);
			break;
		case StatusInfo.MAPPED_CONFIRMED:
			stats.setConfirmedMapped(stats.getConfirmedMapped() + 1);
			break;
		case StatusInfo.MAPPED_DEPLOYED:
			stats.setDeployedMapped(stats.getDeployedMapped() + 1);
			break;
		case StatusInfo.UNMAPPED_TRAINING:
			stats.setTrainingUnmapped(stats.getTrainingUnmapped() + 1);
			break;
		case StatusInfo.UNMAPPED_OPEN:
			stats.setOpenUnmapped(stats.getOpenUnmapped() + 1);
			break;
		case StatusInfo.UNMAPPED_SELECTED:
			stats.setSelectedUnmapped(stats.getSelectedUnmapped() + 1);
			break;
		case StatusInfo.UNMAPPED_CONFIRMED:
			stats.setConfirmedUnmapped(stats.getConfirmedUnmapped() + 1);
			break;
		case StatusInfo.UNMAPPED_DEPLOYED:
			stats.setDeployedUnmapped(stats.getDeployedUnmapped() + 1);
			break;
		default:
			return;
		}
		stats.setName(getTfClientName());
		logger.debug(stats.getName() + ":\n" + stats.getTrainingMapped() + " " + stats.getReservedMapped() + " " + stats.getSelectedMapped() + " " + stats.getConfirmedMapped() + " " + stats.getDeployedMapped() + 
		" " + stats.getTrainingUnmapped() + " " + stats.getOpenUnmapped() + " " + stats.getSelectedUnmapped() + " " + stats.getConfirmedUnmapped() + " " + stats.getDeployedUnmapped());
	}

	public StatusInfo getStats() {
		return stats;
	}

	public void setStats(StatusInfo stats) {
		this.stats = stats;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
