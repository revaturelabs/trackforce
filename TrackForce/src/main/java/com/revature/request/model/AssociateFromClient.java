package com.revature.request.model;

import java.io.Serializable;

public class AssociateFromClient implements Serializable {
	private static final long serialVersionUID = -8738760070575633823L;
	private int id;
	private int mkStatus;
	private int clientId;
	private long startDateUnixTime;
	
	@Override
	public String toString() {
		return "AssociateFromClient [id=" + id + ", mkStatus=" + mkStatus + ", clientId=" + clientId
				+ ", startDateUnixTime=" + startDateUnixTime + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMkStatus() {
		return mkStatus;
	}
	public void setMkStatus(int mkStatus) {
		this.mkStatus = mkStatus;
	}
	public long getStartDateUnixTime() {
		return startDateUnixTime;
	}
	public void setStartDateUnixTime(long startDateUnixTime) {
		this.startDateUnixTime = startDateUnixTime;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	public AssociateFromClient() {}
	
	public AssociateFromClient(int id, int mkStatus,int clientId, long startDateUnixTime) {
		super();
		this.id = id;
		this.mkStatus = mkStatus;
		this.clientId = clientId;
		this.startDateUnixTime = startDateUnixTime;
	}
}
