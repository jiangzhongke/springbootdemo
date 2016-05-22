package com.bootdemo.engine.jdbc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查找
	 * @return
	 */
	public Map<String,Object> find(){
		return jdbcTemplate.queryForMap("select * from city where id = 1");
	}
	
	/**
	 * 查找
	 * @return
	 */
	public Map<String,Object> find(long id){
		return jdbcTemplate.queryForMap("select * from city where id = " +id);
	}
}
