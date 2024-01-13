package com.fileAnalyzer.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fileAnalyzer.app.helper.Helper;
import com.fileAnalyzer.app.model.Timecard;
import com.fileAnalyzer.app.repository.TimeCardRepository;

@Service
public class TimeCardServiceImpl implements TimeCardService{

	@Autowired
	private Helper helper;
	
	@Autowired
	private TimeCardRepository timeCardRepository;
	
	/*
	 * Saves the data from Excel into Database
	 * 
	 * @param: MultipartFile -> Excel file
	 * 
	 * @return: String -> Customized message upon success
	 */
	@Override
	public String save(MultipartFile file) throws IOException {
		
		List<Timecard> list = helper.convertExcelToListOfProduct(file.getInputStream());
		for (Timecard timecard: list) {
			timeCardRepository.save(timecard);
		}
		return "saved ";
		
	}

	@Override
	public List<Timecard> getAllTimecards() {
		return timeCardRepository.findAll();
	}

}
