package com.bootdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdemo.model.Report;
import com.bootdemo.service.ReportService;
import com.bootdemo.task.executor.DemoAsyncTask;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tommy
 * @create 2016-05-17 12:17
 */


@RestController
@Api(tags="Report接口")
@RequestMapping("/report")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    ReportService reportService;
    
	@Autowired
	DemoAsyncTask asyncTask;//异步任务支持
	
	

    @ApiOperation(value="创建报告")	
    @RequestMapping(value="/create",method={RequestMethod.GET,RequestMethod.POST})
    public Map<String, Object> createReport(@RequestBody Map<String, Object> reportMap) {
        logger.info("createReport");
        Report report = reportService.createReport(reportMap);

        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("message", "Report created successfully");
        response.put("report", report);

        return response;
    }

    @ApiOperation(value="获取报告详情")
    @RequestMapping(value = "/getreport/{reportTitle}",method = RequestMethod.GET)
    public Report getReportDetails(@PathVariable("reportTitle") String title) {
        logger.info("getReportDetails");
        return reportService.getReportDetails(title);
    }
    
    
    @ApiOperation(value="测试直接mongotemplate插入")	
    @RequestMapping(value="/testTemplateInsert",method={RequestMethod.GET,RequestMethod.POST})
    public String testMongoInsert() {
        logger.info("testMongoInsert");
        reportService.testTempalteInsert();
        return "ok";
    }
    
    
    @ApiOperation(value="测试直接mongotemplate查询")	
    @RequestMapping(value="/testTemplateList",method={RequestMethod.GET,RequestMethod.POST})
    public List<Report> testMongoList() {
        logger.info("testMongoList");
        return reportService.testTempalteList();
    }
    
    
    @ApiOperation(value="测试异步任务")	
    @RequestMapping(value="/testAsyncTask",method={RequestMethod.GET,RequestMethod.POST})
    public String testAsyncTask() {
        logger.info("testAsyncTask");
        
        this.asyncTask.executeAsyncTask(System.currentTimeMillis());
        return "ok";
    }
    
}