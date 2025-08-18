package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.GenreEntity;
import com.gcu.data.mapper.GenreRowMapper;
import com.gcu.data.repository.GenresRepository;

@Service
public class GenresDataService implements DataAccessInterface<GenreEntity> {

	@Autowired
	GenresRepository genreRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<GenreEntity> findAll() {
		List<GenreEntity> genres = new ArrayList<GenreEntity>();
		try{
			Iterable<GenreEntity> genreIterable = genreRepository.findAll();
			genreIterable.forEach(genres::add);
		} catch (Exception e) { e.printStackTrace(); }
		return genres;
	}

	@Override
	public GenreEntity findById(int id) {
		String sql = "SELECT * FROM genres WHERE genre_id = ?";
		try {
			List<GenreEntity> results = jdbcTemplate.query(sql, new GenreRowMapper(), id);
			if (!results.isEmpty())
			{
				return results.get(0);
			}
		} catch (Exception e) { e.printStackTrace();}
		return null;
	}

	@Override
	public boolean create(GenreEntity t) {
		String sql = "INSERT INTO genres (genre_name) VALUES (?)";
		try {
			int num = jdbcTemplate.update(sql, t.getGenre_name());
			return num == 1;
		} catch (DataAccessException d) { d.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		return false;
	}

	@Override
	public boolean update(GenreEntity t) {
		String sql = "UPDATE genres SET genre_name = ? WHERE genre_id = ?";
		try {
			int num = jdbcTemplate.update(sql, t.getGenre_name(), t.getGenre_id());
			return num == 1;
		} catch (DataAccessException d) { d.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		String sql = "DELETE FROM genres WHERE genre_id = ?";
		try {
			int num = jdbcTemplate.update(sql, id);
			return num == 1;
		} catch (DataAccessException d) { d.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		return false;
	}

	@Override
	public List<GenreEntity> findRange(int limit, int offset) {
		String sql = "SELECT * FROM genres LIMIT ? OFFSET ?";
		List<GenreEntity> results = new ArrayList<GenreEntity>();
		try {
			Iterable<GenreEntity> genresIterable = jdbcTemplate.query(sql, new GenreRowMapper(), limit, offset);
			genresIterable.forEach(results::add);
		} catch (DataAccessException d) { d.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		return results;
	}

	@Override
	public int getEntityCount() {
		String sql = "SELECT COUNT(*) FROM genres WHERE 1";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
