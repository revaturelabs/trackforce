package com.revature.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@XmlRootElement
@Entity
@Table(name = "TF_USER", schema="ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TfUser implements java.io.Serializable {

    private static final long serialVersionUID = 706405344864879997L;
    
	public static final int APPROVED = 1;
	public static final int NOTAPPROVED = 0;
    
    @XmlElement
    @Id
    @Column(name = "TF_USER_ID")
    /* ID's 1-14 are reserved for manual insertion */
    @SequenceGenerator(sequenceName = "UserId_seq", name = "UserIdSeq", initialValue=15)
    @GeneratedValue(generator = "UserIdSeq", strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TF_ROLE_ID")
    private TfRole role;

    @XmlElement
    @Column(name = "TF_USERNAME", length = 20, unique = true)
    private String username;

    @XmlElement
    @Column(name = "TF_HASHPASSWORD", length = 200)
    private String hashedPassword;
    
    @XmlElement
    @Column(name = "TF_ISAPPROVED")
    private int isApproved;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "TF_ASSOCIATE_ID")
    private TfAssociate tfAssociate;

    public TfUser() {
    }

    public TfUser(int tfUserId) {
        this.id = tfUserId;
    }

    public TfUser(int tfUserId, TfRole tfRole, String tfUserUsername, String tfUserHashpassword) {
        this.id = tfUserId;
        this.role = tfRole;
        this.username = tfUserUsername;
        this.hashedPassword = tfUserHashpassword;
    }

    public TfUser(Integer role, String username, String password) {
        this.role = new TfRole(role);
        this.username = username;
        this.hashedPassword = password;
    }

    // Overloaded method to aid creating new associate.
    public TfUser(String username, String password) {
        this.role = new TfRole(5);
        this.username = username;
        this.hashedPassword = password;
    }


	public TfUser(int tfUserId, TfRole tfRole, String tfUserUsername, String tfHashpassword, TfAssociate tfAssociate) {
		super();
		this.id = tfUserId;
		this.role = tfRole;
		this.username = tfUserUsername;
		this.hashedPassword = tfHashpassword;
	}

    public int getTfUserId() {
        return this.id;
    }

    public void setTfUserId(int tfUserId) {
        this.id = tfUserId;
    }

    public TfRole getTfRole() {
        return this.role;
    }

    public void setTfRole(TfRole tfRole) {
        this.role = tfRole;
    }


    public String getTfUserUsername() {
        return this.username;
    }

    public void setTfUserUsername(String tfUserUsername) {
        this.username = tfUserUsername;
    }

    public String getTfUserHashpassword() {
        return this.hashedPassword;
    }

    public void setTfUserHashpassword(String tfUserHashpassword) {
        this.hashedPassword = tfUserHashpassword;
    }

    public int isTf_isApproved() {
		return isApproved;
	}

	public void setTf_isApproved(int tf_isApproved) {
		this.isApproved = tf_isApproved;
	}

	
	
	
	@Override
	public String toString() {
		return "TfUser [tfUserId=" + id + ", tfRole=" + role + ", tfUserUsername=" + username
				+ ", tfHashpassword=" + hashedPassword + ", tf_isApproved=" + isApproved + "]";
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashedPassword == null) ? 0 : hashedPassword.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + id;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + isApproved;
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
		TfUser other = (TfUser) obj;
		if (hashedPassword == null) {
			if (other.hashedPassword != null)
				return false;
		} else if (!hashedPassword.equals(other.hashedPassword))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (isApproved != other.isApproved)
			return false;
		return true;
	}
    
    
}
