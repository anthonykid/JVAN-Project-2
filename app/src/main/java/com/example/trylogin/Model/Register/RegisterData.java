package com.example.trylogin.Model.Register;

import com.google.gson.annotations.SerializedName;

public class RegisterData {

	@SerializedName("role")
	private String role;

	@SerializedName("name")
	private String name;

	@SerializedName("username")
	private String username;

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}