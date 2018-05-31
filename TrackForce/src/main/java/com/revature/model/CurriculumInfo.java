package com.revature.model;

import java.io.Serializable;

public class CurriculumInfo  implements Serializable, Comparable<CurriculumInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3094266737602323184L;
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
	public int compareTo(CurriculumInfo o) {
		return this.id-o.id;
	}

}
