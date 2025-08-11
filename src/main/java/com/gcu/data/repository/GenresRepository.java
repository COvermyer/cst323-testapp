package com.gcu.data.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gcu.data.entity.GenreEntity;

@Repository
public interface GenresRepository extends CrudRepository<GenreEntity, Integer> {

	@Override
	@Query(value="SELECT * FROM genres")
	public List<GenreEntity> findAll();
}
