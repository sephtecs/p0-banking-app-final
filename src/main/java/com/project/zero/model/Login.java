package com.project.zero.model;

import java.util.Objects;

public class Login {
	
	/*
	 * Represents a Login object or JAVA POJO carrying data.
	 */
	
	private int userID;
	private String userName;
	private String pw;
	
	public Login() {
		// TODO Auto-generated constructor stub
	}

	

	public Login(int userId, String username, String password) {
		this.userID = userId;
		this.userName = username;
		this.pw = password;
	}



	public int getUserId() {
		return userID;
	}



	public void setUserId(int userId) {
		this.userID = userId;
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



	@Override
	public int hashCode() {
		return Objects.hash(pw, userID, userName);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(pw, other.pw) && userID == other.userID
				&& Objects.equals(userName, other.userName);
	}



	@Override
	public String toString() {
		return "Login [userId=" + userID + ", username=" + userName + ", password=" + pw + "]";
	}


}
