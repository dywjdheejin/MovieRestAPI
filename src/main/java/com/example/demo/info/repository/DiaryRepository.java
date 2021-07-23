package com.example.demo.info.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.info.model.Diary;
import com.example.demo.info.model.Userinfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DiaryRepository {
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final DiaryRowMapper diaryRowMapper;
	
	public DiaryRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.diaryRowMapper = new DiaryRowMapper();
	}
	
	public List<Diary> findList(){
		
		log.debug("findList query : {}", DiarySql.SELECT);
		
		return namedParameterJdbcTemplate.query(DiarySql.SELECT,
				EmptySqlParameterSource.INSTANCE ,this.diaryRowMapper);
	}
	
	public Diary insert(Diary diary) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameterSource = new MapSqlParameterSource("id", diary.getId())
				.addValue("num", diary.getNum())
				.addValue("content", diary.getContent()); 
		int affectedRows = namedParameterJdbcTemplate.update(DiarySql.INSERT, parameterSource, keyHolder);
		log.debug("{} inserted, new id = {}", affectedRows, diary.getId());
		
		return diary;
	}
	
	public Integer update(Diary diary) {
		String qry = DiarySql.UPDATE + DiarySql.ID_CONDITION + DiarySql.NUM_CONDITION;
		
		SqlParameterSource parameterSource = new MapSqlParameterSource("id", diary.getId())
				.addValue("num", diary.getNum())
				.addValue("content", diary.getContent());
		return namedParameterJdbcTemplate.update(qry, parameterSource);
	}
	
	public Integer delete(String id, int num) {
		SqlParameterSource parameterSource = new MapSqlParameterSource("id", id).addValue("num", num); 
		return namedParameterJdbcTemplate.update(DiarySql.DELETE + DiarySql.ID_CONDITION + DiarySql.NUM_CONDITION, parameterSource);
	}
}