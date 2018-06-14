package com.revature.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Created Trainer entity to be used to pull trainer information from the DB
 * @author Andy A
 * @since 6.18.06.08
 * 
 *
 */

@XmlRootElement
@Entity
@Table(name="TF_TRAINER", schema="ADMIN")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TfTrainer implements Serializable{
 
	private static final long serialVersionUID = 5149341380971468990L;
	
	@XmlElement
	@Id
	@Column(name="TRAINER_ID", unique = true, nullable = false)
	@SequenceGenerator(name = "TRAINER_SEQ_ID", allocationSize = 1, initialValue=1)
	@GeneratedValue(generator = "TRAINER_SEQ_ID", strategy = GenerationType.SEQUENCE)
	private int id;
	
	@XmlElement
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "TF_USER_ID")
	private TfUser user;
	
	@XmlElement
	@Column(name="TRAINER_FIRSTNAME")
	private String firstName;
	
	@XmlElement
	@Column(name="TRAINER_LASTNAME")
	private String lastName;
	
	@XmlElement
	@OneToMany(mappedBy = "trainer")
	private List<TfBatch> primary;
	
	@XmlElement
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="COTRAINER_BATCH",joinColumns= {@JoinColumn(name="TRAINER_ID")},inverseJoinColumns = {@JoinColumn(name="BATCH_ID")})
	private List<TfBatch> coTrainer = new ArrayList<>();
	
	@XmlElement
	@Column(name="TRAINER_ISAPPROVED")
	private int isApproved = 0;

	public TfTrainer() {
		super();
	}

	public TfTrainer(int id) {
		super();
		this.id = id;
	}

	public TfTrainer(int id, String firstName, String lastName, List<TfBatch> primary, List<TfBatch> coTrainer,
			int isApproved) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.primary = primary;
		this.coTrainer = coTrainer;
		this.isApproved = isApproved;
	}
	
	

	public TfUser getTfUser() {
		return user;
	}

	public void setTfUser(TfUser tfUser) {
		this.user = tfUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public List<TfBatch> getPrimary() {
		return primary;
	}

	public void setPrimary(List<TfBatch> primary) {
		this.primary = primary;
	}

	public List<TfBatch> getCoTrainer() {
		return coTrainer;
	}

	public void setCoTrainer(List<TfBatch> coTrainer) {
		this.coTrainer = coTrainer;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	
	
	@Override
	public String toString() {
		return "TfTrainer [id=" + id + ", tfUser=" + user + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", primary=" + primary + ", coTrainer=" + coTrainer + ", isApproved=" + isApproved + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coTrainer == null) ? 0 : coTrainer.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + isApproved;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((primary == null) ? 0 : primary.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		TfTrainer other = (TfTrainer) obj;
		if (coTrainer == null) {
			if (other.coTrainer != null)
				return false;
		} else if (!coTrainer.equals(other.coTrainer))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (isApproved != other.isApproved)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (primary == null) {
			if (other.primary != null)
				return false;
		} else if (!primary.equals(other.primary))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
	
	
	

}