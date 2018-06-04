package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TF_ROLE database table.
 * 
 */
@Entity
@Table(name="TF_ROLE")
@NamedQuery(name="TfRole.findAll", query="SELECT t FROM TfRole t")
public class TfRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TF_ROLE_ID")
	private long tfRoleId;

	@Column(name="TF_ROLE_NAME")
	private String tfRoleName;

	//bi-directional many-to-one association to TfUser
	@OneToMany(mappedBy="tfRole")
	private List<TfUser> tfUsers;

	public TfRole() {
	}

	public long getTfRoleId() {
		return this.tfRoleId;
	}

	public void setTfRoleId(long tfRoleId) {
		this.tfRoleId = tfRoleId;
	}

	public String getTfRoleName() {
		return this.tfRoleName;
	}

	public void setTfRoleName(String tfRoleName) {
		this.tfRoleName = tfRoleName;
	}

	public List<TfUser> getTfUsers() {
		return this.tfUsers;
	}

	public void setTfUsers(List<TfUser> tfUsers) {
		this.tfUsers = tfUsers;
	}

	public TfUser addTfUser(TfUser tfUser) {
		getTfUsers().add(tfUser);
		tfUser.setTfRole(this);

		return tfUser;
	}

	public TfUser removeTfUser(TfUser tfUser) {
		getTfUsers().remove(tfUser);
		tfUser.setTfRole(null);

		return tfUser;
	}

}