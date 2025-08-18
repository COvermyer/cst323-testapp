package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.MovieEntity;
import com.gcu.data.mapper.MovieRowMapper;
import com.gcu.data.repository.MoviesRepository;

@Service
public class MoviesDataService implements DataAccessInterface<MovieEntity> {

	@Autowired
	MoviesRepository moviesRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<MovieEntity> findAll() {
		List<MovieEntity> movies = new ArrayList<MovieEntity>();
		try {
			Iterable<MovieEntity> movieIterable = moviesRepository.findAll();
			movieIterable.forEach(movies::add);
		} catch (Exception e) { e.printStackTrace(); }
		return movies;
	}

	@Override
	public MovieEntity findById(int id) {
		String sql = "SELECT * FROM movies WHERE movie_id = ?";
		try {
			List<MovieEntity> results = jdbcTemplate.query(sql, new MovieRowMapper(), id);
			if (!results.isEmpty())
			{
				return results.get(0);
			}
		} catch (Exception e) { e.printStackTrace();}
		return null;
	}

	@Override
	public boolean create(MovieEntity t) {
		String sql = "INSERT INTO movies (title, release_year, available_copies, genre_id) VALUES (?, ?, ?, ?)";
		try {
			int num = jdbcTemplate.update(sql, t.getTitle(), t.getRelease_year(), t.getAvailable_copies(), t.getGenre_id());
			return num == 1;
		} catch (DataAccessException d) { d.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		return false;
	}

	@Override
	public boolean update(MovieEntity t) {
		String sql = "UPDATE movies SET title = ?, release_year = ?, available_copies = ?, genre_id = ? WHERE movie_id = ?";
		try {
			int num = jdbcTemplate.update(sql, t.getTitle(), t.getRelease_year(), t.getAvailable_copies(), t.getGenre_id(), t.getMovie_id());
			return num == 1;
		} catch (DataAccessException d) { d.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		String sql = "DELETE FROM movies WHERE movie_id = ?";
		try {
			int num = jdbcTemplate.update(sql, id);
			return num == 1;
		} catch (DataAccessException d) { d.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		return false;
	}

	@Override
	public List<MovieEntity> findRange(int limit, int offset) {
		String sql = "SELECT * FROM movies LIMIT ? OFFSET ?";
		List<MovieEntity> results = new ArrayList<MovieEntity>();
		try {
			Iterable<MovieEntity> moviesIterable = jdbcTemplate.query(sql, new MovieRowMapper(), limit, offset);
			moviesIterable.forEach(results::add);
		} catch (DataAccessException d) { d.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		return results;
	}

	@Override
	public int getEntityCount() {
		String sql = "SELECT COUNT(*) FROM movies WHERE 1";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
