package com.training.userservice.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Users{

	private int id;
	private String name;
	private String addr;
	@JsonIgnore
	private String password;

	public Users(int id, String name, String addr, String password) {
		super();
		this.id = id;
		this.name = name;
		this.addr = addr;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	
}
