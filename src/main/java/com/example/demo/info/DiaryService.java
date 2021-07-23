package com.example.demo.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.info.model.Diary;
import com.example.demo.info.model.Userinfo;
import com.example.demo.info.repository.DiaryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiaryService {

	private final DiaryRepository diaryRepository;
	
	@Autowired
	public DiaryService(DiaryRepository diaryRepository) {
		this.diaryRepository = diaryRepository;
	}
	
	public List<Diary> getDiaryList() {
		return this.diaryRepository.findList();
	}
	
	public Diary insert(Diary diary) {
		return this.diaryRepository.insert(diary);
	}
	
	public Integer update(Diary diary) {
		log.debug("diary id = {} , num = {}", diary.getId(), diary.getNum());
		return diaryRepository.update(diary);
	} 
	
	public Integer delete(String id, int num) {
		log.debug("diary id = {}, num = {}", id, num);
		return diaryRepository.delete(id, num);
	}
}