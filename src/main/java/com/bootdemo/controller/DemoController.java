package com.bootdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdemo.amqp.SenderService;
import com.bootdemo.model.Address;
import com.bootdemo.model.User;
import com.bootdemo.service.DemoService;


@Controller
@Api(tags="演示接口")
@RequestMapping(value="/demo")
public class DemoController {

    @Autowired
    DemoService demoService;
    
    @Autowired
    SenderService senderService;

	@ApiOperation(value="接口测试1")
	@RequestMapping(value="/test",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String putCache(){
        demoService.findUser(1l,"wang","yunfei");
        demoService.findAddress(1l,"anhui","hefei");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");               
        return "ok";
    }
	
	@ApiOperation(value="接口测试2")
    @RequestMapping("/test2")
    @ResponseBody
    public String testCache(){
        User user = demoService.findUser(1l,"wang","yunfei");
        Address address =demoService.findAddress(1l,"anhui","hefei");
        System.out.println("我这里没执行查询");
        System.out.println("user:"+"/"+user.getFirstName()+"/"+user.getLastName());
        System.out.println("address:"+"/"+address.getProvince()+"/"+address.getCity());
        return "ok";
    }
	
	@ApiOperation(value="接口测试MQ")
	@RequestMapping(value="/testmq",method={RequestMethod.POST})
    @ResponseBody
    public String putMQ(@ApiParam(name="msg",value="消息") @RequestParam  String msg ){
        senderService.sendBar2Rabbitmq(msg);
        return "ok";
    }
}
