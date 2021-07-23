package com.example.demo.info.repository

class MovielistSql {
	public static final String SELECT = """
		SELECT num, title, synopsis, poster_image, genre FROM movie where 1=1
	""";
	
	public static final String TITLE_CONDITION = """
		AND title = :title
	""";
	
	public static final String GENRE_CONDITION = """
		AND genre = :genre
	""";
}