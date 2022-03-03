package com.project.zero.model;

import java.util.Iterator;
import java.util.List;

public class Print {
	
	public void printTransactionRecords(List<Transactions> transaction) {
		Iterator<Transactions> iterator = transaction.iterator();
		while(iterator.hasNext()) {
			Transactions temp = iterator.next();
			System.out.println(temp);
		}
		
	}
	
	public void printUserDetails(List<Customer> customers) {
		Iterator<Customer> iterator = customers.iterator();
		while(iterator.hasNext()) {
			Customer temp = iterator.next();
			System.out.println(temp);
		}
		
	}
	
	public void printApplicationDetails(List<Customer> customers) {
		Iterator<Customer> iterator = customers.iterator();
		while(iterator.hasNext()) {
			Customer temp = iterator.next();
			System.out.println(temp);
		}
	}

}
