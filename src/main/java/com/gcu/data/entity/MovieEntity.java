package com.gcu.data.entity;

import com.gcu.model.MovieModel;

public class MovieEntity {
	int movie_id;
	String title;
	int release_year;
	int available_copies;
	int genre_id;
	
	public MovieEntity(int movie_id, String title, int release_year, int available_copies, int genre_id) {
		super();
		this.movie_id = movie_id;
		this.title = title;
		this.release_year = release_year;
		this.available_copies = available_copies;
		this.genre_id = genre_id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRelease_year() {
		return release_year;
	}

	public void setRelease_year(int release_year) {
		this.release_year = release_year;
	}

	public int getAvailable_copies() {
		return available_copies;
	}

	public void setAvailable_copies(int available_copies) {
		this.available_copies = available_copies;
	}

	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	
	public MovieModel toModel()
	{
		return new MovieModel(
					this.movie_id,
					this.title,
					this.release_year,
					this.available_copies,
					this.genre_id
				);
	}
}
