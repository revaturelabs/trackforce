package com.revature.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN.TF_ASSOSCIATE")
public class Associate {
	@Id
	@GeneratedValue
	@Column(name = "TF_ASSOCIATE_ID")
	private int associateid;

	@Column(name = "TF_ASSOCIATE_FIRST_NAME")
	private String associatefirstname;

	@Column(name = "TF_ASSOCIATE_LAST_NAME")
	private String associatelastname;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private int marketingstatusid;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private int batchid;

	public Associate() {

	}

	public int getAssociateid() {
		return associateid;
	}

	public void setAssociateid(int associateid) {
		this.associateid = associateid;
	}

	public String getAssociatefirstname() {
		return associatefirstname;
	}

	public void setAssociatefirstname(String associatefirstname) {
		this.associatefirstname = associatefirstname;
	}

	public String getAssociatelastname() {
		return associatelastname;
	}

	public void setAssociatelastname(String associatelastname) {
		this.associatelastname = associatelastname;
	}

	public int getMarketingstatusid() {
		return marketingstatusid;
	}

	public void setMarketingstatusid(int marketingstatusid) {
		this.marketingstatusid = marketingstatusid;
	}

	public int getBatchid() {
		return batchid;
	}

	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}

	@Override
	public String toString() {
		return "Associate [associateid=" + associateid + ", associatefirstname=" + associatefirstname
				+ ", associatelastname=" + associatelastname + ", marketingstatusid=" + marketingstatusid + ", batchid="
				+ batchid + "]";
	}

}
