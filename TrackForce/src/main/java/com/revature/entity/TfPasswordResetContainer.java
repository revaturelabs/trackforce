package com.revature.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * @author tbnil from batch 1901
 * As of writing this, this entity is not used. It is intended to be used to send back password 
 * reset information when an admin resets another user's password to a random string.
 *
 */
@XmlRootElement
public class TfPasswordResetContainer implements Serializable {
	private static final long serialVersionUID = -6436922296724471174L;
	@XmlElement
	private String message;
	@XmlElement
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TfPasswordResetContainer [message=" + message + ", password=" + password + "]";
	}

}
