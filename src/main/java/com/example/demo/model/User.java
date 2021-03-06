package com.example.demo.model;

import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min=3, message="requied")
	private String username;
	@NotNull
	private String Password;	
	@NotNull
	private String Firstname;
	@NotNull
	private String Lastname;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public User() {
		
	}
	
	public User(String fisrtname, String lastname, String username) {
		Firstname = fisrtname;
		Lastname = lastname;
		this.username = username;
	}
	
	public String getFisrtname() {
		return Firstname;
	}

	public void setFisrtname(String fisrtname) {
		Firstname = fisrtname;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", Password=" + Password + ", Firstname=" + Firstname
				+ ", Lastname=" + Lastname + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
