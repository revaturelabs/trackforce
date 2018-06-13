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
	private Integer id;
	
	@XmlElement
	@Column(name = "TF_ROLE_NAME", length = 20)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tfRole")
	private Set<TfUser> users = new HashSet<TfUser>(0);

	public TfRole() {
	}

	public TfRole(Integer tfRoleId) {
		this.id = tfRoleId;
	}

	public TfRole(Integer tfRoleId, String tfRoleName, Set<TfUser> tfUsers) {
		this.id = tfRoleId;
		this.name = tfRoleName;
		this.users = tfUsers;
	}

	
	public Integer getTfRoleId() {
		return this.id;
	}

	public void setTfRoleId(Integer tfRoleId) {
		this.id = tfRoleId;
	}

	
	public String getTfRoleName() {
		return this.name;
	}

	public void setTfRoleName(String tfRoleName) {
		this.name = tfRoleName;
	}

	
	public Set<TfUser> getTfUsers() {
		return this.users;
	}

	public void setTfUsers(Set<TfUser> tfUsers) {
		this.users = tfUsers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TfRole [tfRoleId=" + id + ", tfRoleName=" + name + "]";
	}
	
	

}
