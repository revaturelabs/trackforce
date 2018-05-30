package com.revature.model;


public class CurriculumJSON implements Comparable<CurriculumJSON> {

	private String name;
	private int id;
	private int count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(CurriculumJSON o) {
		return new Integer(this.id).compareTo(new Integer(o.id));
	}
}
