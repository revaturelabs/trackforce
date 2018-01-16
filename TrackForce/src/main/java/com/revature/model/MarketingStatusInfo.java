package com.revature.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class MarketingStatusInfo  implements Serializable, Comparable<MarketingStatusInfo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 52811943506766514L;
	private BigDecimal id;
	private String name;
	
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(MarketingStatusInfo o) {
		return this.id.subtract(o.id).intValueExact();
	}
	
	
}
