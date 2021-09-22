package com.payment.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="employee")
public class Employee {
	
	@Id
	String user_name;
	String password;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Employee [user_name=" + user_name + ", password=" + password + "]";
	}
	
	
}
