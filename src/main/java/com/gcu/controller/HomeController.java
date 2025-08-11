package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("/")
	public String handleDefault(Model model)
	{
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String displayHome(Model model)
	{
		model.addAttribute("title", "Home");
		return "home";
	}
	
	@GetMapping("/error")
	public String displayErrors(Model model)
	{
		model.addAttribute("title", "Error");
		return "error";
	}
}
