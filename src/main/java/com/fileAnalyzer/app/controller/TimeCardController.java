package com.fileAnalyzer.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
}
