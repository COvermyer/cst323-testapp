package com.gcu.data.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.gcu.data.entity.MovieEntity;

public interface MoviesRepository extends CrudRepository<MovieEntity, Integer> {

	@Override
	@Query(value="SELECT * FROM movies")
	public List<MovieEntity> findAll();
}
