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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.info.model.Userinfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("userinfo")
public class InfoController {
	
	private InfoService service;
	
	@Autowired
	public InfoController(InfoService service) {
		this.service = service;
	}
	
	
	@GetMapping("List")
	public Object userList() {
		log.debug("/userList start");
		List<Userinfo> cityList = service.getUserinfoList();
		return cityList;
	}
	
	@PostMapping(value="Add")
	public ResponseEntity<Userinfo> userAdd(@RequestBody Userinfo userinfo) {
		try {
			log.debug("userinfo = {}", userinfo.toString());
			return new ResponseEntity<>(service.insert(userinfo), HttpStatus.OK);
		}catch (Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
	@PostMapping(value="Edit")
	public ResponseEntity<String> userEdit(@RequestBody Userinfo userinfo) {
		try {
			log.debug("userinfo = {}", userinfo.toString());
			Integer updatedCnt = service.updateById(userinfo);
			return new ResponseEntity<>(String.format("%d updated", updatedCnt), HttpStatus.OK);
		}catch (Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="Delete")
	public ResponseEntity<String> userDelete(@RequestParam(value="id") String id) {
		try {
			log.debug("userinfo id = {}", id);
			Integer deletedCnt = service.deleteById(id);
			return new ResponseEntity<>(String.format("%d deleted", deletedCnt), HttpStatus.OK);
		}catch (Exception e) {
			log.error(e.toString());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
