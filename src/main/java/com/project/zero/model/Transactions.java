package com.project.zero.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Transactions {
	
	/*
	 * Represents a Transactions object or JAVA POJO carrying data.
	 */
	
	private String sender;
	private String receiver;
	private long amount;
	private Timestamp transactiontime;
	
	public Transactions() {
		
	}

	public Transactions(String sender, String receiver, long amount, Timestamp transactiontime) {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.transactiontime = transactiontime;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Timestamp getTransactiontime() {
		return transactiontime;
	}

	public void setTransactiontime(Timestamp timestamp) {
		this.transactiontime = timestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, receiver, sender, transactiontime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transactions other = (Transactions) obj;
		return amount == other.amount && Objects.equals(receiver, other.receiver)
				&& Objects.equals(sender, other.sender) && Objects.equals(transactiontime, other.transactiontime);
	}

	@Override
	public String toString() {
		return "Transactions [sender=" + sender + ", receiver=" + receiver + ", amount=" + amount + ", transactiontime="
				+ transactiontime + "]";
	}

}
