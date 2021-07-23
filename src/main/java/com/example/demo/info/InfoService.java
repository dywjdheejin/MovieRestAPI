package com.example.demo.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.info.model.Userinfo;
import com.example.demo.info.repository.UserinfoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfoService {
	
	private final UserinfoRepository userinfoRepository;
	
	@Autowired
	public InfoService(UserinfoRepository userinfoRepository) {
		this.userinfoRepository = userinfoRepository;
	}
	
	public List<Userinfo> getUserinfoList() {
		return this.userinfoRepository.findList();
	}

	public Userinfo insert(Userinfo userinfo) {
		return this.userinfoRepository.insert(userinfo);
	}
	
	public Integer updateById(Userinfo userinfo) {
		log.debug("userinfo id = {}", userinfo.getId());
		return userinfoRepository.updateById(userinfo);
	} 
	
	public Integer deleteById(String id) {
		log.debug("userinfo id = {}", id);
		return userinfoRepository.deleteById(id);
	}
}
