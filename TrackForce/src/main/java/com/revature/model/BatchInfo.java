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

    /**
     * Constructs a newly allocated BatchInfo object that represents an empty batch.
     */
    public BatchInfo() {
        super();
    }

    /**
     * Constructs a newly allocated BatchInfo object that represents a batch of Revature assodiates without curriculum or location.
     * 
     * @param batchName - The name of the batch.
     * @param clientName - The name of the client this batch is mapped to.
     * @param startDate - The date that this batch started.
     * @param endDate - The date that this batch graduates.
     */
    public BatchInfo(String batchName, String clientName, String startDate, String endDate) {
        super();
        this.batchName = batchName;
        this.clientName = clientName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs a newly allocated BatchInfo object that represents a batch of Revature associates.
     * 
     * @param batchName - The name of the batch.
     * @param clientName - The name of the client this batch is mapped to.
     * @param curriculumName - The name of the curriculum this batch is studying.
     * @param location - The location of this batch.
     * @param startDate - The date that this batch started.
     * @param endDate - The date that this batch graduates.
     */
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

    /**
     * Retrieves the batch name
     * @return - A string with the batch name
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * Sets the batch name to the given value.
     * @param batchName - What to set the batch name to.
     */
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    /**
     * Retrieves the name of the client this batch is mapped to.
     * @return - A string with the client name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the name to the given value of the client this batch is mapped to.
     * @param clientName - What to set the client's name to.
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Retrieve the name of the curriculum this batch is studying.
     * @return - A string with the curriculum name.
     */
    public String getCurriculumName() {
        return curriculumName;
    }

    /**
     * Sets the name of the curriculum this batch is studying.
     * @param curriculumName - What to set the curriculum's name to.
     */
    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    /**
     * Retrieve the location where this batch is.
     * @return - A string with the batch's location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the location where this batch is
     * @param location - What to set the batch's location to
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Retrieve the date that this batch started.
     * @return - A string with the batch's start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Set the date that this batch started.
     * @param startDate - What to set the start date to.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Retrieve the date that this batch graduates.
     * @return - A string with the batch's end date.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Set the date that this batch graduates
     * @param endDate - What to set the batch's start date to.
     */
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
