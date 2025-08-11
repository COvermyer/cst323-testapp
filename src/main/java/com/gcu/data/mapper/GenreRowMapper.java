package com.gcu.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.data.entity.GenreEntity;

public class GenreRowMapper implements RowMapper<GenreEntity> {
	@Override
	public GenreEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new GenreEntity(
					rs.getInt("genre_id"),
					rs.getString("genre_name")
				);
	}

}
