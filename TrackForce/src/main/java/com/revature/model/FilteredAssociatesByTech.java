package com.revature.model;

public class FilteredAssociatesByTech {
	private String technology;
	private int numAssociates;
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public int getNumAssociates() {
		return numAssociates;
	}
	public void setNumAssociates(int numAssociates) {
		this.numAssociates = numAssociates;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numAssociates;
		result = prime * result + ((technology == null) ? 0 : technology.hashCode());
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
		FilteredAssociatesByTech other = (FilteredAssociatesByTech) obj;
		if (numAssociates != other.numAssociates)
			return false;
		if (technology == null) {
			if (other.technology != null)
				return false;
		} else if (!technology.equals(other.technology))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FilteredAssociatesByTech [technology=" + technology + ", numAssociates=" + numAssociates + "]";
	}
	public FilteredAssociatesByTech(String technology, int numAssociates) {
		super();
		this.technology = technology;
		this.numAssociates = numAssociates;
	}
}
