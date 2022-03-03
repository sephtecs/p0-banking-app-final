package com.project.zero.exception;

public class AccountAlreadyExists extends RuntimeException {
	public AccountAlreadyExists() {
	}
	
	public AccountAlreadyExists(String message) {
		super(message);
	}
}
