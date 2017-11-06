package com.revature.model;

/**
 * Class to hold information about the batch for the batch listing page.
 */
public class BatchInfo {

    private String batchName;
    private String clientName;
    private String curriculumName;
    private String location;
    private String startDate;
    private String endDate;

    public BatchInfo() {
        super();
    }

    public BatchInfo(String batchName, String clientName, String startDate, String endDate) {
        super();
        this.batchName = batchName;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public BatchInfo(String batchName, String clientName, String curriculumName, String location, String startDate,
            String endDate) {
        super();
        this.batchName = batchName;
        this.clientName = clientName;
        this.curriculumName = curriculumName;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((batchName == null) ? 0 : batchName.hashCode());
        result = prime * result + ((clientName == null) ? 0 : clientName.hashCode());
        result = prime * result + ((curriculumName == null) ? 0 : curriculumName.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BatchInfo other = (BatchInfo) obj;
        if (batchName == null) {
            if (other.batchName != null)
                return false;
        } else if (!batchName.equals(other.batchName))
            return false;
        if (clientName == null) {
            if (other.clientName != null)
                return false;
        } else if (!clientName.equals(other.clientName))
            return false;
        if (curriculumName == null) {
            if (other.curriculumName != null)
                return false;
        } else if (!curriculumName.equals(other.curriculumName))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BatchInfo [batchName=" + batchName + ", clientName=" + clientName + ", curriculumName=" + curriculumName
                + ", location=" + location + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }

}
