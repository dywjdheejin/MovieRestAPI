package com.example.demo.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.info.model.Diary;
import com.example.demo.info.model.Userinfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("diary")
public class DiaryController {
	
	private DiaryService service;
	
	@Autowired
	public DiaryController(DiaryService service) {
		this.service = service;
	}
	
	@GetMapping("List")
	public Object diaryList() {
		log.debug("/diaryList start");
		List<Diary> diaryList = service.getDiaryList();
		return diaryList;
	}
	
	@PostMapping(value="Add")
	public ResponseEntity<Diary> diaryAdd(@RequestBody Diary diary) {
		try {
			log.debug("diary = {}", diary.toString());
			return new ResponseEntity<>(service.insert(diary), HttpStatus.OK);
		}catch (Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
	@PostMapping(value="Edit")
	public ResponseEntity<String> diaryEdit(@RequestBody Diary diary) {
		try {
			log.debug("diary = {}", diary.toString());
			Integer updatedCnt = service.update(diary);
			return new ResponseEntity<>(String.format("%d updated", updatedCnt), HttpStatus.OK);
		}catch (Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="Delete")
	public ResponseEntity<String> userDelete(@RequestParam(value="id") String id, @RequestParam(value="num") int num) {
		try {
			log.debug("diary id = {}, num = {}", id,num);
			Integer deletedCnt = service.delete(id,num);
			return new ResponseEntity<>(String.format("%d deleted", deletedCnt), HttpStatus.OK);
		}catch (Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}