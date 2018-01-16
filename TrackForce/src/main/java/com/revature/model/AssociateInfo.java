package com.revature.model;

import java.math.BigDecimal;

/**
 * Class to hold information about an associate for the batch details page.
 */
public class AssociateInfo implements Comparable<AssociateInfo> {

    private BigDecimal id;
    private String firstName;
    private String lastName;
    private String marketingStatus;
    private String client;
    private String endClient;
    private BigDecimal batchId;
    private String batchName;
    private String curriculumName;

    /**
     * Constructs a newly allocated BatchInfo object that represents an associate.
     *
     * @param id              - The id of the associate.
     * @param firstName       - The associate's first name.
     * @param lastName        - The associate's last name.
     * @param marketingStatus - The associate's marketing status (mapped, unmapped, etc.)
     * @param client          - The clients's name.
     */
    public AssociateInfo(BigDecimal id, String firstName, String lastName, String marketingStatus, String client) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.marketingStatus = marketingStatus;
        this.client = client;
    }

    public AssociateInfo(BigDecimal id, String firstName, String lastName, String marketingStatus, String client,
                         BigDecimal batchId, String batchName, String curriculumName) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.marketingStatus = marketingStatus;
        this.client = client;
        this.endClient = "";
        this.batchName = batchName;
        this.batchId = batchId;
        this.curriculumName = curriculumName;
    }

    public AssociateInfo() {
    }

    /**
     * Retrieve the associate's ID
     *
     * @return - String with the ID of the associate
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * Set's the associate's ID
     *
     * @param id - What to set the associate's ID to.
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * Retrieve the first name of the associate.
     *
     * @return - A string with the first name of the associate.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set's the first name of the associate.
     *
     * @param firstName - What to set the associate's first name to.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieve the last name of the associate.
     *
     * @return - A string with the last name of the associate.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the associate
     *
     * @param lastName - What to set the associate's last name to.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieve the associate's marketing status (mapped, unmapped, etc.)
     *
     * @return - A string with the associate's marketing status
     */
    public String getMarketingStatus() {
        return marketingStatus;
    }

    /**
     * Set the associate's marketing status (mapped, unmapped, etc.)
     *
     * @param marketingStatus - What to set the associate's marketing status to.
     */
    public void setMarketingStatus(String marketingStatus) {
        this.marketingStatus = marketingStatus;
    }

    /**
     * Retrieve the client name
     *
     * @return- A string with the client's name
     */

    public String getClient() {
        return client;
    }

    /**
     * Set the client's name
     *
     * @param client    -What to set the associate's client name to.
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * Retrieve the end client's name
     *
     * @return - A string with the name of the end client
     */
    public String getEndClient() {
        return endClient;
    }

    /**
     * Set the end client's name
     *
     * @param endClient
     */
    public void setEndClient(String endClient) {
        this.endClient = endClient;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public void setCurriculumName(String curriculumName) {
        this.curriculumName = curriculumName;
    }

    public BigDecimal getBatchId() {
        return batchId;
    }

    public void setBatchId(BigDecimal batchId) {
        this.batchId = batchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssociateInfo)) return false;

        AssociateInfo that = (AssociateInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (marketingStatus != null ? !marketingStatus.equals(that.marketingStatus) : that.marketingStatus != null)
            return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (endClient != null ? !endClient.equals(that.endClient) : that.endClient != null) return false;
        if (batchId != null ? !batchId.equals(that.batchId) : that.batchId != null) return false;
        if (batchName != null ? !batchName.equals(that.batchName) : that.batchName != null) return false;
        return curriculumName != null ? curriculumName.equals(that.curriculumName) : that.curriculumName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (marketingStatus != null ? marketingStatus.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (endClient != null ? endClient.hashCode() : 0);
        result = 31 * result + (batchId != null ? batchId.hashCode() : 0);
        result = 31 * result + (batchName != null ? batchName.hashCode() : 0);
        result = 31 * result + (curriculumName != null ? curriculumName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AssociateInfo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", marketingStatus='" + marketingStatus + '\'' +
                ", client='" + client + '\'' +
                ", endClient='" + endClient + '\'' +
                ", batchId=" + batchId +
                ", batchName='" + batchName + '\'' +
                ", curriculumName='" + curriculumName + '\'' +
                '}';
    }

    @Override
    public int compareTo(AssociateInfo o) {
        return this.id.subtract(o.id).intValueExact();
    }
}
