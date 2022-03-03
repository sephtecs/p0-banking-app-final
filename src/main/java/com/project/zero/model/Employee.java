package com.project.zero.model;

import java.util.Objects;

public class Employee {
	
	/*
	 * Represents an Employee object or JAVA POJO carrying data.
	 */
	
	private int userID;
	private String firstName;
	private String userName;
	private String pw;
	private String accountType;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int userId, String firstname, String username, String password, String accounttype) {
		this.userID = userId;
		this.firstName = firstname;
		this.userName = username;
		this.pw = password;
		this.accountType = accounttype;
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

	
	

	public String getAccounttype() {
		return accountType;
	}

	public void setAccounttype(String accounttype) {
		this.accountType = accounttype;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountType, firstName, pw, userID, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(accountType, other.accountType) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(pw, other.pw) && userID == other.userID
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "Employee [userId=" + userID + ", firstname=" + firstName + ", username=" + userName + ", password="
				+ pw + ", accounttype=" + accountType + "]";
	}

}
