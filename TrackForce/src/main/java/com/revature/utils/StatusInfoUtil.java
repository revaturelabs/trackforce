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

/**
 * Helper class that compiles collections of StatusInfo objects.
 */
public class StatusInfoUtil {

    private StatusInfoUtil() {
    }

    private static String allAssociatesStatusInfoName = "All associates";
    private static StatusInfo allAssociatesStatusInfo = new StatusInfo(allAssociatesStatusInfoName);
    private static Map<Integer, StatusInfo> specificClientStatusInfo = new HashMap<>();
    private static Map<Integer, StatusInfo> specificCurriculumStatusInfo = new HashMap<>();

    /**
     * Retrieves a list of associate StatusInfo objects based on each client.
     * @return a list of StatusInfo objects
     */
    public static List<StatusInfo> getSpecificClientStatusInfoAsList() {
        return new ArrayList<>(specificClientStatusInfo.values());
    }

    /**
     * Retrieves a list of associate StatusInfo objects based on each curriculum type.
     * @return a list of StatusInfo objects
     */
    public static List<StatusInfo> getSpecificCurriculumStatusInfoAsList() {
        return new ArrayList<>(specificCurriculumStatusInfo.values());
    }

    /**
     * Returns a StatusInfo object compiling the status counts for all Revature associates
     * across the company.
     * @return a StatusInfo object containing all associate information.
     */
    public static StatusInfo getAllAssociatesStatusInfo() {
        return getDeepCopyOfStatusInfo(allAssociatesStatusInfo);
    }

    /**
     * Returns a StatusInfo object compiling the status counts for associates
     * tied to a specific client.
     * @param clientID the unique identifier of an individual client
     * @return a StatusInfo object of the associates for this client
     */
    public static StatusInfo getClientStatusInfo(int clientID) {
        StatusInfo clientStatusInfo = specificClientStatusInfo.get(clientID);
        if (clientStatusInfo != null)
            return getDeepCopyOfStatusInfo(clientStatusInfo);
        else
            return new StatusInfo();
    }

    /**
     * Returns a StatusInfo object compiling the status counts for associates
     * tied to a specific curriculum (Java, Salesforce, etc).
     * @param curriculumID the unique identifier of a curriculum type
     * @return a StatusInfo object of the associates for this curriculum
     */
    public static StatusInfo getCurriculumStatusInfo(int curriculumID) {
        StatusInfo curriculumStatusInfo = specificCurriculumStatusInfo.get(curriculumID);
        if (curriculumStatusInfo != null)
            return getDeepCopyOfStatusInfo(curriculumStatusInfo);
        else
            return new StatusInfo();
    }

    /**
     * Returns a list of maps. The key is the name of a client. The value is the number of
     * associates tied to that client, who also fall under the given marketing status.
     * @param statusID the ID for the desired marketing status
     * @return A list of client name / associate count maps
     */
    public static List<Map<String, Object>> getClientsBasedOnStatusID(int statusID) {
        List<StatusInfo> clientStatusInfos = getSpecificClientStatusInfoAsList();
        return getStageBasedOnStatusInfosAndStatusID(clientStatusInfos, statusID);
    }

    /**
     * Returns a list of maps. The key is the name of a curriculum. The value is the number of
     * associates tied to that curriculum, who also fall under the given marketing status.
     * @param statusID the ID for the desired marketing status
     * @return A list of curriculum name / associate count maps
     */
    public static List<Map<String, Object>> getCurriculumsBasedOnStatusID(int statusID) {
        List<StatusInfo> curriculumStatusInfos = getSpecificCurriculumStatusInfoAsList();
        return getStageBasedOnStatusInfosAndStatusID(curriculumStatusInfos, statusID);
    }

