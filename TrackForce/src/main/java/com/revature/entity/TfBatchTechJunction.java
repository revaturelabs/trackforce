package com.revature.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.revature.entity.TfBatch;
import com.revature.entity.TfTech;


@Entity
@Table(name="TF_BATCH_TECH_JUNCTION", schema="ADMIN")
@AssociationOverrides({
	@AssociationOverride(name="pk.batch", joinColumns=@JoinColumn(name="TF_BATCH_ID")),
	@AssociationOverride(name="pk.tech", joinColumns=@JoinColumn(name="TF_TECH_ID"))
})
public class TfBatchTechJunction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private BatchTechId pk = new BatchTechId();
	
	public TfBatchTechJunction() {}

	@EmbeddedId
	public BatchTechId getPk() {
		return pk;
	}

	public void setPk(BatchTechId pk) {
		this.pk = pk;
	}
	
	@Transient
	public TfBatch getBatch() {
		return getPk().getBatch();
	}
	
	public void setBatch(TfBatch batch) {
		getPk().setBatch(batch);
	}
	
	@Transient
	public TfTech getTech() {
		return getPk().getTech();
	}
	
	public void setTech(TfTech tech) {
		getPk().setTech(tech);
	}
	
	
	
	
}
