package com.revature.criteria;

public class GraphedCriteriaResult
{
	private long count;
	private int id;
	private String name;

	public GraphedCriteriaResult() { }

	public GraphedCriteriaResult(long count, int id, String name) {
		this.count = count;
		this.id = id;
		this.name = name;
	}

	public long getCount() { return count; }

	public void setCount(long count) { this.count = count; }

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }
}