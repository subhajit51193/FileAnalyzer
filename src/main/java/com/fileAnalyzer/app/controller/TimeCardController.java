package com.fileAnalyzer.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileAnalyzer.app.model.TimeCardDto;
import com.fileAnalyzer.app.service.TimeCardService;

@RestController
@RequestMapping("/api")
public class TimeCardController {

	@Autowired
	private TimeCardService timeCardService;
	
	@PostMapping("/file/upload")
	public ResponseEntity<String> saveFileDetails(@RequestParam("file") MultipartFile file) throws IOException{
		
		String res = timeCardService.save(file);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/moreThen14Hours")
	public ResponseEntity<List<TimeCardDto>> getDataForMoreThen14Hours(){
		
		List<TimeCardDto> res = timeCardService.moreThen14HoursShift();
		return new ResponseEntity<List<TimeCardDto>>(res,HttpStatus.OK);
	}
	
	@GetMapping("/inBetweenHours")
	public ResponseEntity<List<TimeCardDto>> inBetweenHours(){
		
		List<TimeCardDto> res = timeCardService.inBetweenHours();
		return new ResponseEntity<List<TimeCardDto>>(res,HttpStatus.OK);
	}
}
