package com.revature.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class BatchTechId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5847407459276440428L;
	private TfBatch batch;
	private TfTech tech;
	
	@ManyToOne
	public TfBatch getBatch() {
		return batch;
	}
	
	public void setBatch(TfBatch batch) {
		this.batch = batch;
	}
	
	@ManyToOne
	public TfTech getTech() {
		return tech;
	}
	
	public void setTech(TfTech tech) {
		this.tech = tech;
	}
}
