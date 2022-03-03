package com.project.zero.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.project.zero.dao.CustomerDetails;
import com.project.zero.daoImpl.CustomerDAOImpl;

class CustomerDetailsTest {

	CustomerDetails customerDetails;
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		customerDetails = new CustomerDAOImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Testing to see if customer exists")
	void testDoesCustomerExist() {
		String username = "bingbong";
		boolean actual = customerDetails.isUserExists(username);
		assertTrue(actual);
	}
	
	@Test
	@DisplayName("Testing to see if customer exists")
	void testDoesCustomerExist2() {
		String username = "balabala";
		boolean actual = customerDetails.isUserExists(username);
		assertTrue(actual);
	}
	
	@Test
	@DisplayName("Testing to see if customer exists")
	void testDoesCustomerExist3() {
		String username = "jay";
		boolean actual = customerDetails.isUserExists(username);
		assertTrue(actual);
	}

}
