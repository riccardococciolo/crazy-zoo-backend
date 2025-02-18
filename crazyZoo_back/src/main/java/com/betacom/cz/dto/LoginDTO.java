package com.betacom.cz.dto;

public class LoginDTO {
	
    private String token;
    private String username;
    private String role;
    
    //Getters&Setters
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}     

}
