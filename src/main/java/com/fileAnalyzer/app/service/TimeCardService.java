package com.fileAnalyzer.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fileAnalyzer.app.model.Timecard;

public interface TimeCardService {

	public String save(MultipartFile file) throws IOException;
	
	public List<Timecard> getAllTimecards();
}
