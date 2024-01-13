package com.fileAnalyzer.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Timecard {

	@Id
    private String positionId;
    private String positionStatus;
    private String time;
    private String timeOut;
    private String timecardHours;
    private String payCycleStartDate;
    private String payCycleEndDate;
    private String employeeName;
    private String fileNumber;
	
}
