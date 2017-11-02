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
@Table(name = "ADMIN.TF_BATCH")

public class Batch {
	@Id
	@GeneratedValue

	@Column(name = "tf_batch_id")
	private int batchid;

	@Column(name = "tf_batch_name")
	private String batchName;

	@Column(name = "tf_batch_start_date")
	private int batchstartdate;

	@Column(name = "tf_batch_end_date")
	private int batchenddate;

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private int clientid;

	@ManyToOne(fetch=FetchType.EAGER)
	private int currriculumid;

	public int getBatchid() {
		return batchid;
	}

	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public int getBatchstartdate() {
		return batchstartdate;
	}

	public void setBatchstartdate(int batchstartdate) {
		this.batchstartdate = batchstartdate;
	}

	public int getBatchenddate() {
		return batchenddate;
	}

	public void setBatchenddate(int batchenddate) {
		this.batchenddate = batchenddate;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public int getCurrriculumid() {
		return currriculumid;
	}

	public void setCurrriculumid(int currriculumid) {
		this.currriculumid = currriculumid;
	}

	@Override
	public String toString() {
		return "Batch [batchid=" + batchid + ", batchName=" + batchName + ", batchstartdate=" + batchstartdate
				+ ", batchenddate=" + batchenddate + ", clientid=" + clientid + ", currriculumid=" + currriculumid
				+ "]";
	}
	
}