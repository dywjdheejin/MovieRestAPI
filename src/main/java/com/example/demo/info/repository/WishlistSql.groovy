package com.example.demo.info.repository

class WishlistSql {
	public static final String SELECT = """
		SELECT num, title, synopsis, poster_image, genre FROM wishlist where 1=1
	""";
	
	public static final String INSERT = """
		INSERT INTO wishlist (title, synopsis, poster_image, genre) values (:title, :synopsis, :poster_image, :genre)
	""";
	
	public static final String DELETE = """
		DELETE FROM wishlist WHERE 1=1  
	""";
	
	public static final String NUM_CONDITION = """
		AND num = :num
	""";
	
	public static final String TITLE_CONDITION = """
		AND title = :title
	""";
	
	public static final String GENRE_CONDITION = """
		AND genre = :genre
	""";

}
