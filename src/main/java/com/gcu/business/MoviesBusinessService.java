package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.MoviesDataService;
import com.gcu.data.entity.MovieEntity;
import com.gcu.model.MovieModel;

@Service
public class MoviesBusinessService implements MoviesBusinessServiceInterface {

	@Autowired
	MoviesDataService service;
	
	@Override
	public List<MovieModel> getMovies() {
		List<MovieEntity> moviesEntity = service.findAll();
		List<MovieModel> moviesDomain = new ArrayList<MovieModel>();
		for (MovieEntity e : moviesEntity)
			moviesDomain.add(e.toModel());
		return moviesDomain;
	}

	@Override
	public MovieModel getMovieById(int movie_id) {
		return service.findById(movie_id).toModel();
	}

	@Override
	public boolean addMovie(MovieModel movie) {
		return service.create(new MovieEntity(movie.getMovie_id(), movie.getTitle(), movie.getRelease_year(), movie.getAvailable_copies(), movie.getGenre_id()));
	}

	@Override
	public boolean updateMovie(MovieModel movie) {
		return service.update(new MovieEntity(movie.getMovie_id(), movie.getTitle(), movie.getRelease_year(), movie.getAvailable_copies(), movie.getGenre_id()));
	}

	@Override
	public boolean deleteMovie(int id) {
		return service.deleteById(id);
	}

	@Override
	public boolean deleteMovie(MovieModel movie) {
		return deleteMovie(movie.getMovie_id());
	}

	@Override
	public List<MovieModel> getMoviesInRange(int limit, int offset) {
		List<MovieEntity> moviesEntities = service.findRange(limit, offset);
		List<MovieModel> moviesDomain = new ArrayList<MovieModel>();
		for (MovieEntity e : moviesEntities)
			moviesDomain.add(e.toModel());
		return moviesDomain;
	}

	@Override
	public int getTotalMovieCount() {
		return service.getEntityCount();
	}

}
