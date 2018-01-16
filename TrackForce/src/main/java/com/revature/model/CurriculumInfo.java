package com.revature.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CurriculumInfo  implements Serializable, Comparable<CurriculumInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3094266737602323184L;
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
	public int compareTo(CurriculumInfo o) {
		return this.id.subtract(o.id).intValueExact();
	}

}
