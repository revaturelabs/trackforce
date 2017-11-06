package com.revature.model;

/**
 * This class models all information that pertains to clients
 * @author Jacob Hackel, Jarrod Bieber
 * @version 1.0
 */
public class ClientInfo {

	private String name; // Name of client
	private int trainingMapped;
	private int trainingUnmapped;
	private int reservedMapped;
	private int openUnmapped;
	private int selectedMapped;
	private int selectedUnmapped;
	private int confirmedMapped;
	private int confirmedUnmapped;
	private int deployedMapped;
	private int deployedUnmapped;
	
	/**
	 * Default no-arg constructor
	 */
	public ClientInfo() {
	}
	
	/**
	 * Constructs a ClientInfo object
	 * @param name The name of the client
	 * @param trainingMapped The number of associates who are currently training and mapped to this client.
	 * @param trainingUnmapped The number of associates who are currently training and not mapped to this client.
	 * @param reservedMapped The number of associates who are finished training and mapped to this client.
	 * @param openUnmapped The number of associates who are finished training and are not mapped to this client.
	 * @param selectedMapped The number of associates who have been selected by, and were mapped to this client.
	 * @param selectedUnmapped The number of associates who have been selected by, but were not mapped to this client.
	 * @param confirmedMapped The number of associates who have been confirmed by and were mapped to this client.
	 * @param confirmedUnmapped The number of associates who have been confirmed, and were not mapped to this client.
	 * @param deployedMapped The number of associates who are deployed to a client location and were mapped to this client.
	 * @param deployedUnmapped The number of associates who are deployed to a client location and were not mapped to this client.
	 */
	public ClientInfo(String name, int trainingMapped, int trainingUnmapped, int reservedMapped, int openUnmapped,
			int selectedMapped, int selectedUnmapped, int confirmedMapped, int confirmedUnmapped, int deployedMapped,
			int deployedUnmapped) {
		super();
		this.name = name;
		this.trainingMapped = trainingMapped;
		this.trainingUnmapped = trainingUnmapped;
		this.reservedMapped = reservedMapped;
		this.openUnmapped = openUnmapped;
		this.selectedMapped = selectedMapped;
		this.selectedUnmapped = selectedUnmapped;
		this.confirmedMapped = confirmedMapped;
		this.confirmedUnmapped = confirmedUnmapped;
		this.deployedMapped = deployedMapped;
		this.deployedUnmapped = deployedUnmapped;
	}
	
	/**
	 * This returns the name of this client
	 * @return this client's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This allows the client name to be set
	 * @param name the name to set for this client
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This returns the number associates who are mapped and training
	 * @return the number of mapped associates who are training
	 */
	public int getTrainingMapped() {
		return trainingMapped;
	}
	
	/**
	 * This allows the number of mapped associates who are training to be set
	 * @param trainingMapped the number of mapped associates who are training
	 */
	public void setTrainingMapped(int trainingMapped) {
		this.trainingMapped = trainingMapped;
	}
	
	/**
	 * This returns the number of associates who are unmapped and training
	 * @return the number of unmapped associates who are training
	 */
	public int getTrainingUnmapped() {
		return trainingUnmapped;
	}

	/**
	 * This allows the number of unmapped associates who are training to be set.
	 * @param trainingUnmapped the number of unmapped associates who are training
	 */
	public void setTrainingUnmapped(int trainingUnmapped) {
		this.trainingUnmapped = trainingUnmapped;
	}
	
	
	public void confirmed(int trainingUnmapped) {
		this.trainingUnmapped = trainingUnmapped;
	}
	
	/**
	 * This returns the number of associates who are mapped and finished with training.
	 * @return the number of mapped associates who are done training
	 */
	public int getReservedMapped() {
		return reservedMapped;
	}
	
	/**
	 * This allows the number of mapped associates who are finished training to be set.
	 * @param reservedMapped the number of unmapped associates who are done training.
	 */
	public void setReservedMapped(int reservedMapped) {
		this.reservedMapped = reservedMapped;
	}
	
	public int getOpenUnmapped() {
		return openUnmapped;
	}
	
	public void setOpenUnmapped(int openUnmapped) {
		this.openUnmapped = openUnmapped;
	}

	/**
	 * This returns the number of mapped associates who have been selected
	 * by the client.
	 * @return The number of mapped associates who were selected by this client.
	 */
	public int getSelectedMapped() {
		return selectedMapped;
	}
	
	/**
	 * This allows the number of mapped associates who have been selected
	 * by the client to be set.
	 * @param selectedMapped The number of mapped associates who are selected by this client.
	 */
	public void setSelectedMapped(int selectedMapped) {
		this.selectedMapped = selectedMapped;
	}
	
	/**
	 * This returns the number of associates who were selected by the client, but weren't mapped.
	 * @return The number of unmapped associates who were selected by this client.
	 */
	public int getSelectedUnmapped() {
		return selectedUnmapped;
	}
	
	
	/**
	 * This allows the number of associates who are selected by the client,
	 * but aren't mapped, to be set.
	 * @param selectedUnmapped The number of unmapped associates who are selected.
	 */
	public void setSelectedUnmapped(int selectedUnmapped) {
		this.selectedUnmapped = selectedUnmapped;
	}
	
	/**
	 * This returns the number of associates who were mapped and selected
	 * by the client.
	 * @return The number of mapped associates who were confirmed by this client.
	 */
	public int getConfirmedMapped() {
		return confirmedMapped;
	}
	
	/**
	 * This allows the number of associates who were mapped and confirmed
	 * by this client to be set.
	 * @param confirmedMapped The number of mapped associates who were confirmed by
	 * this client.
	 */
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
		result = prime * result + openUnmapped;
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
		if (openUnmapped != other.openUnmapped)
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
		return "ClientInfo  [name=" + name + ", trainingMapped=" + trainingMapped + ", trainingUnmapped="
				+ trainingUnmapped + ", reservedMapped=" + reservedMapped + ", openUnmapped=" + openUnmapped
				+ ", selectedMapped=" + selectedMapped + ", selectedUnmapped=" + selectedUnmapped + ", confirmedMapped="
				+ confirmedMapped + ", confirmedUnmapped=" + confirmedUnmapped + ", deployedMapped=" + deployedMapped
				+ ", deployedUnmapped=" + deployedUnmapped + "]";
	}

}
