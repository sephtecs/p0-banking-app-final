package com.project.zero.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.zero.dao.LoginDetails;
import com.project.zero.model.Customer;
import com.project.zero.model.Login;
import com.project.zero.util.DBConnection;

public class LoginDAOImpl implements LoginDetails {
	
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

	public boolean validate(String username, String password) {
		Login login = new Login();
		Customer customer = new Customer();
		List<Customer> customers = new ArrayList<Customer>();
		List<Login> logins = new ArrayList<Login>();
		
		System.out.println("Searching for user with username : " + username);
		
		PreparedStatement statement;
		connection = DBConnection.getConnection();

		try {
			statement = connection.prepareStatement("select * from login where username = ? and password = ? ");
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				login = new Login();
				login.setUserId(result.getInt(1));
				login.setUsername(result.getString(2));
				login.setPassword(result.getString(3));
				logins.add(login);
			}
			
			statement = connection.prepareStatement("select * from customers where username = ? and password = ?");
			statement.setString(1, username);
			statement.setString(2, password);
			result = statement.executeQuery();
			
			while (result.next()) {
				customer = new Customer();
				customer.setUserId(result.getInt(1));
				customer.setFirstname(result.getString(2));
				customer.setUsername(result.getString(3));
				customer.setPassword(result.getString(4));
				customer.setBalance(result.getLong(5));
				customer.setAccounttype(result.getString(6));
//				customer.setStatus(res.getString(7));
				customers.add(customer);
			}
			
			//System.out.println(customer.getStatus());
			result.close();
			statement.close();
			connection.close();
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(logins.size() == 0) {
			return false;
		}
		
		return true;
	}
	
	public boolean doesLoginExist(String username) {
		PreparedStatement statement;
		boolean loginExists = false;
		connection = DBConnection.getConnection();

		try {
			statement = connection.prepareStatement("select * from login where username = ? ");
			statement.setString(1, username);
			ResultSet res = statement.executeQuery();
			loginExists = res.next();
			//
			res.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginExists;
	}


	@Override
	public boolean register(String username, String password, String accounttype, double balance, String firstname) {
		PreparedStatement stat = null;
		connection = DBConnection.getConnection();
		int rows = 0;
		
		try {
			if(accounttype.equalsIgnoreCase("C")) {
			stat = connection.prepareStatement("insert into customers values(default,?,?,?,?)");
			stat.setString(1, firstname);
			stat.setString(2, username);
			stat.setString(3, password);
			stat.setDouble(4, balance);
			rows = stat.executeUpdate();
			
			System.out.println(rows + " customer added to database");
			
			stat = null;
			rows = 0;
			stat = connection.prepareStatement("insert into login values(default,?,?)");
			stat.setString(1, username);
			stat.setString(2, password);
			rows = stat.executeUpdate();
			System.out.println(rows + " user registered successfully");
			} else {
				stat = connection.prepareStatement("insert into employees values(default,?,?,?)");
				stat.setString(1, firstname);
				stat.setString(2, username);
				stat.setString(3, password);
				rows = stat.executeUpdate();
				System.out.println(rows + " employee added to database");
				
				stat = null;
				rows = 0;
				stat = connection.prepareStatement("insert into login values(default,?,?)");
				stat.setString(1, username);
				stat.setString(2, password);
				rows = stat.executeUpdate();
				System.out.println(rows + " user registered successfully");
			}
			
			stat.close();
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
