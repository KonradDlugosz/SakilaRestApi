package com.sparta.sakila.entity;

import java.util.List;

public class FilmTextAdd{
	private FilmText film;
	private String message;

	public void setFilm(FilmText film){
		this.film = film;
	}

	public FilmText getFilm(){
		return film;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}