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
    
    @XmlElement
    @Id
    @Column(name = "TF_USER_ID")
    /* ID's 1-14 are reserved for manual insertion */
    @SequenceGenerator(sequenceName = "UserId_seq", name = "UserIdSeq", initialValue=15)
    @GeneratedValue(generator = "UserIdSeq", strategy = GenerationType.SEQUENCE)
    private int tfUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TF_ROLE_ID")
    private TfRole tfRole;

    @XmlElement
    @Column(name = "TF_USERNAME", length = 20, unique = true)
    private String tfUserUsername;

    @XmlElement
    @Column(name = "TF_HASHPASSWORD", length = 200)
    private String tfHashpassword;
    
    @XmlElement
    @Column(name = "TF_ISAPPROVED")
    private boolean tf_isApproved;

    public TfUser() {
    }

    public TfUser(int tfUserId) {
        this.tfUserId = tfUserId;
    }

    public TfUser(int tfUserId, TfRole tfRole, String tfUserUsername, String tfUserHashpassword) {
        this.tfUserId = tfUserId;
        this.tfRole = tfRole;
        this.tfUserUsername = tfUserUsername;
        this.tfHashpassword = tfUserHashpassword;
    }

    public TfUser(Integer role, String username, String password) {
        this.tfRole = new TfRole(role);
        this.tfUserUsername = username;
        this.tfHashpassword = password;
    }

    // Overloaded method to aid creating new associate.
    public TfUser(String username, String password) {
        this.tfRole = new TfRole(5);
        this.tfUserUsername = username;
        this.tfHashpassword = password;
    }


	public TfUser(int tfUserId, TfRole tfRole, String tfUserUsername, String tfHashpassword, TfAssociate tfAssociate) {
		super();
		this.tfUserId = tfUserId;
		this.tfRole = tfRole;
		this.tfUserUsername = tfUserUsername;
		this.tfHashpassword = tfHashpassword;
	}

    public int getTfUserId() {
        return this.tfUserId;
    }

    public void setTfUserId(int tfUserId) {
        this.tfUserId = tfUserId;
    }

    public TfRole getTfRole() {
        return this.tfRole;
    }

    public void setTfRole(TfRole tfRole) {
        this.tfRole = tfRole;
    }


    public String getTfUserUsername() {
        return this.tfUserUsername;
    }

    public void setTfUserUsername(String tfUserUsername) {
        this.tfUserUsername = tfUserUsername;
    }

    public String getTfUserHashpassword() {
        return this.tfHashpassword;
    }

    public void setTfUserHashpassword(String tfUserHashpassword) {
        this.tfHashpassword = tfUserHashpassword;
    }

    public boolean isTf_isApproved() {
		return tf_isApproved;
	}

	public void setTf_isApproved(boolean tf_isApproved) {
		this.tf_isApproved = tf_isApproved;
	}

	
	
	@Override
	public String toString() {
		return "TfUser [tfUserId=" + tfUserId + ", tfRole=" + tfRole + ", tfUserUsername=" + tfUserUsername
				+ ", tfHashpassword=" + tfHashpassword + ", tf_isApproved=" + tf_isApproved + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tfHashpassword == null) ? 0 : tfHashpassword.hashCode());
		result = prime * result + ((tfRole == null) ? 0 : tfRole.hashCode());
		result = prime * result + tfUserId;
		result = prime * result + ((tfUserUsername == null) ? 0 : tfUserUsername.hashCode());
		result = prime * result + (tf_isApproved ? 1231 : 1237);
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
		if (tfHashpassword == null) {
			if (other.tfHashpassword != null)
				return false;
		} else if (!tfHashpassword.equals(other.tfHashpassword))
			return false;
		if (tfRole == null) {
			if (other.tfRole != null)
				return false;
		} else if (!tfRole.equals(other.tfRole))
			return false;
		if (tfUserId != other.tfUserId)
			return false;
		if (tfUserUsername == null) {
			if (other.tfUserUsername != null)
				return false;
		} else if (!tfUserUsername.equals(other.tfUserUsername))
			return false;
		if (tf_isApproved != other.tf_isApproved)
			return false;
		return true;
	}
}
