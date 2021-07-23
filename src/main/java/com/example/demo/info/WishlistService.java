package com.example.demo.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.info.model.Wishlist;
import com.example.demo.info.repository.WishlistRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WishlistService {
private final WishlistRepository wishlistRepository;
	
	@Autowired
	public WishlistService(WishlistRepository wishlistRepository) {
		this.wishlistRepository = wishlistRepository;
	}
	
	
	public List<Wishlist> getWishList() {
		return this.wishlistRepository.findList();
	}
	
	public List<Wishlist> findWishlistByGenre(String genre) {
		log.debug("genre = {}", genre);
		return this.wishlistRepository.findByGenre(genre);
	}
	
	public Wishlist insert(Wishlist wishlist) {
		return this.wishlistRepository.insert(wishlist);
	}
	
	public Integer deleteByNum(int num) {
		log.debug("wishlist num = {}", num);
		return wishlistRepository.deleteByNum(num);
	}

}
