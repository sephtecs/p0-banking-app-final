package com.project.zero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.project.zero.dao.CustomerDetails;
import com.project.zero.dao.EmployeeDetails;
import com.project.zero.dao.LoginDetails;
import com.project.zero.daoImpl.CustomerDAOImpl;
import com.project.zero.daoImpl.EmployeeDAOImpl;
import com.project.zero.daoImpl.LoginDAOImpl;
import com.project.zero.model.Customer;
import com.project.zero.model.Employee;
import com.project.zero.model.Login;
import com.project.zero.model.Transactions;

public class ProductApp {
	//Essential Variables:
	int choice = 0;
	Scanner scanner = new Scanner(System.in);
	
	//For Customer/ Employee
	int userId = 0;
	String firstName = null;
	String userName = null;
	String pw = null;
	String accountType = null;
	double balance = 0;
	long amount = 0;
	boolean isNotValid = true;
	boolean isValidAdd = true;
	
	//Iterating over and printing customer transactions
	List<Customer> customers = new ArrayList<Customer>();
	List<Transactions> transactions = new ArrayList<Transactions>();
	
	//Data Access Objects for Customer, Employee, and Login for DB connection
	CustomerDetails customerDetails = new CustomerDAOImpl();
	EmployeeDetails employeeDetails = new EmployeeDAOImpl();
	LoginDetails loginDetails = new LoginDAOImpl();
	
