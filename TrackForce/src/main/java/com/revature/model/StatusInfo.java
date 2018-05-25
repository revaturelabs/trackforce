package com.revature.model;

import java.io.Serializable;

/**
 * This class models all information that pertains to mapped/unmapped statuses
 */
public class StatusInfo implements Serializable {

	private static final long serialVersionUID = -5362503104883791588L;
	public static final int MAPPED_TRAINING = 1;
	public static final int MAPPED_RESERVED = 2;
	public static final int MAPPED_SELECTED = 3;
	public static final int MAPPED_CONFIRMED = 4;
	public static final int MAPPED_DEPLOYED = 5;

	public static final int UNMAPPED_TRAINING = 6;
	public static final int UNMAPPED_OPEN = 7;
	public static final int UNMAPPED_SELECTED = 8;
	public static final int UNMAPPED_CONFIRMED = 9;
	public static final int UNMAPPED_DEPLOYED = 10;

	public static final int DIRECTLY_PLACED = 11;
	public static final int TERMINATED = 12;

	private String name;
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
	 * Default no-arg constructor for an empty StatusInfo object.
	 */
	public StatusInfo() {
		this.name = "";
	}

	/**
	 * Constructs a StatusInfo object with a name.
	 *
	 * @param name
	 *            The name of the client
	 */
	public StatusInfo(String name) {
		this.name = name;
	}

	public void clear() {
		this.name = "";
		this.trainingMapped = 0;
		this.trainingUnmapped = 0;
		this.reservedMapped = 0;
		this.openUnmapped = 0;
		this.selectedMapped = 0;
		this.selectedUnmapped = 0;
		this.confirmedMapped = 0;
		this.confirmedUnmapped = 0;
		this.deployedMapped = 0;
		this.deployedUnmapped = 0;
	}

