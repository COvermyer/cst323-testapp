package com.gcu.controller;

import java.util.List;

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
import com.gcu.business.MoviesBusinessServiceInterface;
import com.gcu.model.GenreModel;
import com.gcu.model.MovieModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	MoviesBusinessServiceInterface moviesService;
	
	@Autowired
	GenresBusinessServiceInterface genresService;
	
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
		
		return "redirect:/movies/display?limit=" + limit + "&offset=" + newOffset;
	}
	
	@GetMapping("/display")
	public String displayMovies(@RequestParam(defaultValue = "25") int limit, @RequestParam(defaultValue = "0") int offset, Model model)
	{
		model.addAttribute("title", "Registered Movies");
		model.addAttribute("limit", limit);
		model.addAttribute("offset", offset);
		model.addAttribute("hasPrevious", offset > 0);
		model.addAttribute("hasNext", (offset + limit < moviesService.getTotalMovieCount()));
		model.addAttribute("movies", moviesService.getMoviesInRange(limit, offset));
		
		return "movies";
	}
	
	@GetMapping("/new")
	public String newMovieRegistration(Model model)
	{
		List<GenreModel> genres = genresService.getGenres();
		model.addAttribute("title", "New Movie Registration");
		model.addAttribute("genres", genres);
		model.addAttribute("movieModel", new MovieModel());
		return "moviesRegistration";
	}
	
	@PostMapping("/doMovieRegistration")
	public String doMovieRegistration(@Valid @ModelAttribute("movieModel") MovieModel movieModel,
										BindingResult bindingResult,
										Model model)
	{
		if (bindingResult.hasErrors())
		{
			List<GenreModel> genres = genresService.getGenres();
			model.addAttribute("title", "New Movie Registration");
			model.addAttribute("genres", genres);
			return "moviesRegistration";
		}
		
		String genre = genresService.getGenreById(movieModel.getGenre_id()).getGenre_name();
		System.out.println(String.format("New %s Movie: %s, (%d) - %d available", genre, movieModel.getTitle(), movieModel.getRelease_year(), movieModel.getAvailable_copies()));
		System.out.println("Genre ID: " + movieModel.getGenre_id());
		return "redirect:/movies/all";
	}
}
