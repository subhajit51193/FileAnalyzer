package com.fileAnalyzer.app.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fileAnalyzer.app.model.Timecard;

@Component
public class Helper {

	/*
	 * This methods checks if excel file hasve been passed as param or not
	 * 
	 * @param: MultipartFile -> File passed as param
	 * @return: Boolean -> If it is excel file then true otherwise false
	 */
	public static boolean checkExcelFormat(MultipartFile multipartFile) {
		
		String contentType = multipartFile.getContentType();
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * This method convert excel data into list of TimeCard objects product
	 * and returns the list
	 * 
	 * @param: InputStream -> input stream
	 * @return: List<TimeCard> -> List of all TimeCard objects
	 */
	public List<Timecard> convertExcelToListOfProduct(InputStream inputStream){
		
		List<Timecard> list = new ArrayList<>();
		
		try {
			
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			while(iterator.hasNext()) {
				Row row = iterator.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cells = row.iterator();
				int cid = 0;
				
				Timecard timecard = new Timecard();
				
				while(cells.hasNext()) {
					Cell cell = cells.next();
					
					switch (cid) {
					case 0:
						
						timecard.setPositionId(cell.getStringCellValue());
						break;
					
					case 1:
						timecard.setPositionStatus(cell.getStringCellValue());
						break;
					
					case 2:
						timecard.setTime(cell.toString());
						break;
					case 3:
						timecard.setTimeOut(cell.toString());
						break;
					case 4:
						timecard.setTimecardHours(cell.toString());
						break;
					case 5:
						timecard.setPayCycleStartDate(cell.toString());
						break;
					case 6:
						timecard.setPayCycleEndDate(cell.toString());
						break;
					
					case 7:
						timecard.setEmployeeName(cell.getStringCellValue());
						break;
					
					case 8:
						timecard.setFileNumber(cell.getStringCellValue());
						break;
					
					default:
						break;
					}
					cid++;
				}
				
				list.add(timecard);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
