package com.gcu.data.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.gcu.model.CustomerModel;

@Table("customers")
public class CustomerEntity {
	@Column("customer_id")
	int customer_id;
	
	@Column("name")
	String name;
	
	@Column("email")
	String email;
	
	@Column("join_date")
	String join_date;

	public CustomerEntity(int customer_id, String name, String email, String join_date) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.email = email;
		this.join_date = join_date;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJoin_date() {
		return join_date;
	}

	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	
	public CustomerModel toModel()
	{
		return new CustomerModel(
					this.customer_id,
					this.name,
					this.email,
					this.join_date
				);
	}
}
