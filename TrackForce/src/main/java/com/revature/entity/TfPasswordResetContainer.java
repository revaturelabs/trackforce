package com.revature.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



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
