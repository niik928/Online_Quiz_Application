package com.quizz.entities;

public class User {
private int id;
private String username;
private String email;
private String password;
private String role;

public enum role{
	USER,ADMIN;
}

public User() {
	super();
}
public User(int id, String username, String email, String password, String role) {
	super();
	this.id = id;
	this.username = username;
	this.email = email;
	this.password = password;
	this.role = role;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", role="
			+ role + "]";
}


}
