package com.gcu.data.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.gcu.model.GenreModel;

@Table("genre")
public class GenreEntity {

	@Column("genre_id")
	int genre_id;
	
	@Column("genre_name")
	String genre_name;

	public GenreEntity(int genre_id, String genre_name) {
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
	
	public GenreModel toModel()
	{
		return new GenreModel(
				this.genre_id,
				this.genre_name
			);
	}
}
