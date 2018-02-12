package com.revature.model;

import java.io.Serializable;

public class MarketingStatusInfo  implements Serializable, Comparable<MarketingStatusInfo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 52811943506766514L;
	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer integer) {
		this.id = integer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(MarketingStatusInfo o) {
		return this.id-o.id;
	}
	
	
}
