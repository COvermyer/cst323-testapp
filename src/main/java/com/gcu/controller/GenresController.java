package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.GenresBusinessServiceInterface;
import com.gcu.model.GenreModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/genres")
public class GenresController {

	@Autowired
	GenresBusinessServiceInterface service;
	
	@GetMapping("/all")
	public String displayGenres(Model model)
	{
		model.addAttribute("title", "Genres");
		model.addAttribute("genres", service.getGenres());
		return "genres";
	}
	
	@GetMapping("/new")
	public String newGenreRegistration(Model model)
	{
		model.addAttribute("title", "Genre Registration");
		model.addAttribute("genreModel", new GenreModel());
		return "genreRegistration";
	}
	
	@PostMapping("/doGenreRegistration")
	public String doGenreRegistration(@Valid @ModelAttribute("genreModel") GenreModel genreModel,
									  BindingResult bindingResult,
									  Model model) {
		if (bindingResult.hasErrors() || !service.addGenre(genreModel))
		{
			model.addAttribute("title", "Genre Registration");
			return "genreRegistration";
		}
		
		System.out.println("New genre: " + genreModel.getGenre_name());
		return "redirect:/genres/all";
	}
}
