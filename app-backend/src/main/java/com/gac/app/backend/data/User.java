package com.gac.app.backend.data;

import java.sql.Timestamp;

public class User {
	
	private int ID;
	private String user;
	private String email;
	private String name;
	private String surname;
	private String password;
	private Timestamp lastlogin;

	public User() {		
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Timestamp getLastlogin() {
		return lastlogin;
	}
}
