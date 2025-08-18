package com.gcu.business;

import java.util.List;

import com.gcu.model.GenreModel;

public interface GenresBusinessServiceInterface {
	List<GenreModel> getGenres();
	List<GenreModel> getGenresInRange(int limit, int offset);
	GenreModel getGenreById(int genre_id);
	boolean addGenre(GenreModel genre);
	boolean updateGenre(GenreModel genre);
	boolean deleteGenre(int id);
	boolean deleteGenre(GenreModel genre);
	int getTotalGenreCount();
}
