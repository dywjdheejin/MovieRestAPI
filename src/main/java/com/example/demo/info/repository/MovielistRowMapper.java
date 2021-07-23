package com.example.demo.info.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.info.model.Movielist;


public class MovielistRowMapper implements RowMapper<Movielist>{
	
	@Override
	public Movielist mapRow(ResultSet rs, int rowNum) throws SQLException {
		Movielist movielist = new Movielist();
		movielist.setNum(rs.getInt("num"));
		movielist.setTitle(rs.getString("title"));
		movielist.setSynopsis(rs.getString("synopsis"));
		movielist.setPoster_image(rs.getString("poster_image"));
		movielist.setGenre(rs.getString("genre"));
		return movielist;
	}
}