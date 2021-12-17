package com.sparta.sakila.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Film{

	@JsonProperty("rentalRate")
	private Double rentalRate;

	@JsonProperty("rentalDuration")
	private Integer rentalDuration;

	@JsonProperty("length")
	private Integer length;

	@JsonProperty("rating")
	private String rating;

	@JsonProperty("description")
	private String description;

	@JsonProperty("replacementCost")
	private Double replacementCost;

	@JsonProperty("language")
	private Language language;

	@JsonProperty("title")
	private String title;

	@JsonProperty("originalLanguage")
	private Object originalLanguage;

	@JsonProperty("specialFeatures")
	private String specialFeatures;

	@JsonProperty("lastUpdate")
	private String lastUpdate;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("releaseYear")
	private Integer releaseYear;

	public void setRentalRate(Double rentalRate){
		this.rentalRate = rentalRate;
	}

	public Double getRentalRate(){
		return rentalRate;
	}

	public void setRentalDuration(Integer rentalDuration){
		this.rentalDuration = rentalDuration;
	}

	public Integer getRentalDuration(){
		return rentalDuration;
	}

	public void setLength(Integer length){
		this.length = length;
	}

	public Integer getLength(){
		return length;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setReplacementCost(Double replacementCost){
		this.replacementCost = replacementCost;
	}

	public Double getReplacementCost(){
		return replacementCost;
	}

	public void setLanguage(Language language){
		this.language = language;
	}

	public Language getLanguage(){
		return language;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setOriginalLanguage(Object originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	public Object getOriginalLanguage(){
		return originalLanguage;
	}

	public void setSpecialFeatures(String specialFeatures){
		this.specialFeatures = specialFeatures;
	}

	public String getSpecialFeatures(){
		return specialFeatures;
	}

	public void setLastUpdate(String lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdate(){
		return lastUpdate;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setReleaseYear(Integer releaseYear){
		this.releaseYear = releaseYear;
	}

	public Integer getReleaseYear(){
		return releaseYear;
	}
}