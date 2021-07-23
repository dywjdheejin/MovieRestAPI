package com.example.demo.info.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.info.model.Diary;

public class DiaryRowMapper implements RowMapper<Diary>{
	
	@Override
	public Diary mapRow(ResultSet rs, int rowNum) throws SQLException {
		Diary diary = new Diary();
		diary.setId(rs.getString("id"));
		diary.setNum(rs.getInt("num"));
		diary.setContent(rs.getString("content"));
		
		return diary;
	}
}