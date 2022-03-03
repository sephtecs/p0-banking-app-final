package com.project.zero.model;

import java.util.Objects;

public class Customer {
	
	/*
	 * Represents a Customer object or JAVA POJO carrying data.
	 */
	
	private int userID;
	private String firstName;
	private String userName;
	private String pw;
	private long balance;
	private String accountType;
	private String status;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	

	public Customer(int userId, String firstname, String username, String password, long balance, String accounttype,
			String status) {
		this.userID = userId;
		this.firstName = firstname;
		this.userName = username;
		this.pw = password;
		this.balance = balance;
		this.accountType = accounttype;
		this.status = status;
	}


	public int getUserId() {
		return userID;
	}

	public void setUserId(int userId) {
		this.userID = userId;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return pw;
	}

	public void setPassword(String password) {
		this.pw = password;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long l) {
		this.balance = l;
	}
	
	

	public String getAccounttype() {
		return accountType;
	}

	public void setAccounttype(String accounttype) {
		this.accountType = accounttype;
	}
	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		return Objects.hash(accountType, balance, firstName, pw, status, userID, userName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(accountType, other.accountType) && balance == other.balance
				&& Objects.equals(firstName, other.firstName) && Objects.equals(pw, other.pw)
				&& Objects.equals(status, other.status) && userID == other.userID
				&& Objects.equals(userName, other.userName);
	}


	@Override
	public String toString() {
		return "Customer [userId=" + userID + ", firstname=" + firstName + ", username=" + userName + ", password="
				+ pw + ", balance=" + balance + ", accounttype=" + accountType + ", status=" + status + "]";
	}

}
