package com.fileAnalyzer.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fileAnalyzer.app.model.Timecard;

public interface TimeCardRepository extends JpaRepository<Timecard, String>{

	@Query(value = "select * FROM (select *, (SUBSTRING_INDEX(timecard_hours,\":\",1)*60 + SUBSTRING_INDEX(timecard_hours,\":\",-1))/60 AS hours FROM timecard) AS c1 WHERE c1.hours > 14"
			,nativeQuery = true)
	public List<Timecard> getRecordsForMoreThen14Hours();
	
	@Query(value = "select * FROM (select *, (SUBSTRING_INDEX(timecard_hours,\":\",1)*60 + SUBSTRING_INDEX(timecard_hours,\":\",-1))/60 AS hours FROM timecard) AS c1 WHERE c1.hours > 1 AND c1.hours < 10", nativeQuery = true)
	public List<Timecard> getRecordInBetweenHours();
}