    /**
     * This method takes a list of StatusInfo objects that pertain to a 
     * specific metric, such as a list for clients, or a list for curriculums.
     * From each StatusInfo object in the list, a map is created
     * comprising of a StatusInfo name, and the count of associates in a specific status.
     * A list of all the created maps is returned.
     * @param statusInfos
     * @param statusID
     * @return
     */
    private static List<Map<String, Object>> getStageBasedOnStatusInfosAndStatusID(List<StatusInfo> statusInfos, int statusID) {
        List<Map<String, Object>> maps = new ArrayList<>();
        // if valid statusID, add map for each statusInfo
        if (statusID >= 1 && statusID <= 10) {
            for (StatusInfo statusInfo : statusInfos) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", statusInfo.getName());
                map.put("count", getStatusCount(statusInfo, statusID));
                maps.add(map);
            }
        }
        return maps;
    }

    /**
     * Sets the StatusInfo object that represents counts of all Revature associates
     * to the given StatusInfo object.
     * @param statusInfo The StatusInfo object containing counts for all Revature associates
     */
    private static void setAllAssociatesStatusInfo(StatusInfo statusInfo) {
        allAssociatesStatusInfo = statusInfo;
    }

    /**
     * Adds a mapping into the specificClientStatusInfo map with a given client,
     * and it's related StatusInfo object
     * @param clientID the unique identifier of a client
     * @param clientStatusInfo the StatusInfo object associated with a client
     */
    private static void putClientStatusInfo(int clientID, StatusInfo clientStatusInfo) {
        specificClientStatusInfo.put(clientID, clientStatusInfo);
    }

    /**
     * Adds a mapping into the specificCurriculumStatusInfo map with a given curriculum,
     * and it's related StatusInfo object
     * @param curriculumID the unique identifier of a curriculum
     * @param curriculumStatusInfo the StatusInfo object associated with a curriculum
     */
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

    /**
     * Returns the count of associates who fall under a specific marketing status
     * (Training/mapped, open/unmapped, deployed/mapped, etc).
     * @param statusInfo a StatusInfo object containing counts of associates
     * @param statusID the desired marketing status ID to pull a count from
     * @return a count of associates with a specific marketing status
     */
    private static int getStatusCount(StatusInfo statusInfo, int statusID) {
        switch (statusID) {
            case 1:
                return statusInfo.getTrainingMapped();
            case 2:
                return statusInfo.getReservedMapped();
            case 3:
                return statusInfo.getSelectedMapped();
            case 4:
                return statusInfo.getConfirmedMapped();
            case 5:
                return statusInfo.getDeployedMapped();
            case 6:
                return statusInfo.getTrainingUnmapped();
            case 7:
                return statusInfo.getOpenUnmapped();
            case 8:
                return statusInfo.getSelectedUnmapped();
            case 9:
                return statusInfo.getConfirmedUnmapped();
            case 10:
                return statusInfo.getDeployedUnmapped();
            default:
                // not handled error case
                return -1;
        }
    }

    /**
     * Gets a deep copy of a StatusInfo object, based on it's states 
     * (since StatusInfo is mutable, this preserves the original)
     * @param statusInfo the StatusInfo object you want to copy
     * @return a new StatusInfo object that is a deep copy of the original
     */
    private static StatusInfo getDeepCopyOfStatusInfo(StatusInfo statusInfo) {
        return new StatusInfo(statusInfo.getName(), statusInfo.getTrainingMapped(), statusInfo.getTrainingUnmapped(), statusInfo.getReservedMapped(),
                statusInfo.getOpenUnmapped(), statusInfo.getSelectedMapped(), statusInfo.getSelectedUnmapped(), statusInfo.getConfirmedMapped(),
                statusInfo.getConfirmedUnmapped(), statusInfo.getDeployedMapped(), statusInfo.getDeployedUnmapped());
    }

    /**
     * Convenience method to clear the static maps.
     */
    public static void clearMaps() {
        specificClientStatusInfo.clear();
        specificCurriculumStatusInfo.clear();
    }

    /**
     * If any map saved in StatusInfoUtil is null or empty, returns true.<br>
     * Else, returns false.
     * 
     * @return a boolean value based on maps being filled or not.
     */
    public static boolean mapsAreEmpty() {
        if (specificClientStatusInfo == null || specificCurriculumStatusInfo == null)
            return true;
        return specificClientStatusInfo.isEmpty() || specificCurriculumStatusInfo.isEmpty();
    }
}
