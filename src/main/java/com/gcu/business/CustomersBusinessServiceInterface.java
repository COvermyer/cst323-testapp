package com.gcu.business;

import java.util.List;

import com.gcu.model.CustomerModel;

public interface CustomersBusinessServiceInterface {
	List<CustomerModel> getCustomers();
	List<CustomerModel> getCustomersInRange(int limit, int offset);
	CustomerModel getCustomerById(int customer_id);
	boolean addCustomer(CustomerModel customer);
	boolean updateCustomer(CustomerModel customer);
	boolean deleteCustomer(int id);
	boolean deleteCustomer(CustomerModel customer);
	int getTotalCustomerCount();
}
