package com.revature.model;

import java.io.Serializable;

public class TechInfo  implements Serializable, Comparable<TechInfo> {

		private static final long serialVersionUID = 6881758298570753347L;
		
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
	public int compareTo(TechInfo o) {
		return this.id-o.id;
	}

}
