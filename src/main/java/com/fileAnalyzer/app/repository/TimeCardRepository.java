package com.fileAnalyzer.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fileAnalyzer.app.model.Timecard;

public interface TimeCardRepository extends JpaRepository<Timecard, String>{

}
