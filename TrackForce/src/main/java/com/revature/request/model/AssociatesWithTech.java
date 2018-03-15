package com.revature.request.model;

public class AssociatesWithTech {
	private String techName;
	private int count;
	public String getTechName() {
		return techName;
	}
	public void setTechName(String techName) {
		this.techName = techName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "AssociatesWithTech [techName=" + techName + ", count=" + count + "]";
	}
		
}
