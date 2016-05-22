package com.bootdemo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.bootdemo.model.Report;

import java.util.List;

public interface ReportRepository extends MongoRepository<Report, String> {
    Report findByTitle(String title);
    List<Report> findByDate(String date);
}