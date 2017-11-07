package com.revature.model;

/**
 * Class to hold information about an associate for the batch details page.
 */
public class AssociateInfo {

    private String id;
    private String firstName;
    private String lastName;
    private String marketingStatus;
    private String client;
    
    /**
     * Constructs a newly allocated AssociateInfo object that represents an empty associate.
     */
    public AssociateInfo() {
        super();
    }

    /**
     * Constructs a newly allocated BatchInfo object that represents an associate.
     * @param id - The id of the associate.
     * @param firstName - The associate's first name.
     * @param lastName - The associate's last name.
     * @param marketingStatus - The associate's marketing status (mapped, unmapped, etc.)
     */
    public AssociateInfo(String id, String firstName, String lastName, String marketingStatus, String client) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.marketingStatus = marketingStatus;
        this.client = client;
    }

    /**
     * Retrieve the associate's ID
     * @return - String with the ID of the associate
     */
    public String getId() {
        return id;
    }

    /**
     * Set's the associate's ID
     * @param id - What to set the associate's ID to.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieve the first name of the associate.
     * @return - A string with the first name of the associate.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set's the first name of the associate.
     * @param firstName - What to set the associate's first name to.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieve the last name of the associate.
     * @return - A string with the last name of the associate.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the associate
     * @param lastName - What to set the associate's last name to.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieve the associate's marketing status (mapped, unmapped, etc.)
     * @return - A string with the associate's marketing status
     */
    public String getMarketingStatus() {
        return marketingStatus;
    }

    /**
     * Set the associate's marketing status (mapped, unmapped, etc.)
     * @param marketingStatus - What to set the associate's marketing status to.
     */
    public void setMarketingStatus(String marketingStatus) {
        this.marketingStatus = marketingStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((marketingStatus == null) ? 0 : marketingStatus.hashCode());
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
        AssociateInfo other = (AssociateInfo) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (marketingStatus == null) {
            if (other.marketingStatus != null)
                return false;
        } else if (!marketingStatus.equals(other.marketingStatus))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AssociateInfo [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", marketingStatus="
                + marketingStatus + "]";
    }
}
