package com.bootdemo.test;


import static org.junit.Assert.*;

import java.util.Hashtable;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bootdemo.app.Application;
import com.bootdemo.model.Report;
import com.bootdemo.service.ReportService;

@RunWith(SpringJUnit4ClassRunner.class)
// 1
@SpringApplicationConfiguration(classes = Application.class)
// 2
@WebAppConfiguration
// 3
@IntegrationTest("server.port:0")
// 4
public class ServiceTest {

	@Autowired
	ReportService reportService;

	// 在所有测试方法前执行一次，一般在其中写上整体初始化的代码
	@BeforeClass
	public static void beforeClass() {
		
	};

	//在所有测试方法后执行一次，一般在其中写上销毁和释放资源的代码 
	@AfterClass
	public static void afterClass() {
		
	};

	// 在每个测试方法后执行，在方法执行完成后要做的事情
	@Before
	public void before() {

	}

	// 测试方法执行超过1000毫秒后算超时，测试将失败
	@After
	public void after() {

	}

	@Test
	public void createReport() {
		Map<String, Object> reportMap = new Hashtable<String, Object>();
		reportMap.put("title", "test2");
		reportMap.put("content", "test title");
		reportMap.put("date", "2016/05/19");
		Report tmpreport1 = reportService.createReport(reportMap);
		assertTrue(tmpreport1 != null);
	}

	@Test
	public void getReport() {
		Report tmpreport1 = reportService.getReportDetails("test2");
		assertTrue(tmpreport1 != null);
	}
}
