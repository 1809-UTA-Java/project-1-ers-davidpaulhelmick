package com.revature.ers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REIMBURSEMENTSTATUS")
public class ReimbursementStatus {

	@Id
	@Column(name = "RS_ID")
	private int rID;

	@Column(name = "RS_STATUS")
	private String rStatus;

	public ReimbursementStatus(int rID, String rStatus) {
		super();
		this.rID = rID;
		this.rStatus = rStatus;
	}

	public ReimbursementStatus() {
		super();
	}

	public int getrID() {
		return rID;
	}

	public void setrID(int rID) {
		this.rID = rID;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [rID=" + rID + ", rStatus=" + rStatus + "]";
	}

}
