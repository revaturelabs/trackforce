package com.revature.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@Cacheable
@Entity
@Table(name = "TF_USER", schema="ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="TrackForce")
public class TfUser implements java.io.Serializable {

    private static final long serialVersionUID = 706405344864879997L;
    
	public static final int APPROVED = 1;
	public static final int NOTAPPROVED = 0;
    
    @XmlElement
    @Id
    @Column(name = "TF_USER_ID")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TF_ROLE_ID")
    private TfRole TfRole;
    
    @XmlElement
    @Column(name = "TF_USERNAME", length = 20, unique = true)
    private String username;

    @JsonIgnore
    @XmlElement
    @Column(name = "TF_HASHPASSWORD", length = 200)
    private String password;
    
    @XmlElement
    @Column(name = "TF_ISAPPROVED")
    private int isApproved;
    
    @XmlElement
    @Transient
    private String token;
    
    @XmlElement
    @Transient
    private Integer role;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	public TfRole getTfRole() {
		return TfRole;
	}
	@JsonIgnore
	public void setTfRole(TfRole tfRole) {
		TfRole = tfRole;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((TfRole == null) ? 0 : TfRole.hashCode());
		result = prime * result + id;
		result = prime * result + isApproved;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			System.out.println(this.toString() + "and" + obj.toString());
			return true;}
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TfUser other = (TfUser) obj;
		if (TfRole == null) {
			if (other.TfRole != null)
				return false;
		} else if (!TfRole.equals(other.TfRole))
			return false;
		if (id != other.id)
			return false;
		if (isApproved != other.isApproved)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		System.out.println("You Passed!");
		return true;
	}

	@Override
	public String toString() {
		return "TfUser [id=" + id + ", username=" + username + ", password=" + password + ", isApproved=" + isApproved
				+ ", token=" + token + ", role=" + role + "]";
	}
	
}
