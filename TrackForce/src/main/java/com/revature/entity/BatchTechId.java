package com.revature.entity;

import javax.persistence.CascadeType;
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
	
	@ManyToOne(cascade=CascadeType.ALL)
	public TfBatch getBatch() {
		return batch;
	}
	
	public void setBatch(TfBatch batch) {
		this.batch = batch;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public TfTech getTech() {
		return tech;
	}
	
	public void setTech(TfTech tech) {
		this.tech = tech;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatchTechId that = (BatchTechId) o;

        if (batch != null ? !batch.equals(that.batch) : that.batch != null) return false;
        if (tech != null ? !tech.equals(that.tech) : that.tech != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (batch != null ? batch.hashCode() : 0);
        result = 31 * result + (tech != null ? tech.hashCode() : 0);
        return result;
    }
}
