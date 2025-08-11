package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.CustomersDataService;
import com.gcu.data.entity.CustomerEntity;
import com.gcu.model.CustomerModel;

@Service
public class CustomersBusinessService implements CustomersBusinessServiceInterface {

	@Autowired
	CustomersDataService service;
	
	@Override
	public List<CustomerModel> getCustomers() {
		List<CustomerEntity> customersEntity = service.findAll();
		List<CustomerModel> customersDomain = new ArrayList<CustomerModel>();
		for (CustomerEntity e : customersEntity)
			customersDomain.add(e.toModel());
		return customersDomain;
	}

	@Override
	public List<CustomerModel> getCustomersInRange(int limit, int offset) {
		List<CustomerEntity> customersEntity = service.findRange(limit, offset);
		List<CustomerModel> customersDomain = new ArrayList<CustomerModel>();
		for (CustomerEntity e : customersEntity)
			customersDomain.add(e.toModel());
		return customersDomain;
	}
	
	@Override
	public CustomerModel getCustomerById(int customer_id) {
		return service.findById(customer_id).toModel();
	}

	@Override
	public boolean addCustomer(CustomerModel customer) {
		return service.create(new CustomerEntity(customer.getCustomer_id(), customer.getName(), customer.getEmail(), customer.getJoin_date()));
	}

	@Override
	public boolean updateCustomer(CustomerModel customer) {
		return service.update(new CustomerEntity(customer.getCustomer_id(), customer.getName(), customer.getEmail(), customer.getJoin_date()));
	}

	@Override
	public boolean deleteCustomer(int id) {
		return service.deleteById(id);
	}

	@Override
	public boolean deleteCustomer(CustomerModel customer) {
		return deleteCustomer(customer.getCustomer_id());
	}

	@Override
	public int getTotalCustomerCount() {
		return service.getEntityCount();
	}

}
