package com.revature.model;

public class ClientInfo {
	
	private String name; // Name of client
	private int trainingMapped;
	private int trainingUnmapped;
	private int reservedMapped;
	private int reservedUnmapped;
	private int selectedMapped;
	private int selectedUnmapped;
	private int confirmedMapped;
	private int confirmedUnmapped;
	private int deployedMapped;
	private int deployedUnmapped;
	
	public ClientInfo() {
	}

	public ClientInfo(String name, int trainingMapped, int trainingUnmapped, int reservedMapped, int reservedUnmapped,
			int selectedMapped, int selectedUnmapped, int confirmedMapped, int confirmedUnmapped, int deployedMapped,
			int deployedUnmapped) {
		super();
		this.name = name;
		this.trainingMapped = trainingMapped;
		this.trainingUnmapped = trainingUnmapped;
		this.reservedMapped = reservedMapped;
		this.reservedUnmapped = reservedUnmapped;
		this.selectedMapped = selectedMapped;
		this.selectedUnmapped = selectedUnmapped;
		this.confirmedMapped = confirmedMapped;
		this.confirmedUnmapped = confirmedUnmapped;
		this.deployedMapped = deployedMapped;
		this.deployedUnmapped = deployedUnmapped;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTrainingMapped() {
		return trainingMapped;
	}

	public void setTrainingMapped(int trainingMapped) {
		this.trainingMapped = trainingMapped;
	}

	public int getTrainingUnmapped() {
		return trainingUnmapped;
	}

	public void setTrainingUnmapped(int trainingUnmapped) {
		this.trainingUnmapped = trainingUnmapped;
	}

	public int getReservedMapped() {
		return reservedMapped;
	}

	public void setReservedMapped(int reservedMapped) {
		this.reservedMapped = reservedMapped;
	}

	public int getReservedUnmapped() {
		return reservedUnmapped;
	}

	public void setReservedUnmapped(int reservedUnmapped) {
		this.reservedUnmapped = reservedUnmapped;
	}

	public int getSelectedMapped() {
		return selectedMapped;
	}

	public void setSelectedMapped(int selectedMapped) {
		this.selectedMapped = selectedMapped;
	}

	public int getSelectedUnmapped() {
		return selectedUnmapped;
	}

	public void setSelectedUnmapped(int selectedUnmapped) {
		this.selectedUnmapped = selectedUnmapped;
	}

	public int getConfirmedMapped() {
		return confirmedMapped;
	}

	public void setConfirmedMapped(int confirmedMapped) {
		this.confirmedMapped = confirmedMapped;
	}

	public int getConfirmedUnmapped() {
		return confirmedUnmapped;
	}

	public void setConfirmedUnmapped(int confirmedUnmapped) {
		this.confirmedUnmapped = confirmedUnmapped;
	}

	public int getDeployedMapped() {
		return deployedMapped;
	}

	public void setDeployedMapped(int deployedMapped) {
		this.deployedMapped = deployedMapped;
	}

	public int getDeployedUnmapped() {
		return deployedUnmapped;
	}

	public void setDeployedUnmapped(int deployedUnmapped) {
		this.deployedUnmapped = deployedUnmapped;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + confirmedMapped;
		result = prime * result + confirmedUnmapped;
		result = prime * result + deployedMapped;
		result = prime * result + deployedUnmapped;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + reservedMapped;
		result = prime * result + reservedUnmapped;
		result = prime * result + selectedMapped;
		result = prime * result + selectedUnmapped;
		result = prime * result + trainingMapped;
		result = prime * result + trainingUnmapped;
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
		ClientInfo other = (ClientInfo) obj;
		if (confirmedMapped != other.confirmedMapped)
			return false;
		if (confirmedUnmapped != other.confirmedUnmapped)
			return false;
		if (deployedMapped != other.deployedMapped)
			return false;
		if (deployedUnmapped != other.deployedUnmapped)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reservedMapped != other.reservedMapped)
			return false;
		if (reservedUnmapped != other.reservedUnmapped)
			return false;
		if (selectedMapped != other.selectedMapped)
			return false;
		if (selectedUnmapped != other.selectedUnmapped)
			return false;
		if (trainingMapped != other.trainingMapped)
			return false;
		if (trainingUnmapped != other.trainingUnmapped)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientInfo  [name=" + name + ", trainingMapped=" + trainingMapped + ", trainingUnmapped=" + trainingUnmapped
				+ ", reservedMapped=" + reservedMapped + ", reservedUnmapped=" + reservedUnmapped + ", selectedMapped="
				+ selectedMapped + ", selectedUnmapped=" + selectedUnmapped + ", confirmedMapped=" + confirmedMapped
				+ ", confirmedUnmapped=" + confirmedUnmapped + ", deployedMapped=" + deployedMapped
				+ ", deployedUnmapped=" + deployedUnmapped + "]";
	}
	
	

}
