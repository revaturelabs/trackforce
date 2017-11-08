package com.revature.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.model.StatusInfo;

public class StatusInfoUtil {

	private StatusInfo allClientStatusInfo = new StatusInfo("All clients");
	private Map<Integer, StatusInfo> specificClientStatusInfo = new HashMap<>();
	private Map<Integer, StatusInfo> specificCurriculumStatusInfo = new HashMap<>();

	StatusInfo getClientStatusInfo(int clientID) {
		return specificClientStatusInfo.get(clientID);
	}

	StatusInfo getCurriculumStatusInfo(int curriculumID) {
		return specificCurriculumStatusInfo.get(curriculumID);
	}

	void addClientStatusInfo(int clientID, StatusInfo clientStatusInfo) {
		specificClientStatusInfo.put(clientID, clientStatusInfo);
	}

	void addCurriculumStatusInfo(int curriculumID, StatusInfo curriculumStatusInfo) {
		specificClientStatusInfo.put(curriculumID, curriculumStatusInfo);
	}

	List<StatusInfo> getSpecificClientStatusInfoAsList() {
		return new ArrayList<>(specificClientStatusInfo.values());
	}

	List<StatusInfo> getSpecificCurriculumStatusInfoAsList() {
		return new ArrayList<>(specificCurriculumStatusInfo.values());
	}

}
