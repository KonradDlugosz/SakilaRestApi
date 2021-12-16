package com.sprata.sakila.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Staff{

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("password")
	private Object password;

	@JsonProperty("address")
	private Address address;

	@JsonProperty("lastUpdate")
	private String lastUpdate;

	@JsonProperty("active")
	private Boolean active;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("store")
	private Integer store;

	@JsonProperty("picture")
	private Object picture;

	@JsonProperty("email")
	private String email;

	@JsonProperty("username")
	private String username;

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setPassword(Object password){
		this.password = password;
	}

	public Object getPassword(){
		return password;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}

	public void setLastUpdate(String lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdate(){
		return lastUpdate;
	}

	public void setActive(Boolean active){
		this.active = active;
	}

	public Boolean isActive(){
		return active;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setStore(Integer store){
		this.store = store;
	}

	public Integer getStore(){
		return store;
	}

	public void setPicture(Object picture){
		this.picture = picture;
	}

	public Object getPicture(){
		return picture;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}