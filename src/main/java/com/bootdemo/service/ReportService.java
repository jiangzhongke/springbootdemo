package com.bootdemo.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bootdemo.controller.ReportController;
import com.bootdemo.model.Report;


import com.bootdemo.repo.ReportRepository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:     ReportService
 * @Description:   Mongo测试 
 * 
 * @author         tommy
 * @version        V0.1  
 * @Date           2016/5/18 16:07 
 */

@Service
public class ReportService {
	 private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

	 
    @Autowired
    private ReportRepository repository;
    
    
    @Autowired
    private MongoTemplate mongoTemplate; /*测试直接使用mongotemplate*/
    
    @Cacheable(value = "reportcache", keyGenerator = "wiselyKeyGenerator")
    public Report createReport(Map<String, Object> reportMap) {
        Report report = new Report(reportMap.get("date").toString(),
                reportMap.get("title").toString(),
                reportMap.get("content").toString());
        repository.save(report);
        return report;
    }

    @Cacheable(value = "reportcache", keyGenerator = "wiselyKeyGenerator")    
    public Report getReportDetails(String title) {
        System.out.println("无缓存的时候调用这里---数据库查询, title=" + title);
        return repository.findByTitle(title);
    }
    
    
    /**
     * 测试插入数据
     * @return
     */
    public int testTempalteInsert(){
    	int tmpret = 0;
    	try{
    	   Report report = new Report("2015-05-08 12:00:03",
                   "tempalte标题",
                   "测试内容");
    	   mongoTemplate.insert(report);
    	   
    	}catch(Exception ex){
    		logger.error(ex.getMessage());
    		tmpret = -1;
    	}
    	return tmpret;
   }
    
    /**
     * 通过mongotemplate 获取列表
     * @return
     */
    public List<Report> testTempalteList(){
    	 Query query=new Query();
    	 query.with(new Sort(new Sort.Order(Sort.Direction.ASC,"date")));
    	 List<Report> tmpreportlist = mongoTemplate.find(query, Report.class); 
    	 return tmpreportlist;
    }
}