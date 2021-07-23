package com.example.demo.info;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.info.model.Movielist;
import com.example.demo.info.model.Wishlist;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("movielist")
public class MovielistController {
	
	private MovielistService service;
	
	public MovielistController(MovielistService service) {
		this.service = service;
	}
	
	@GetMapping("list")
	public Object Movielist() {
		log.debug("/Movielist start");
		List<Movielist> movielist = service.getMovielist();
		return movielist;
	}
	
	@GetMapping("movielistByGenre")
	public Object movielistByGenre(@RequestParam("genre") String genre) {
		log.debug("genre = {}", genre);
		List<Movielist> movielist = service.findMovielistByGenre(genre);
		return movielist;
	}
	
	@GetMapping("movielistByTitle")
	public Object movielistByTitle(@RequestParam("title") String title) {
		log.debug("title = {}", title);
		List<Movielist> movielist = service.findMovielistByTitle(title);
		return movielist;
	}

}