package com.example.demo.info.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import com.example.demo.info.model.Userinfo;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Repository
public class UserinfoRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final UserinfoRowMapper userinfoRowMapper;
	
	public UserinfoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.userinfoRowMapper = new UserinfoRowMapper();
	}
	
	public List<Userinfo> findList(){
		
		log.debug("findList query : {}", UserinfoSql.SELECT);
		
		return namedParameterJdbcTemplate.query(UserinfoSql.SELECT,
				EmptySqlParameterSource.INSTANCE ,this.userinfoRowMapper);

	}
	
	public Userinfo insert(Userinfo userinfo) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameterSource = new MapSqlParameterSource("id", userinfo.getId())
				.addValue("pw", userinfo.getPw())
				.addValue("name", userinfo.getName()); 
		int affectedRows = namedParameterJdbcTemplate.update(UserinfoSql.INSERT, parameterSource, keyHolder);
		log.debug("{} inserted, new id = {}", affectedRows, userinfo.getId());
		//userinfo.setId(keyHolder.getKey().intValue());
		return userinfo;
	}
	
	public Integer updateById(Userinfo userinfo) {
		String qry = UserinfoSql.UPDATE + UserinfoSql.ID_CONDITION;
		
		SqlParameterSource parameterSource = new MapSqlParameterSource("id", userinfo.getId())
				.addValue("pw", userinfo.getPw())
				.addValue("name", userinfo.getName());
		return namedParameterJdbcTemplate.update(qry, parameterSource);
	}
	
	public Integer deleteById(String id) {
		SqlParameterSource parameterSource = new MapSqlParameterSource("id", id); 
		return namedParameterJdbcTemplate.update(UserinfoSql.DELETE + UserinfoSql.ID_CONDITION, parameterSource);
	}
}
	
