package com.revature.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="TF_BATCH_TECH_JUNCTION")
@AssociationOverrides({
	@AssociationOverride(name="pk.TF_BATCH", joinColumns=@JoinColumn(name="TF_BATCH_ID")),
	@AssociationOverride(name="pk.TF_TECH", joinColumns=@JoinColumn(name="TF_TECH_ID"))
})
public class TfBatchTechJunction {

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
		return this.getPk().getBatch();
	}
	
	public void setBatch(TfBatch batch) {
		getPk().setBatch(batch);
	}
	
	@Transient
	public TfTech getTech() {
		return this.getPk().getTech();
	}
	
	public void setTech(TfTech tech) {
		getPk().setTech(tech);
	}
	
	
	
	
}
