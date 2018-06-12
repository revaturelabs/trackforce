package com.revature.model;

import java.io.Serializable;

/*
 * This class maps to the selected-status const in constants folder
 *  id  marketing status
	1	MAPPED: TRAINING
	2	MAPPED: RESERVED
	3	MAPPED: SELECTED
	4	MAPPED: CONFIRMED
	5	MAPPED: DEPLOYED
	6	UNMAPPED: TRAINING
	7	UNMAPPED: OPEN
	8	UNMAPPED: SELECTED
	9	UNMAPPED: CONFIRMED
	10	UNMAPPED: DEPLOYED
	11	DIRECTLY PLACED
	12	TERMINATED
 * 
 * Reviewed by Cameron
 * @since 6.18.06.12
 * 
 * */
public class MarketingStatusInfo  implements Serializable, Comparable<MarketingStatusInfo> {
	
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
