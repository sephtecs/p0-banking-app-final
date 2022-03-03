package com.project.zero.dao;

public interface LoginDetails {
	
	/*
	 * Interfaces of JDBC API include: Driver, Connection, Statement, PreparedStatement, Callable Statement, ResultSet, ResultSetMetaData, DatabaseMetaData
	 */
	
	//CREATE:
	public boolean register(String username, String password, String accounttype, double balance, String firstname);
	public boolean validate(String username, String password);
	//READ:
	public boolean doesLoginExist(String username);
	

}
