package com.example.demo.info.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.info.model.Wishlist;

public class WishlistRowMapper implements RowMapper<Wishlist>{
	
	@Override
	public Wishlist mapRow(ResultSet rs, int rowNum) throws SQLException {
		Wishlist wishlist = new Wishlist();
		wishlist.setNum(rs.getInt("num"));
		wishlist.setTitle(rs.getString("title"));
		wishlist.setSynopsis(rs.getString("synopsis"));
		wishlist.setPoster_image(rs.getString("poster_image"));
		wishlist.setGenre(rs.getString("genre"));
		return wishlist;
	}
}
