package com.revature.model;

/**
 * Class to hold information about the batch for the batch listing page.
 */
public class BatchInfo {

    private Long id;
    private String batchName;
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
     * @param startDate - The date that this batch started.
     * @param endDate - The date that this batch graduates.
     */
    public BatchInfo(String batchName, String startDate, String endDate) {
        super();
        this.batchName = batchName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs a newly allocated BatchInfo object that represents a batch of Revature associates.
     * 
     * @param batchName - The name of the batch.
     * @param curriculumName - The name of the curriculum this batch is studying.
     * @param location - The location of this batch.
     * @param startDate - The date that this batch started.
     * @param endDate - The date that this batch graduates.
     */
    public BatchInfo(Long id, String batchName, String curriculumName, String location, String startDate, String endDate) {
        super();
        this.id = id;
        this.batchName = batchName;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BatchInfo)) return false;

        BatchInfo batchInfo = (BatchInfo) o;

        if (id != null ? !id.equals(batchInfo.id) : batchInfo.id != null) return false;
        if (batchName != null ? !batchName.equals(batchInfo.batchName) : batchInfo.batchName != null) return false;
        if (curriculumName != null ? !curriculumName.equals(batchInfo.curriculumName) : batchInfo.curriculumName != null)
            return false;
        if (location != null ? !location.equals(batchInfo.location) : batchInfo.location != null) return false;
        if (startDate != null ? !startDate.equals(batchInfo.startDate) : batchInfo.startDate != null) return false;
        return endDate != null ? endDate.equals(batchInfo.endDate) : batchInfo.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (batchName != null ? batchName.hashCode() : 0);
        result = 31 * result + (curriculumName != null ? curriculumName.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BatchInfo{" +
                "id=" + id +
                ", batchName='" + batchName + '\'' +
                ", curriculumName='" + curriculumName + '\'' +
                ", location='" + location + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
