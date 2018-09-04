package com.revature.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TfUserAndCreatorRoleContainer implements Serializable {
	private static final long serialVersionUID = -6436922296724471174L;
	@XmlElement
	private TfUser user;
	@XmlElement
	private int creatorRole;
	
	public TfUserAndCreatorRoleContainer(TfUser user, int creatorRole) {
		super();
		this.user = user;
		this.creatorRole = creatorRole;
	}
	public TfUserAndCreatorRoleContainer() {
		super();
	}
	
	public TfUser getUser() {
		return user;
	}
	public void setUser(TfUser user) {
		this.user = user;
	}
	
	public int getCreatorRole() {
		return creatorRole;
	}
	public void setCreatorRole(int creatorRole) {
		this.creatorRole = creatorRole;
	}
	
	@Override
	public String toString() {
		return "TfUserAndCreatorRoleContainer [user=" + user + ", creatorRole=" + creatorRole + "]";
	}
}
