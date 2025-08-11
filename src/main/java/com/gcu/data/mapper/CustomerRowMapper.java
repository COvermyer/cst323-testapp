package com.gcu.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.data.entity.CustomerEntity;

public class CustomerRowMapper implements RowMapper<CustomerEntity> {

	@Override
	public CustomerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new CustomerEntity(
					rs.getInt("customer_id"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("join_date")
				);
	}

}
