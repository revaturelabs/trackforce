package com.revature.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Cacheable
@Entity
@Table(name = "TF_USER", schema="ADMIN" /*uniqueConstraints= @UniqueConstraint(columnNames= {"TF_USERNAME"}) */)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="TrackForce")
public class TfUser implements java.io.Serializable 
{
    private static final long serialVersionUID = 706405344864879997L;
	public static final int APPROVED = 1;
    
    @XmlElement
    @Id
    @Column(name = "TF_USER_ID")
    /* ID's 1-14 are reserved for manual insertion */
    @SequenceGenerator(sequenceName = "UserId_seq", name = "UserIdSeq", initialValue=15)
    @GeneratedValue(generator = "UserIdSeq", strategy = GenerationType.SEQUENCE)
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
    
 	// This is just used for passing around the string token while logged in - Adam 06.18.06.13
    @XmlElement
    @Transient
    private String token;
    
    @XmlElement
    @Transient
    private Integer role;

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	@JsonIgnore
	public TfRole getTfRole() { return TfRole; }

	@JsonIgnore
	public void setTfRole(TfRole tfRole) { TfRole = tfRole; }

	public String getUsername() { return username; }

	public void setUsername(String username) { this.username = username; }
	
	@JsonIgnore
	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }

	public int getIsApproved() { return isApproved; }

	public void setIsApproved(int isApproved) { this.isApproved = isApproved; }

	public String getToken() { return token; }

	public void setToken(String token) { this.token = token; }

	public Integer getRole() { return role; }

	public void setRole(Integer role) { this.role = role; }

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

	/** @param obj the reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj
	 * argument; {@code false} otherwise. */
	@Override
	public boolean equals(Object obj) { return super.equals(obj); }

	@Override
	public String toString() {
		return "TfUser [id=" + id + ", username=" + username + ", password=" + password + ", isApproved=" + isApproved
				+ ", token=" + token + ", role=" + role + "]";
	}
}