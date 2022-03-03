package com.project.zero.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.zero.dao.EmployeeDetails;
import com.project.zero.model.Customer;
import com.project.zero.model.Employee;
import com.project.zero.model.Transactions;
import com.project.zero.util.DBConnection;

public class EmployeeDAOImpl implements EmployeeDetails {
	
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
	
//---------------------------------- Implementation of CRUD Functionality in Employee Details --------------------------------------------
	//DELETE METHOD:
	public boolean deleteUser(String username) {
		connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		System.out.println("Deleting account with username : " + username);
		int rows = 0;
		
		try {
			statement = connection.prepareStatement("delete from customers where username = ?");
			statement.setString(1, username);
			rows = statement.executeUpdate();
			System.out.println(rows + " deleted successfully");
			//------------------------------- Delete From Login Table --------------------
			statement= connection.prepareStatement("delete from login where username = ?");
			statement.setString(1, username);
			//UPDATE DB
			rows = statement.executeUpdate();
			//CLOSE CONNECTION
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
	
	//READ:
	public List<Customer> searchByUsername(String username) {
		PreparedStatement statement = null;
		connection = DBConnection.getConnection();
		List<Customer> customers = new ArrayList<Customer>();
		
		System.out.println("Searching for " + username + "in the database");
		
		try {
			statement = connection.prepareStatement("select * from customers where username like ? ");
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			//
			while (result.next()) {
				Customer customer = new Customer();
				customer.setUserId(1);
				customer.setFirstname(result.getString(2));
				customer.setUsername(result.getString(3));
				customer.setPassword(result.getString(4));
				customer.setBalance(result.getLong(5));
				customer.setAccounttype(result.getString(6));
				customers.add(customer);
			}
			result.close();
			statement.close();
			connection.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	//ANOTHER READ METHOD:
	public boolean doesUserExist(int userId) {
		connection = DBConnection.getConnection();
		boolean userExists = false;
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement("select * from users where user_Id = ? ");
			statement.setInt(1, userId);

			ResultSet result = statement.executeQuery();
			userExists = result.next();
			result.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userExists;
	}
	
	//ANOTHER READ METHOD THAT PRINTS ALL CUSTOMER ACCOUNTS:
	public void printAllUsers() {
		connection = DBConnection.getConnection();
		System.out.println("Printing all accounts ");
		
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from customers");
			ResultSetMetaData rsmd = result.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				System.out.print(rsmd.getColumnName(i) + "    ");
			}
			System.out.println();

			while (result.next()) {
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(result.getString(i) + "    ");
				}
				System.out.println();
			}
			result.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//READS ALL LOGGED TRANSACTIONS
	public List<Transactions> getRecordedTransactions() {
		connection = DBConnection.getConnection();
		System.out.println("Getting log of all transactions");
		List<Transactions> transactions = new ArrayList<Transactions>();
		
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from transactions");
			while(result.next()) {
				Transactions transaction = new Transactions();
				transaction.setReceiver(result.getString(1));
				transaction.setSender(result.getString(2));
				transaction.setAmount(result.getLong(3));
				transaction.setTransactiontime(result.getTimestamp(4));
				transactions.add(transaction);
			}
			result.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}
	
	//EMPLOYEE METHODS
	public Employee getEmployeeValues(String username, String password) {
		Employee employee = new Employee();
		connection = DBConnection.getConnection();
		PreparedStatement statement = null;
		List<Employee> employees = new ArrayList<Employee>();

		try {
			statement = connection.prepareStatement("select * from employees where username = ? and password = ? ");
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet res = statement.executeQuery();

			while (res.next()) {
				employee = new Employee();
				employee.setUserId(res.getInt(1));
				employee.setFirstname(res.getString(2));
				employee.setUsername(res.getString(3));
				employee.setPassword(res.getString(4));
				employee.setAccounttype(res.getString(5));
				employees.add(employee);
				
				System.out.println("user id from employee object : " + employee.getUserId());
			}
			
			res.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
}