	//Instantiating Objects
	Customer customer = new Customer();
	Employee employee = new Employee();
	Login login = new Login();
	

// ------------------------------------------------------- START THE ENGINES ----------------------------------------------------------------
	public void startProductApp() {
		
		while (true) {
			System.out.println("======================================");
			System.out.println("          THE MAVEN BANK APP          ");
			System.out.println("======================================");
			System.out.println("1. Login ");
			System.out.println("2. Create an Account ");
			System.out.println("9. Exit");
			System.out.println("Please make a selection: ");
			choice = scanner.nextInt();
			System.out.println("\n");
			switch (choice) {
			case 1:
// ---------------------------------------------- USER CAN LOG IN AS A CUSTOMER OR EMPLOYEE ----------------------------------------------------
				do {
					System.out.println("Login as:");
					System.out.println("For customer enter 'c':");
					System.out.println("For employee enter 'e':");
					accountType = scanner.next();
					// char values in DB to trigger account type
					if (accountType.equals("c") || accountType.equals("e")) {
						isNotValid = false;
					}
					
				} while (isNotValid);

				System.out.println("Please enter your username: ");
				userName = scanner.next();

				System.out.println("Please enter your password: ");
				pw = scanner.next();
				
				boolean validLogin = loginDetails.validate(userName, pw);
				customer = customerDetails.getValues(userName, pw);
				
			if (!validLogin) {
				if(customer.getStatus() != null && customer.getStatus().equals("N"))
					System.out.println("Account has not yet been approved. Administrator will update in a timely manner.");
					else
					System.out.println("Incorrect username or password. Try again");
					
				accountType = null;
				continue;
				}

				
				employee = employeeDetails.getEmployeeValues(userName, pw);
				System.out.println("Hello, " + userName);

// ---------------------------------------------------- CUSTOMER PORTAL ------------------------------------------------------------
				if (accountType.equals("c") && customer.getFirstname() != null) {

					while (true) {
						System.out.println("=========================================");
						System.out.println("Welcome, " + userName + "!");
						System.out.println("=========================================");
						System.out.println("1. Make a Withdrawal ");
						System.out.println("2. Make a Deposit");
						System.out.println("3. View Account Balance");
						System.out.println("9. Exit");
						System.out.println("=========================================");
						System.out.println("Please make a selection: ");
						choice = scanner.nextInt();

						switch (choice) {
// ----------------------------------------------------- WITHDRAW METHOD --------------------------------------------------------------
						case 1:
							isNotValid = true;
							while (isNotValid) {
								System.out.println("Please enter the amount to be withdrawn: ");
								amount = scanner.nextInt();
								if (amount <= customer.getBalance() && amount >= 0) {
									isNotValid = false;
								} else if(amount < 0)
									System.out.println("Amount cannot be overdrafted.");
								else {
									System.out.println("Cannot withdraw more than account balance");									
								}
							}
							// set a true or false conditional here
							boolean isValidWithdraw = customerDetails.withdraw(userName, amount);
							// if it's valid, include it as a customer transaction
							isValidAdd = customerDetails.recordTransaction(customer, -(amount));
							if (!isValidWithdraw) {
								System.out.println("Withdraw was unsuccessful. Try again");
								continue;
							}

							System.out.println("$" + amount + " was withdrawn from your account.");
							customer = customerDetails.getValues(userName, pw);
							break;
// ----------------------------------------------------- DEPOSIT METHOD --------------------------------------------------------------							
						case 2:
							System.out.println("Deposit menu:");
							// setup another boolean here
							isNotValid = true;
							while (isNotValid) {
								System.out.println("How much would you like to deposit in your account?");
								amount = scanner.nextInt();
								// if-check for system validations. Results in negative balance.
								if (amount < 0)
									System.out.println("Amount to deposit cannot not be negative.");
								else 
									isNotValid = false;
							}
							
							boolean isDepositValid = customerDetails.deposit(userName, amount);
							isValidAdd = customerDetails.recordTransaction(customer, amount);

							if (!isDepositValid) {
								System.out.println("Deposit was unsuccessful. Please try again.");
								continue;
							}

							System.out.println("You have successfully depsosited " + "$" + amount + " in your account.");
							customer = customerDetails.getValues(userName, pw);
							break;
							
// ----------------------------------------------------- VIEW ACCOUNT BALANCE METHOD --------------------------------------------------------------	

						case 3:
							System.out.println("Here's your current balance:");
							System.out.println("Current customer balance: " + "$" + customer.getBalance());
							break;
// -------------------------------------------------------- EXIT & GOOD BYE -----------------------------------------------------------------------	

						case 9:
							System.out.println("Thanks for banking with Maven! See you next time.");
							System.exit(9);
							break;
						}
					} 
					
// ------------------------------------------------------- EMPLOYEE PORTAL ------------------------------------------------------------------------
					
				} else if (accountType.equals("e")) {

					while (true) {
						System.out.println("=========================================");
						System.out.println("1. Search Customer by Name ");
						System.out.println("2. Transactions Log ");
						System.out.println("3. Close Customer Account ");
						System.out.println("9. Exit ");
						System.out.println("=========================================");
						System.out.println("Please make a selection: ");
						choice = scanner.nextInt();

						
						switch (choice) {
// ----------------------------------- EMPLOYEE CAN SEARCH AN ACCOUNT BY USERNAME ---------------------------------------------------
						case 1:
							System.out.println("Please enter username to search : ");
							userName = scanner.next();
							
							customers = employeeDetails.searchByUsername(userName);
							// If no username in DB
							if (customers.size()==0) {
								System.out.println("No username matching your criteria");
								continue;
							}
							// Otherwise, print customer details
							printUserDetails(customers);

							break;
// ---------------------------------- EMPLOYEE CAN VIEW TRANSACTION LOG OF ALL CUSTOMERS ---------------------------------------------					
						case 2:
							
							System.out.println("View a record of all Maven Banking transactions");
							transactions = employeeDetails.getRecordedTransactions();
							// If no records exist, let user know
							if (transactions.size()==0) {
								System.out.println("No transactions");
								continue;
							}
							// Otherwise, print 
							printTransactionRecords(transactions);
							break;
// ---------------------------------- EMPLOYEE CAN DELETE/REJECT CUSTOMER ACCOUNT BY USERNAME ------------------------------------------
						case 3:
							//Delete user section
							System.out.println("Remove a customer from database:");
							employeeDetails.printAllUsers();
							System.out.println("Please enter username to delete :");
							userName = scanner.next();
							
							if(loginDetails.doesLoginExist(userName))
							{
								employeeDetails.deleteUser(userName);
								System.out.println("User with username : "+userName+ " deleted successfully");
							}
							else
							{
								System.out.println("User with username : "+userName+ " does not exists, hence cannot be deleted");

							}
// ------------------------------------------- EXIT & GOODBYE EMPLOYEE PORTAL -------------------------------------------------------------
						case 9:
							System.out.println("Thank you for banking with Maven. Please come again!");
							System.exit(9);
							break;
						}
					}
				} else {
					System.out.println("Invalid selection. Please try again later.");
					System.exit(9);
				}
// -------------------------------------------------------- ACCOUNT CREATION METHOD -----------------------------------------------------------------------					
			case 2:

				do {
					System.out.println("One step closer to banking with Maven! ");
					System.out.println("What type of account would you like to create?");
					System.out.println("Please select 'c' for customer or 'e' for employee");
					accountType = scanner.next();
					accountType = accountType.toUpperCase();

					if (accountType.equals("C") || accountType.equals("E")) {
						isNotValid = false;
					}
				} while (isNotValid);


				System.out.println("Please enter a username: ");
				userName = scanner.next();

				if (loginDetails.doesLoginExist(userName)) {
					System.out.println("Username already exists. Please try again.");
					continue;
				}

				System.out.println("Please enter a password: ");
				pw = scanner.next();

				System.out.println("Please enter first name: ");
				firstName = scanner.next();

				if (accountType.equalsIgnoreCase("C")) {
					System.out.println("Please enter starting balance: ");
					balance = scanner.nextLong();
				}

				login = new Login(userId, userName, pw);
				

				boolean isValidRegister = loginDetails.register(userName, pw, accountType, balance, firstName);

				if (!isValidRegister) {
					System.out.println("Username already exists, please try another.");
					continue;
				}
				if(accountType.equals("C")) {
					System.out.println("Congratulations " + userName + ", your account has been approved.");
				} else
					System.out.println("Congratulations " + userName + ", your account has been approved.");

				break;
// ------------------------------------------------------- EXIT & GOODBYE -------------------------------------------------------------
			case 9:
				System.out.println("Thanks for banking with Maven! See you next time.");
				System.exit(9);

			}
		}
	}

// ------------------------------------------------------- PRINT METHODS -------------------------------------------------------------
	public void printUserDetails(List<Customer> customers) {
		Iterator<Customer> iter = customers.iterator();
		while(iter.hasNext()) {
			Customer cust = iter.next();
			System.out.println(cust);
		}
	}
	public void printTransactionRecords(List<Transactions> transaction) {
		Iterator<Transactions> iter = transaction.iterator();
		while(iter.hasNext()) {
			Transactions trans = iter.next();
			System.out.println(trans);
		}
	}
}
