package com.example.demo.info.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Wishlist {
	private int num;
	private String title;
	private String synopsis;
	private String poster_image;
	private String genre;
}
