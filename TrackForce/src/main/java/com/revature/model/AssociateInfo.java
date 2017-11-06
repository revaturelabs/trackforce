package com.revature.model;

public class AssociateInfo {

    private String id;
    private String firstName;
    private String lastName;
    private String marketingStatus;
    
    public AssociateInfo() {
        super();
    }

    public AssociateInfo(String id, String firstName, String lastName, String marketingStatus) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.marketingStatus = marketingStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMarketingStatus() {
        return marketingStatus;
    }

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
