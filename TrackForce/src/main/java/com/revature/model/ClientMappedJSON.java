package com.revature.model;

public class ClientMappedJSON implements Comparable<ClientMappedJSON> {
	private int id;
	private String name;
	private int count;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	@Override
	public int compareTo(ClientMappedJSON arg0) {
		// TODO Auto-generated method stub
		return this.id-arg0.id;
	}
	
	
}
