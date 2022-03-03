package com.project.zero.dao;

import com.project.zero.model.Customer;

public interface CustomerDetails {
	
	/*
	 * Interfaces of JDBC API include: Driver, Connection, Statement, PreparedStatement, Callable Statement, ResultSet, ResultSetMetaData, DatabaseMetaData
	 */
	
	//SOME CRUD FUNCTIONALITY:
	//CREATE
	public boolean recordTransaction(Customer customer, double amount);
	public boolean recordTransaction(Customer customer, String receiver, double amount);
	
	//READ
	public Customer getValues(String username, String password);
	public boolean isUserExists(String username);
	
	//UPDATE
	public boolean withdraw(String username, long amount);
	public boolean deposit(String username, long amount);

	

}
