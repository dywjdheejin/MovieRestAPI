package com.example.demo.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.info.model.Movielist;
import com.example.demo.info.repository.MovielistRepository;
import com.example.demo.info.repository.WishlistRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovielistService {
private final MovielistRepository movielistRepository;
	
	@Autowired
	public MovielistService(MovielistRepository movielistRepository) {
		this.movielistRepository = movielistRepository;
	}
	
	
	public List<Movielist> getMovielist() {
		return this.movielistRepository.findList();
	}
	
	public List<Movielist> findMovielistByGenre(String genre) {
		log.debug("genre = {}", genre);
		return this.movielistRepository.findByGenre(genre);
	}
	
	public List<Movielist> findMovielistByTitle(String title) {
		log.debug("title = {}", title);
		return this.movielistRepository.findByTitle(title);
	}
}