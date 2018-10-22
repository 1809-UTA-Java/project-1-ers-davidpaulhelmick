package com.revature.ers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REIMBURSEMENTTYPE")
public class ReimbursementType {
	@Id
	@Column(name = "RT_ID")
	private int rID;

	@Column(name = "RT_TYPE")
	private String rType;

	public ReimbursementType(int rID, String rType) {
		super();
		this.rID = rID;
		this.rType = rType;
	}

	public ReimbursementType() {
		super();
	}

	public int getrID() {
		return rID;
	}

	public void setrID(int rID) {
		this.rID = rID;
	}

	public String getrType() {
		return rType;
	}

	public void setrType(String rType) {
		this.rType = rType;
	}

	@Override
	public String toString() {
		return "ReimbursementType [rID=" + rID + ", rType=" + rType + "]";
	}

}
