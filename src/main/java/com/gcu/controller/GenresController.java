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

import com.gcu.business.GenresBusinessServiceInterface;
import com.gcu.model.GenreModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/genres")
public class GenresController {

	@Autowired
	GenresBusinessServiceInterface service;
	
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
		
		return "redirect:/genres/display?limit=" + limit + "&offset=" + newOffset;
	}
	
	@GetMapping("/display")
	public String displayGenres(@RequestParam(defaultValue = "25") int limit, @RequestParam(defaultValue = "0") int offset, Model model)
	{
		model.addAttribute("title", "Registered Genres");
		model.addAttribute("limit", limit);
		model.addAttribute("offset", offset);
		model.addAttribute("hasPrevious", offset > 0);
		model.addAttribute("hasNext", (offset + limit < service.getTotalGenreCount()));
		model.addAttribute("genres", service.getGenresInRange(limit, offset));
		
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
