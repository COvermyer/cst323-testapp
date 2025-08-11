package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.data.entity.CustomerEntity;
import com.gcu.data.mapper.CustomerRowMapper;
import com.gcu.data.repository.CustomersRepository;

@Service
public class CustomersDataService implements DataAccessInterface<CustomerEntity> {

	@Autowired
	private CustomersRepository customersRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<CustomerEntity> findAll() {
		List<CustomerEntity> customers = new ArrayList<CustomerEntity>();
		try {
			Iterable<CustomerEntity> customersIterable = customersRepository.findAll();
			customersIterable.forEach(customers::add);
		} catch (Exception e) { e.printStackTrace(); }
		return customers;
	}

	@Override
	public CustomerEntity findById(int id) {
		String sql = "SELECT * FROM customers WHERE customer_id = ?";
		try {
			List<CustomerEntity> results = jdbcTemplate.query(sql, new CustomerRowMapper(), id);
			if (!results.isEmpty())
			{
				return results.get(0);
			}
		} catch (Exception e) { e.printStackTrace();}
		return null;
	}

	@Override
	public boolean create(CustomerEntity t) {
		String sql = "INSERT INTO customers (name, email, join_date) VALUES (?, ?, ?)";
		try {
			int num = jdbcTemplate.update(sql, t.getName(), t.getEmail(), t.getJoin_date());
			return num == 1;
		} catch (DataAccessException d) { d.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		return false;
	}

	@Override
	public boolean update(CustomerEntity t) {
		String sql = "UPDATE customers SET name = ?, email = ?, join_date = ? WHERE customer_id = ?";
		try {
			int num = jdbcTemplate.update(sql, t.getName(), t.getEmail(), t.getJoin_date(), t.getCustomer_id());
			return num == 1;
		} catch (DataAccessException d) { d.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		String sql = "DELETE FROM customers WHERE customer_id = ?";
		try {
			int num = jdbcTemplate.update(sql, id);
			return num == 1;
		} catch (DataAccessException d) { d.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		return false;
	}

	@Override
	public List<CustomerEntity> findRange(int limit, int offset) {
		String sql = "SELECT * FROM customers LIMIT ? OFFSET ?";
		List<CustomerEntity> results = new ArrayList<CustomerEntity>();
		try {
			Iterable<CustomerEntity> customersIterable = jdbcTemplate.query(sql, new CustomerRowMapper(), limit, offset);
			customersIterable.forEach(results::add);
		} catch (DataAccessException d) { d.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		return results;
	}

	@Override
	public int getEntityCount() {
		String sql = "SELECT COUNT(*) FROM customers WHERE 1";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
