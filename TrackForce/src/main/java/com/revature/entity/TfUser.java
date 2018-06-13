package com.revature.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;



/**
 * <p> </p>
 * @version.date v06.2018.06.13
 */
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
    private int id;
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TF_ROLE_ID")
    @JsonIgnore
    private TfRole tfRole;

    @XmlElement
    @Column(name = "TF_USERNAME", length = 20, unique = true)
    private String tfUserUsername;

    @XmlElement
    @Column(name = "TF_HASHPASSWORD", length = 200)
    private String password;
    
    @XmlElement
    @Column(name = "TF_ISAPPROVED")
    private int approved;
    
    // This is just used for passing around the string token while logged in - Adam 06.2018.06.13
    @XmlElement
    @Transient
    private String token;
    
    // This allows for easier transport of the role to the web application - Adam 06.2018.06.13
    @XmlElement
    @Transient
    private Integer role;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

	public TfRole getTfRole() {
		return tfRole;
	}

	public void setTfRole(TfRole tfRole) {
		this.tfRole = tfRole;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + approved;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((tfRole == null) ? 0 : tfRole.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (approved != other.approved)
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (tfRole == null) {
			if (other.tfRole != null)
				return false;
		} else if (!tfRole.equals(other.tfRole))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
 
	@Override
	public String toString() {
		return "TfUser [id=" + id + ", tfRole=" + tfRole + ", username=" + username + ", password=" + password
				+ ", approved=" + approved + ", token=" + token + "]";
	}

    
}
