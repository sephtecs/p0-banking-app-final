package com.project.zero.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.zero.dao.CustomerDetails;
import com.project.zero.model.Customer;
import com.project.zero.util.DBConnection;

public class CustomerDAOImpl implements CustomerDetails{
	
	/*
	 * Java Database Connectivity is an application programming interface for the programming language Java, 
	 * which defines how a client may access a database. 
	 * It is a Java-based data access technology used for Java database connectivity.
	 */
	
	/*
	 * A JDBC driver is a software component enabling a Java application to interact with a database. 
	 * JDBC drivers are analogous to ODBC drivers, ADO.NET data providers, and OLE DB providers. 
	 * To connect with individual databases, JDBC (the Java Database Connectivity API) requires drivers for each database.
	 * 
	 * 4 Types:
	 * - JDBC-ODBC bridge
	 * - partial Java driver
	 * - pure Java driver for database middleware
	 * - pure Java driver for direct-to-database
	 */
	
	/*
	 * Statement will be used for executing static SQL statements and it can't accept input parameters. 
	 * PreparedStatement will be used for executing SQL statements many times dynamically. 
	 * It will accept input parameters.
	 */
	
	/*
	 * executeQuery : Returns one ResultSet object. executeUpdate : Returns an integer representing the number of rows affected by the SQL statement. 
	 * Use this method if you are using INSERT , DELETE , or UPDATE SQL statements.
	 */
	
	/*
	 * Interfaces of JDBC API include: Driver, Connection, Statement, PreparedStatement, Callable Statement, ResultSet, ResultSetMetaData, DatabaseMetaData
	 */
	
	Connection connection = DBConnection.getConnection();

	public Customer getValues(String username, String password) {
		Customer customer = new Customer();
		PreparedStatement statement;
		connection = DBConnection.getConnection();
		List<Customer> customers = new ArrayList<Customer>();

		try {
			statement = connection.prepareStatement("select * from customers where username = ? and password = ? ");
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet res = statement.executeQuery();
			
			while (res.next()) {
				customer = new Customer();
				customer.setUserId(res.getInt(1));
				customer.setFirstname(res.getString(2));
				customer.setUsername(res.getString(3));
				customer.setPassword(res.getString(4));
				customer.setBalance(res.getLong(5));
				customer.setAccounttype(res.getString(6));
				customers.add(customer);
			}
			
			res.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
// ---------------------------------- Implementation of CRUD Functionality in Customer Details --------------------------------------------
	@Override
	public boolean withdraw(String username, long amount) {
		connection = DBConnection.getConnection();
		CallableStatement statement;
		try {
			statement = connection.prepareCall("call withdraw(?,?)");
			statement.setString(1, username);
			statement.setLong(2, amount);
			//UPDATES DB
			statement.execute();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("Successfully withdrew.");
		return true;
	}

	@Override
	public boolean deposit(String username, long amount) {
		connection = DBConnection.getConnection();
		CallableStatement statement;
		try {
			statement = connection.prepareCall("call deposit(?,?)");
			statement.setString(1, username);
			statement.setLong(2, amount);
			//UPDATES DB
			statement.execute();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("Succesfully deposited.");
		return true;
	}
	
	public boolean isUserExists(String username) {
		PreparedStatement stat;
		connection = DBConnection.getConnection();
		boolean isloginExists = false;

		try {
			stat = connection.prepareStatement("select * from customers where username = ? ");
			stat.setString(1, username);
			ResultSet res = stat.executeQuery();
			isloginExists = res.next();
			res.close();
			stat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isloginExists;
	}

	@Override
	public boolean recordTransaction(Customer customer, double amount) {
		PreparedStatement statement = null;
		connection = DBConnection.getConnection();
		int rows = 0;

		try {
			statement = connection.prepareStatement("insert into transactions values(?,?,?,default)");
			statement.setString(1, customer.getUsername());
			statement.setString(2, "null");
			statement.setDouble(3, amount);
			rows = statement.executeUpdate();		
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (rows == 0) {
			return false;
		} else
			return true;
	}


	@Override
	public boolean recordTransaction(Customer customer, String receiver, double amount) {
		PreparedStatement statement = null;
		connection = DBConnection.getConnection();
		int rows = 0;

		try {
			statement = connection.prepareStatement("insert into transactions values(?,?,?,default)");
			statement.setString(1, customer.getUsername());
			statement.setString(2, receiver);
			statement.setDouble(3, amount);
			rows = statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (rows == 0) {
			return false;
		} else
			return true;
	}
}
