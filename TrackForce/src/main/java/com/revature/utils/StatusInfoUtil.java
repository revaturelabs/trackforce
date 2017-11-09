package com.revature.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfCurriculum;
import com.revature.model.StatusInfo;

public class StatusInfoUtil {

	private StatusInfoUtil() {
	}

	private static String allAssociatesStatusInfoName = "All associates";
	private static StatusInfo allAssociatesStatusInfo = new StatusInfo(allAssociatesStatusInfoName);
	private static Map<Integer, StatusInfo> specificClientStatusInfo = new HashMap<>();
	private static Map<Integer, StatusInfo> specificCurriculumStatusInfo = new HashMap<>();

	public static List<StatusInfo> getSpecificClientStatusInfoAsList() {
		return new ArrayList<>(specificClientStatusInfo.values());
	}

	public static List<StatusInfo> getSpecificCurriculumStatusInfoAsList() {
		return new ArrayList<>(specificCurriculumStatusInfo.values());
	}

	public static StatusInfo getAllAssociatesStatusInfo() {
		return getDeepCopyOfStatusInfo(allAssociatesStatusInfo);
	}

	public static StatusInfo getClientStatusInfo(int clientID) {
		StatusInfo clientStatusInfo = specificClientStatusInfo.get(clientID);
		if (clientStatusInfo != null)
			return getDeepCopyOfStatusInfo(clientStatusInfo);
		else
			return new StatusInfo();
	}

	public static StatusInfo getCurriculumStatusInfo(int curriculumID) {
		StatusInfo curriculumStatusInfo = specificCurriculumStatusInfo.get(curriculumID);
		if (curriculumStatusInfo != null)
			return getDeepCopyOfStatusInfo(curriculumStatusInfo);
		else
			return new StatusInfo();
	}
	
	public static Map<Integer, StatusInfo> get(int statusID){
		
	}

	private static void setAllAssociatesStatusInfo(StatusInfo statusInfo) {
		allAssociatesStatusInfo = statusInfo;
	}

	private static void putClientStatusInfo(int clientID, StatusInfo clientStatusInfo) {
		specificClientStatusInfo.put(clientID, clientStatusInfo);
	}

	private static void putCurriculumStatusInfo(int curriculumID, StatusInfo curriculumStatusInfo) {
		specificCurriculumStatusInfo.put(curriculumID, curriculumStatusInfo);
	}

	/**
	 * Updates the status count for status info from all associates and the status
	 * counts for specific clients based on the status of specific associates'
	 * statuses.
	 * 
	 * @param associates
	 *            List of all associates
	 */
	public static void updateStatusInfoFromAssociates(List<TfAssociate> associates) {
		StatusInfo allAssociatesStatusInfo = new StatusInfo(allAssociatesStatusInfoName);
		for (TfAssociate associate : associates) {
			// increment allAssociatesStatusInfo for every associate
			incrementStatusCount(allAssociatesStatusInfo, associate);

			// increment specificClientStatusInfo based on associates with a mapped client
			TfClient tfClient = associate.getTfClient();
			if (tfClient != null) {
				int clientID = tfClient.getTfClientId().intValue();
				StatusInfo clientStatusInfo = getClientStatusInfo(clientID);

				// if clientStatusInfo is not in specificClientStatusInfo map, set name
				if (clientStatusInfo.equals(new StatusInfo())) {
					clientStatusInfo.setName(tfClient.getTfClientName());
				}
				incrementStatusCount(clientStatusInfo, associate);
				putClientStatusInfo(clientID, clientStatusInfo);
			}

			// increment specificCurriculumStatusInfo based on associates with a curriculum
			TfBatch tfBatch = associate.getTfBatch();
			if (tfBatch != null) {
				TfCurriculum tfCurriculum = tfBatch.getTfCurriculum();
				if (tfCurriculum != null) {
					int curriculumID = tfCurriculum.getTfCurriculumId().intValue();
					StatusInfo curriculumStatusInfo = getCurriculumStatusInfo(curriculumID);

					// if curriculumStatusInfo is not in specificCurriculumStatusInfo map, set name
					if (curriculumStatusInfo.equals(new StatusInfo())) {
						curriculumStatusInfo.setName(tfCurriculum.getTfCurriculumName());
					}
					incrementStatusCount(curriculumStatusInfo, associate);
					putCurriculumStatusInfo(curriculumID, curriculumStatusInfo);
				}
			}
		}
		setAllAssociatesStatusInfo(allAssociatesStatusInfo);
	}

	/**
	 * Returns a StatusInfo object with updated counts based off of an associate's
	 * marketing status.
	 * 
	 * @param associates
	 *            A list of associates
	 * @return A StatusInfo object with updated counts based off of an associate
	 */
	private static StatusInfo incrementStatusCount(StatusInfo statusInfo, TfAssociate associate) {
		// increment status count based on status id
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
			// unused id
			break;
		}
		return statusInfo;
	}

	private static StatusInfo getDeepCopyOfStatusInfo(StatusInfo statusInfo) {
		return new StatusInfo(statusInfo.getName(), statusInfo.getTrainingMapped(), statusInfo.getTrainingUnmapped(),
				statusInfo.getReservedMapped(), statusInfo.getOpenUnmapped(), statusInfo.getSelectedMapped(),
				statusInfo.getSelectedUnmapped(), statusInfo.getConfirmedMapped(), statusInfo.getConfirmedUnmapped(),
				statusInfo.getDeployedMapped(), statusInfo.getDeployedUnmapped());
	}

	/**
	 * Convenience method to clear the static maps.
	 */
	public static void clearMaps() {
		specificClientStatusInfo.clear();
		specificCurriculumStatusInfo.clear();
	}
}
