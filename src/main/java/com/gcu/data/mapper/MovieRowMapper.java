package com.gcu.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.data.entity.MovieEntity;

public class MovieRowMapper implements RowMapper<MovieEntity> {

	@Override
	public MovieEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new MovieEntity(
					rs.getInt("movie_id"),
					rs.getString("title"),
					rs.getInt("release_year"),
					rs.getInt("available_copies"),
					rs.getInt("genre_id")
				);
	}

}
