package com.sparta.sakila.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilmText{

	@JsonProperty("description")
	private String description;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("title")
	private String title;

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public String toJson(){
		String data = "{\"id\":\" "
				+id+
				"\",\"title\":\""
				+title+
				"\",\"description\":\""
				+description+
				"\"}";
		return data;

		}




}