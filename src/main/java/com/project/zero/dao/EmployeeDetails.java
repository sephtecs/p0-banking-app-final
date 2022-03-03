package com.project.zero.dao;

import java.util.List;

import com.project.zero.model.Customer;
import com.project.zero.model.Employee;
import com.project.zero.model.Transactions;

public interface EmployeeDetails {
	
	/*
	 * Interfaces of JDBC API include: Driver, Connection, Statement, PreparedStatement, Callable Statement, ResultSet, ResultSetMetaData, DatabaseMetaData
	 */
	
	//SOME CRUD FUNCTIONALITY:
	
	//READ:
	public List<Customer> searchByUsername(String username);
	public void printAllUsers();
	public List<Transactions> getRecordedTransactions();
	public boolean doesUserExist(int userId);
	public Employee getEmployeeValues(String username, String password);
	
	//DELETE:
	public boolean deleteUser(String username);
	
}
