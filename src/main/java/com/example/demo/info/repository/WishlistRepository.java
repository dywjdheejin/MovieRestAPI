package com.example.demo.info.repository;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.info.model.Wishlist;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class WishlistRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final WishlistRowMapper wishlistRowMapper;
	
	public WishlistRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.wishlistRowMapper = new WishlistRowMapper();
	}

	public List<Wishlist> findList(){
		
		log.debug("findList query = {}", WishlistSql.SELECT);

		return namedParameterJdbcTemplate.query(WishlistSql.SELECT
				, EmptySqlParameterSource.INSTANCE
				, this.wishlistRowMapper);
	}
	
	public List<Wishlist> findByGenre(String genre){
		
		String qry = WishlistSql.SELECT
				+ WishlistSql.GENRE_CONDITION;
		SqlParameterSource param = new MapSqlParameterSource("genre", genre);
		return namedParameterJdbcTemplate.query(qry, param, this.wishlistRowMapper);
	}
	
	public Wishlist insert(Wishlist wishlist) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameterSource = new MapSqlParameterSource("title", wishlist.getTitle())
				.addValue("synopsis", wishlist.getSynopsis())
				.addValue("poster_image", wishlist.getPoster_image())
				.addValue("genre", wishlist.getGenre()); 
		int affectedRows = namedParameterJdbcTemplate.update(WishlistSql.INSERT, parameterSource, keyHolder);
		log.debug("{} inserted, new title = {}", affectedRows, keyHolder.getKey());
		//wishlist.setNum(keyHolder.getKey().intValue());
		return wishlist;
	}
	
	public Integer deleteByNum(int num) {
		SqlParameterSource parameterSource = new MapSqlParameterSource("num", num); 
		return namedParameterJdbcTemplate.update(WishlistSql.DELETE + WishlistSql.NUM_CONDITION, parameterSource);
	}
}
