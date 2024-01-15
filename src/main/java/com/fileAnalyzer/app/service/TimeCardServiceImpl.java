package com.fileAnalyzer.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fileAnalyzer.app.helper.Helper;
import com.fileAnalyzer.app.model.TimeCardDto;
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

	@Override
	public List<TimeCardDto> moreThen14HoursShift() {
		
		List<TimeCardDto> res = new ArrayList<>();
		
		List<Timecard> list = timeCardRepository.getRecordsForMoreThen14Hours();
		for (Timecard timecard: list) {
			res.add(new TimeCardDto(timecard.getEmployeeName(), timecard.getPositionStatus()));
		}
		for (TimeCardDto timeCardDto: res) {
			System.out.println(timeCardDto);
		}
		return res;
		
	}

	@Override
	public List<TimeCardDto> inBetweenHours() {
		
		List<TimeCardDto> res = new ArrayList<>();
		
		List<Timecard> list = timeCardRepository.getRecordInBetweenHours();
		
		for (Timecard timecard: list) {
			res.add(new TimeCardDto(timecard.getEmployeeName(), timecard.getPositionStatus()));
		}
		for (TimeCardDto timeCardDto: res) {
			System.out.println(timeCardDto);
		}
		return res;
	}
}
