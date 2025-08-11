package com.gcu.model;

import jakarta.validation.constraints.NotBlank;

public class GenreModel {
	int genre_id;

	@NotBlank(message="Genre Name is a required field")
	String genre_name;

	public GenreModel() {
		super();
	}

	public GenreModel(int genre_id, String genre_name) {
		super();
		this.genre_id = genre_id;
		this.genre_name = genre_name;
	}

	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}

	public String getGenre_name() {
		return genre_name;
	}

	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	
	
}
