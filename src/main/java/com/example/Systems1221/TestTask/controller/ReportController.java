package com.example.Systems1221.TestTask.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {


    @GetMapping(value = "/dailySumCal", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity dailyReportWithSumOfCalories(String userEmail){
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/dailySumCalAndReqSumCal", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity CheckEqDailySumOfCalorieAndCalorieReq(String userEmail){
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/foodHistory", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity foodHistoryByDay(String userEmail){
        return ResponseEntity.ok().build();
    }
}
