package com.example.demo.dao;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException (Long Id){
		super("Could not find id: "+ Id);
	}
}
