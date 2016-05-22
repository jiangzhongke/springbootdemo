package com.bootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdemo.engine.jdbc.JdbcService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(tags="城市接口")
@RequestMapping(value="/city")
public class CityController {
	
	@Autowired
	private JdbcService jdbcService;

	
	@ApiOperation(value="根据id查找城市")
	@RequestMapping(value="/find/{id}",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody Object find(@ApiParam(name="id",value="城市id") @PathVariable long id) {
		return jdbcService.find(id);
	}
	
	@ApiOperation(value="根据id查找城市")
	@RequestMapping(value="/find2/{id}")
	@ResponseBody Object find2(@ApiParam(name="id",value="城市id") @PathVariable long id) {
		return jdbcService.find(id);
	}
}
