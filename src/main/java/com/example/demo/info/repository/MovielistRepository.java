package com.example.demo.info.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.info.model.Movielist;
import com.example.demo.info.model.Wishlist;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MovielistRepository {
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final MovielistRowMapper movielistRowMapper;
	
	public MovielistRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.movielistRowMapper = new MovielistRowMapper();
	}
	
	public List<Movielist> findList(){
		
		log.debug("findList query = {}", MovielistSql.SELECT);

		return namedParameterJdbcTemplate.query(MovielistSql.SELECT
				, EmptySqlParameterSource.INSTANCE
				, this.movielistRowMapper);
	}
	
	public List<Movielist> findByGenre(String genre){
		
		String qry = MovielistSql.SELECT
				+ MovielistSql.GENRE_CONDITION;
		SqlParameterSource param = new MapSqlParameterSource("genre", genre);
		return namedParameterJdbcTemplate.query(qry, param, this.movielistRowMapper);
	}
	
	public List<Movielist> findByTitle(String title){
		
		String qry = MovielistSql.SELECT
				+ MovielistSql.TITLE_CONDITION;
		SqlParameterSource param = new MapSqlParameterSource("title", title);
		return namedParameterJdbcTemplate.query(qry, param, this.movielistRowMapper);
	}

}