	/**
	 * Constructs a StatusInfo object.
	 *
	 * @param name
	 *            The name of the client
	 * @param trainingMapped
	 *            The number of associates who are currently training and mapped to
	 *            this client.
	 * @param trainingUnmapped
	 *            The number of associates who are currently training and not mapped
	 *            to this client.
	 * @param reservedMapped
	 *            The number of associates who are finished training and mapped to
	 *            this client.
	 * @param openUnmapped
	 *            The number of associates who are finished training and are not
	 *            mapped to this client.
	 * @param selectedMapped
	 *            The number of associates who have been selected by, and were
	 *            mapped to this client.
	 * @param selectedUnmapped
	 *            The number of associates who have been selected by, but were not
	 *            mapped to this client.
	 * @param confirmedMapped
	 *            The number of associates who have been confirmed by and were
	 *            mapped to this client.
	 * @param confirmedUnmapped
	 *            The number of associates who have been confirmed, and were not
	 *            mapped to this client.
	 * @param deployedMapped
	 *            The number of associates who are deployed to a client location and
	 *            were mapped to this client.
	 * @param deployedUnmapped
	 *            The number of associates who are deployed to a client location and
	 *            were not mapped to this client.
	 */
	public StatusInfo(String name, int trainingMapped, int trainingUnmapped, int reservedMapped, int openUnmapped,
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
	 * This returns the name of this object.
	 *
	 * @return this object's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This allows the object name to be set.
	 *
	 * @param name
	 *            the name to set for this object.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This returns the number associates who are mapped and training.
	 *
	 * @return the number of mapped associates who are training.
	 */
	public int getTrainingMapped() {
		return trainingMapped;
	}

	/**
	 * This allows the number of mapped associates who are training to be set
	 *
	 * @param trainingMapped
	 *            the number of mapped associates who are training.
	 */
	public void setTrainingMapped(int trainingMapped) {
		this.trainingMapped = trainingMapped;
	}

	/**
	 * This returns the number of associates who are unmapped and training
	 *
	 * @return the number of unmapped associates who are training.
	 */
	public int getTrainingUnmapped() {
		return trainingUnmapped;
	}

	/**
	 * This allows the number of unmapped associates who are training to be set.
	 *
	 * @param trainingUnmapped
	 *            the number of unmapped associates who are training.
	 */
	public void setTrainingUnmapped(int trainingUnmapped) {
		this.trainingUnmapped = trainingUnmapped;
	}

	/**
	 * This returns the number of associates who are mapped and finished with
	 * training.
	 *
	 * @return the number of mapped associates who are done training.
	 */
	public int getReservedMapped() {
		return reservedMapped;
	}

	/**
	 * This allows the number of mapped associates who are finished training to be
	 * set.
	 *
	 * @param reservedMapped
	 *            the number of unmapped associates who are done training.
	 */
	public void setReservedMapped(int reservedMapped) {
		this.reservedMapped = reservedMapped;
	}

	/**
	 * This returns the number of associates who are finished training, and aren't
	 * mapped to this object.
	 *
	 * @return The number of unmapped associates who are finished training.
	 */
	public int getOpenUnmapped() {
		return openUnmapped;
	}

	/**
	 * This allows the number of unmapped associates who are finished training to be
	 * set.
	 *
	 * @param openUnmapped
	 *            The number of unmapped associates who are finished training.
	 */
	public void setOpenUnmapped(int openUnmapped) {
		this.openUnmapped = openUnmapped;
	}

	/**
	 * This returns the number of mapped associates who have been selected by this
	 * object.
	 *
	 * @return The number of mapped associates who were selected by this object.
	 */
	public int getSelectedMapped() {
		return selectedMapped;
	}

	/**
	 * This allows the number of mapped associates who have been selected by this
	 * object to be set.
	 *
	 * @param selectedMapped
	 *            The number of mapped associates who are selected by this object.
	 */
	public void setSelectedMapped(int selectedMapped) {
		this.selectedMapped = selectedMapped;
	}

	/**
	 * This returns the number of associates who were selected by the object, but
	 * weren't mapped.
	 *
	 * @return The number of unmapped associates who were selected by this object.
	 */
	public int getSelectedUnmapped() {
		return selectedUnmapped;
	}

	/**
	 * This allows the number of associates who are selected by the object, but
	 * aren't mapped, to be set.
	 *
	 * @param selectedUnmapped
	 *            The number of unmapped associates who are selected by this object.
	 */
	public void setSelectedUnmapped(int selectedUnmapped) {
		this.selectedUnmapped = selectedUnmapped;
	}

	/**
	 * This returns the number of associates who were mapped and selected by the
	 * object.
	 *
	 * @return The number of mapped associates who were confirmed by this object.
	 */
	public int getConfirmedMapped() {
		return confirmedMapped;
	}

	/**
	 * This allows the number of associates who were mapped and confirmed by this
	 * object to be set.
	 *
	 * @param confirmedMapped
	 *            The number of mapped associates who are confirmed by this object.
	 */
	public void setConfirmedMapped(int confirmedMapped) {
		this.confirmedMapped = confirmedMapped;
	}

	/**
	 * This returns the number of associates who aren't mapped, and who have been
	 * confirmed by the object.
	 *
	 * @return The number of mapped associates who were confirmed by this object.
	 */
	public int getConfirmedUnmapped() {
		return confirmedUnmapped;
	}

	/**
	 * This allows the number of associates who are not mapped, but have been
	 * confirmed by the object, to be set.
	 *
	 * @param confirmedUnmapped
	 *            The number of unmapped associates who are confirmed by this
	 *            object.
	 */
	public void setConfirmedUnmapped(int confirmedUnmapped) {
		this.confirmedUnmapped = confirmedUnmapped;
	}

	/**
	 * This returns the number of associates who were mapped, and are deployed at a
	 * object location.
	 *
	 * @return The number of mapped associates who are deployed with a object.
	 */
	public int getDeployedMapped() {
		return deployedMapped;
	}

	/**
	 * This allows the number of associates who were mapped, and are deployed at a
	 * object location, to be set.
	 *
	 * @param deployedMapped
	 *            The number of mapped associates who have deployed with this
	 *            object.
	 */
	public void setDeployedMapped(int deployedMapped) {
		this.deployedMapped = deployedMapped;
	}

	/**
	 * This returns the number of associates who were mapped, and are deployed with
	 * this object.
	 *
	 * @return The number of unmapped associates who are deployed with this object.
	 */
	public int getDeployedUnmapped() {
		return deployedUnmapped;
	}

	/**
	 * This allows the number of associates who are unmapped, and are deployed with
	 * this object, to be set.
	 *
	 * @param deployedUnmapped
	 *            The number of unmapped associates who are deployed with this
	 *            object.
	 */
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
		StatusInfo other = (StatusInfo) obj;
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
		return trainingUnmapped != other.trainingUnmapped;
	}

