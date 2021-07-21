package com.example.demo.info.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.info.model.Userinfo;

public class UserinfoRowMapper implements RowMapper<Userinfo>{
	
	@Override
	public Userinfo mapRow(ResultSet rs, int rowNum) throws SQLException{
	
		Userinfo userinfo = new Userinfo();
		userinfo.setId(rs.getString("id"));
		userinfo.setName(rs.getString("name"));
		userinfo.setPw(rs.getString("pw"));
		return userinfo;
	}
}
