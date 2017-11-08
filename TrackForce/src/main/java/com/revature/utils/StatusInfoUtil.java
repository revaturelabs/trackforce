package com.revature.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.entity.TfAssociate;
import com.revature.model.StatusInfo;

public class StatusInfoUtil {

	private StatusInfoUtil() {
	}

	private static StatusInfo allAssociatesStatusInfo = new StatusInfo("All associates");
	private static Map<Integer, StatusInfo> specificClientStatusInfo = new HashMap<>();
	private static Map<Integer, StatusInfo> specificCurriculumStatusInfo = new HashMap<>();

	public static StatusInfo getAllAssociatesStatusInfo() {
		return getDeepCopyOfStatusInfo(allAssociatesStatusInfo);
	}

	public static StatusInfo getClientStatusInfo(int clientID) {
		return getDeepCopyOfStatusInfo(specificClientStatusInfo.get(clientID));
	}

	public static StatusInfo getCurriculumStatusInfo(int curriculumID) {
		return getDeepCopyOfStatusInfo(specificCurriculumStatusInfo.get(curriculumID));
	}

	public static void setAllAssociatesStatusInfo(StatusInfo statusInfo) {
		allAssociatesStatusInfo = statusInfo;
	}

	public static void putClientStatusInfo(int clientID, StatusInfo clientStatusInfo) {
		specificClientStatusInfo.put(clientID, clientStatusInfo);
	}

	public static void putCurriculumStatusInfo(int curriculumID, StatusInfo curriculumStatusInfo) {
		specificClientStatusInfo.put(curriculumID, curriculumStatusInfo);
	}

	public static List<StatusInfo> getSpecificClientStatusInfoAsList() {
		return new ArrayList<>(specificClientStatusInfo.values());
	}

	public static List<StatusInfo> getSpecificCurriculumStatusInfoAsList() {
		return new ArrayList<>(specificCurriculumStatusInfo.values());
	}

	private static StatusInfo getDeepCopyOfStatusInfo(StatusInfo statusInfo) {
		return new StatusInfo(statusInfo.getName(), statusInfo.getTrainingMapped(), statusInfo.getTrainingUnmapped(),
				statusInfo.getReservedMapped(), statusInfo.getOpenUnmapped(), statusInfo.getSelectedMapped(),
				statusInfo.getSelectedUnmapped(), statusInfo.getConfirmedMapped(), statusInfo.getConfirmedUnmapped(),
				statusInfo.getDeployedMapped(), statusInfo.getDeployedUnmapped());
	}

	/**
	 * Convenience method to clear the static maps
	 */
	public static void clearMaps() {
		specificClientStatusInfo.clear();
		specificCurriculumStatusInfo.clear();
	}

	/**
	 * Returns a StatusInfo object with updated counts based off of an associate's marketing
	 * status.
	 * 
	 * @param associates
	 *            A list of associates
	 * @return A StatusInfo object with updated counts based off of an associate
	 */
	public static StatusInfo updateStatusCount(StatusInfo statusInfo, TfAssociate associate) {
		switch (associate.getTfMarketingStatus().getTfMarketingStatusId().intValue()) {
		case 1:
			statusInfo.setTrainingMapped(statusInfo.getTrainingMapped() + 1);
			break;
		case 2:
			statusInfo.setReservedMapped(statusInfo.getReservedMapped() + 1);
			break;
		case 3:
			statusInfo.setSelectedMapped(statusInfo.getSelectedMapped() + 1);
			break;
		case 4:
			statusInfo.setConfirmedMapped(statusInfo.getConfirmedMapped() + 1);
			break;
		case 5:
			statusInfo.setDeployedMapped(statusInfo.getDeployedMapped() + 1);
			break;
		case 6:
			statusInfo.setTrainingUnmapped(statusInfo.getTrainingUnmapped() + 1);
			break;
		case 7:
			statusInfo.setOpenUnmapped(statusInfo.getOpenUnmapped() + 1);
			break;
		case 8:
			statusInfo.setSelectedUnmapped(statusInfo.getSelectedUnmapped() + 1);
			break;
		case 9:
			statusInfo.setConfirmedUnmapped(statusInfo.getConfirmedUnmapped() + 1);
			break;
		case 10:
			statusInfo.setDeployedUnmapped(statusInfo.getDeployedUnmapped() + 1);
			break;
		default:
			break;
		}
		return statusInfo;
	}
}
