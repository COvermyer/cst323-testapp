package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.business.CustomersBusinessServiceInterface;
import com.gcu.model.CustomerModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/customers")
public class CustomersController {

	@Autowired
	CustomersBusinessServiceInterface service;
	
	@GetMapping("/select")
	public String handleSelection(@RequestParam int limit, @RequestParam(defaultValue = "0") int offset, @RequestParam(required = false) String direction)
	{
		int newOffset = offset;
		if("next".equals(direction))
		{
			newOffset += limit;
		}
		else if ("prev".equals(direction))
			newOffset = Math.max(0, offset - limit); // Prevent negative offset
		
		return "redirect:/customers/display?limit=" + limit + "&offset=" + newOffset;
	}
	
	@GetMapping("/display")
	public String displayCustomers(@RequestParam(defaultValue = "25") int limit, @RequestParam(defaultValue = "0") int offset, Model model)
	{
		model.addAttribute("title", "Registered Customers");
		model.addAttribute("limit", limit);
		model.addAttribute("offset", offset);
		model.addAttribute("hasPrevious", offset > 0);
		model.addAttribute("hasNext", (offset + limit < service.getTotalCustomerCount()));
		model.addAttribute("customers", service.getCustomersInRange(limit, offset));
		
		return "customers";
	}
	
	
	// CUSTOMER Registration
	
	/**
	 * New Customer Registration GET method
	 * @param model
	 * @return
	 */
	@GetMapping("/new")
	public String newCustomerRegistration(Model model)
	{
		model.addAttribute("title", "New Customer Registration");
		model.addAttribute("customerModel", new CustomerModel());
		return "customerRegistration";
	}
	
	/**
	 * New Customer Registration POST method
	 * @param customerModel
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/doCustomerRegistration")
	public String doCustomerRegistration(@Valid @ModelAttribute("customerModel") CustomerModel customerModel,
	                                     BindingResult bindingResult,
	                                     Model model) {
	    
	    if (bindingResult.hasErrors() || !service.addCustomer(customerModel)) {
	        model.addAttribute("title", "New Customer Registration");
	        return "customerRegistration";
	    }

	    return "redirect:/customers/display";
	}
}
