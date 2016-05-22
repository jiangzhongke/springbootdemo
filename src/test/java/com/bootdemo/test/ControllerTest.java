package com.bootdemo.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hamcrest.Matchers;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bootdemo.app.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration // 使用@WebIntegrationTest注解需要将@WebAppConfiguration注释掉
@WebIntegrationTest("server.port:0")// 使用0表示端口号随机，也可以具体指定如8888这样的固定端口
public class ControllerTest {

    private String dateReg;
    private Pattern pattern;
    private RestTemplate template = new TestRestTemplate();
    @Value("${local.server.port}")// 注入端口号
    private int port;

    @Rule
    // 这里注意，使用@Rule注解必须要用public
    public OutputCapture capture = new OutputCapture();

    
    @Test
    public void testGetReport(){
        String url = "http://localhost:"+port+"/report/getreport/test2";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(); 
        //String result = template.postForObject(url, map, String.class);
        String result = template.getForObject(url,String.class);
        System.out.println(result);
        assertNotNull(result);
        System.out.println(capture.toString());
        assertThat(capture.toString(), Matchers.containsString("\"title\":\"test2\""));

    }

}

