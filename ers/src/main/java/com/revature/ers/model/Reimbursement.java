package com.revature.ers.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "REIMBURSEMENTS")
public class Reimbursement {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REIMBURSEMENTS_SEQ")
    @SequenceGenerator(name = "REIMBURSEMENTS_SEQ", sequenceName = "SEQUENCE_REIMBURSEMENTS")
	@Column(name = "R_ID")
	private int rID;

	@Column(name = "R_AMOUNT")
	private double amount;

	@Column(name = "R_DESCRIPTION")
	private String description;
	
	@Column(name = "r_receipt")
	private byte receipt[];

	@Column(name = "R_SUBMITTED")
	private Timestamp submitted;

	@Column(name = "R_RESOLVED")
	private Timestamp resolved;

	@ManyToOne
	@JoinColumn(name = "u_id_author")
	private Users author;

	@ManyToOne
	@JoinColumn(name = "u_id_resolver")
	private Users resolver;

	@ManyToOne
	@JoinColumn(name = "RT_ID")
	private ReimbursementType type;

	@ManyToOne
	@JoinColumn(name = "RS_ID")
	private ReimbursementStatus status;

	public Reimbursement(int rID, double amount, String description, Timestamp submitted, Timestamp resolved,
			Users author, Users resolver, ReimbursementType type, ReimbursementStatus status) {
		super();
		this.rID = rID;
		this.amount = amount;
		this.description = description;
		this.submitted = submitted;
		this.resolved = resolved;
		this.author = author;
		this.resolver = resolver;
		this.type = type;
		this.status = status;
	}

	public Reimbursement() {
		super();
	}

	public Reimbursement(double amount, String description, Timestamp submitted, Timestamp resolved,
			Users author, Users resolver, ReimbursementType type, ReimbursementStatus status) {
		super();
		this.amount = amount;
		this.description = description;
		this.submitted = submitted;
		this.resolved = resolved;
		this.author = author;
		this.resolver = resolver;
		this.type = type;
		this.status = status;
	}

	public int getrID() {
		return rID;
	}

	public void setrID(int rID) {
		this.rID = rID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public Users getAuthor() {
		return author;
	}

	public void setAuthor(Users author) {
		this.author = author;
	}

	public Users getResolver() {
		return resolver;
	}

	public void setResolver(Users resolver) {
		this.resolver = resolver;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reimbursement [rID=" + rID + ", amount=" + amount + ", description=" + description + ", submitted="
				+ submitted + ", resolved=" + resolved + ", author=" + author + ", resolver=" + resolver + ", type="
				+ type + ", status=" + status + "]";
	}

}

