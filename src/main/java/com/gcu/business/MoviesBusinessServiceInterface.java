package com.gcu.business;

import java.util.List;

import com.gcu.model.MovieModel;

public interface MoviesBusinessServiceInterface {
	List<MovieModel> getMovies();
	List<MovieModel> getMoviesInRange(int limit, int offset);
	MovieModel getMovieById(int movie_id);
	boolean addMovie(MovieModel movie);
	boolean updateMovie(MovieModel movie);
	boolean deleteMovie(int id);
	boolean deleteMovie(MovieModel movie);
	int getTotalMovieCount();
}
