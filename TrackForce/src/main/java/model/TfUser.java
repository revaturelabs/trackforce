package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TF_USER database table.
 * 
 */
@Entity
@Table(name="TF_USER")
@NamedQuery(name="TfUser.findAll", query="SELECT t FROM TfUser t")
public class TfUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TF_USER_ID")
	private long tfUserId;

	@Column(name="TF_HASHPASSWORD")
	private String tfHashpassword;

	@Column(name="TF_USERNAME")
	private String tfUsername;

	//bi-directional many-to-one association to TfAssociate
	@ManyToOne
	@JoinColumn(name="TF_ASSOCIATE_ID")
	private TfAssociate tfAssociate;

	//bi-directional many-to-one association to TfRole
	@ManyToOne
	@JoinColumn(name="TF_ROLE_ID")
	private TfRole tfRole;

	public TfUser() {
	}

	public long getTfUserId() {
		return this.tfUserId;
	}

	public void setTfUserId(long tfUserId) {
		this.tfUserId = tfUserId;
	}

	public String getTfHashpassword() {
		return this.tfHashpassword;
	}

	public void setTfHashpassword(String tfHashpassword) {
		this.tfHashpassword = tfHashpassword;
	}

	public String getTfUsername() {
		return this.tfUsername;
	}

	public void setTfUsername(String tfUsername) {
		this.tfUsername = tfUsername;
	}

	public TfAssociate getTfAssociate() {
		return this.tfAssociate;
	}

	public void setTfAssociate(TfAssociate tfAssociate) {
		this.tfAssociate = tfAssociate;
	}

	public TfRole getTfRole() {
		return this.tfRole;
	}

	public void setTfRole(TfRole tfRole) {
		this.tfRole = tfRole;
	}

}