package com.example.demo.info;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.info.model.Wishlist;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("wishlist")
public class WishlistController {
	
	private WishlistService service;
	
	public WishlistController(WishlistService service) {
		this.service = service;
	}
	
	@GetMapping("list")
	public Object WishList() {
		log.debug("/WishList start");
		List<Wishlist> wishlist = service.getWishList();
		return wishlist;
	}
	
	@GetMapping("wishlistByGenre")
	public Object wishlistByGenre(@RequestParam("genre") String genre) {
		log.debug("genre = {}", genre);
		List<Wishlist> wishlist = service.findWishlistByGenre(genre);
		return wishlist;
	}
	
	@PostMapping("Add")
	public ResponseEntity<Wishlist> wishlistAdd(@RequestBody Wishlist wishlist) {
		try {
			log.debug("wishlist = {}", wishlist.toString());
			return new ResponseEntity<>(service.insert(wishlist), HttpStatus.OK);
		}catch (Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ResponseBody
	@GetMapping(value="Delete")
	public ResponseEntity<String> wishlistDelete(@RequestParam(value="num") int num) {
		try {
			log.debug("wishlist num = {}", num);
			Integer deletedCnt = service.deleteByNum(num);
			return new ResponseEntity<>(String.format("%d deleted", deletedCnt), HttpStatus.OK);
		}catch (Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
