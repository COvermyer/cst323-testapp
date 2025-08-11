package com.gcu.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerModel {
    
    private int customer_id;

    @NotBlank(message = "Name is a required field")
    private String name;

    @NotBlank(message = "Email is a required field")
    @Email(message = "Please enter a valid email")
    private String email;

    private String join_date;

    public CustomerModel() {
        super();
    }

    public CustomerModel(int customer_id, String name, String email, String join_date) {
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

    public boolean isValid() {
        return name != null && !name.trim().isEmpty()
            && email != null && !email.trim().isEmpty();
    }
}