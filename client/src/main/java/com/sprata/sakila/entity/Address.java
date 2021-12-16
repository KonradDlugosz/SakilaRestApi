package com.sprata.sakila.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address{

	@JsonProperty("id")
	private Integer id;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
}