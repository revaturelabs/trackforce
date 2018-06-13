package com.revature.entity;
// Generated Nov 7, 2017 9:24:46 PM by Hibernate Tools 5.2.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@XmlRootElement
@Entity
@Table(name = "TF_ROLE", schema = "ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TfRole implements java.io.Serializable {

	private static final long serialVersionUID = 2827764589977541041L;
	
	@XmlElement
	@Id
	@Column(name = "TF_ROLE_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Integer tfRoleId;
	
	@XmlElement
	@Column(name = "TF_ROLE_NAME", length = 20)
	private String tfRoleName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfRole")
	private Set<TfUser> tfUsers = new HashSet<TfUser>(0);

	public TfRole() {
	}

	public TfRole(Integer tfRoleId) {
		this.tfRoleId = tfRoleId;
	}

	public TfRole(Integer tfRoleId, String tfRoleName, Set<TfUser> tfUsers) {
		this.tfRoleId = tfRoleId;
		this.tfRoleName = tfRoleName;
		this.tfUsers = tfUsers;
	}

	
	public Integer getTfRoleId() {
		return this.tfRoleId;
	}

	public void setTfRoleId(Integer tfRoleId) {
		this.tfRoleId = tfRoleId;
	}

	
	public String getTfRoleName() {
		return this.tfRoleName;
	}

	public void setTfRoleName(String tfRoleName) {
		this.tfRoleName = tfRoleName;
	}

	
	public Set<TfUser> getTfUsers() {
		return this.tfUsers;
	}

	public void setTfUsers(Set<TfUser> tfUsers) {
		this.tfUsers = tfUsers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tfRoleId == null) ? 0 : tfRoleId.hashCode());
		result = prime * result + ((tfRoleName == null) ? 0 : tfRoleName.hashCode());
		result = prime * result + ((tfUsers == null) ? 0 : tfUsers.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TfRole other = (TfRole) obj;
		if (tfRoleId == null) {
			if (other.tfRoleId != null)
				return false;
		} else if (!tfRoleId.equals(other.tfRoleId))
			return false;
		if (tfRoleName == null) {
			if (other.tfRoleName != null)
				return false;
		} else if (!tfRoleName.equals(other.tfRoleName))
			return false;
		if (tfUsers == null) {
			if (other.tfUsers != null)
				return false;
		} else if (!tfUsers.equals(other.tfUsers))
			return false;
		return true;
	}
	
	

}