	@Override
	public String toString() {
		return "StatusInfo [name=" + name + ", trainingMapped=" + trainingMapped + ", trainingUnmapped="
				+ trainingUnmapped + ", reservedMapped=" + reservedMapped + ", openUnmapped=" + openUnmapped
				+ ", selectedMapped=" + selectedMapped + ", selectedUnmapped=" + selectedUnmapped + ", confirmedMapped="
				+ confirmedMapped + ", confirmedUnmapped=" + confirmedUnmapped + ", deployedMapped=" + deployedMapped
				+ ", deployedUnmapped=" + deployedUnmapped + "]";
	}

	public void subtractFromMap(Integer id) {

		switch (id) {
		case StatusInfo.MAPPED_TRAINING:
			setTrainingMapped(getTrainingMapped() - 1);
			break;
		case StatusInfo.MAPPED_RESERVED:
			setReservedMapped(getReservedMapped() - 1);
			break;
		case StatusInfo.MAPPED_SELECTED:
			setSelectedMapped(getSelectedMapped() - 1);
			break;
		case StatusInfo.MAPPED_CONFIRMED:
			setConfirmedMapped(getConfirmedMapped() - 1);
			break;
		case StatusInfo.MAPPED_DEPLOYED:
			setDeployedMapped(getDeployedMapped() - 1);
			break;
		case StatusInfo.UNMAPPED_TRAINING:
			setTrainingUnmapped(getTrainingUnmapped() - 1);
			break;
		case StatusInfo.UNMAPPED_OPEN:
			setOpenUnmapped(getOpenUnmapped() - 1);
			break;
		case StatusInfo.UNMAPPED_SELECTED:
			setSelectedUnmapped(getSelectedUnmapped() - 1);
			break;
		case StatusInfo.UNMAPPED_CONFIRMED:
			setConfirmedUnmapped(getConfirmedUnmapped() - 1);
			break;
		case StatusInfo.UNMAPPED_DEPLOYED:
			setDeployedUnmapped(getDeployedUnmapped() - 1);
			break;
		default:
			return;
		}
	}

	public void appendToMap(Integer integer) {

		switch (integer) {
		case StatusInfo.MAPPED_TRAINING:
			setTrainingMapped(getTrainingMapped() + 1);
			break;
		case StatusInfo.MAPPED_RESERVED:
			setReservedMapped(getReservedMapped() + 1);
			break;
		case StatusInfo.MAPPED_SELECTED:
			setSelectedMapped(getSelectedMapped() + 1);
			break;
		case StatusInfo.MAPPED_CONFIRMED:
			setConfirmedMapped(getConfirmedMapped() + 1);
			break;
		case StatusInfo.MAPPED_DEPLOYED:
			setDeployedMapped(getDeployedMapped() + 1);
			break;
		case StatusInfo.UNMAPPED_TRAINING:
			setTrainingUnmapped(getTrainingUnmapped() + 1);
			break;
		case StatusInfo.UNMAPPED_OPEN:
			setOpenUnmapped(getOpenUnmapped() + 1);
			break;
		case StatusInfo.UNMAPPED_SELECTED:
			setSelectedUnmapped(getSelectedUnmapped() + 1);
			break;
		case StatusInfo.UNMAPPED_CONFIRMED:
			setConfirmedUnmapped(getConfirmedUnmapped() + 1);
			break;
		case StatusInfo.UNMAPPED_DEPLOYED:
			setDeployedUnmapped(getDeployedUnmapped() + 1);
			break;
		default:
			return;
		}
	}
}
