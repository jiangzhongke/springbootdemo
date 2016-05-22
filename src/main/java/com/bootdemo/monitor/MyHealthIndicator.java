package com.bootdemo.monitor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator implements HealthIndicator {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public Health health() {		
		return Health.up().build();
	}
	
	@Autowired
	private HealthAggregator healthAggregator;
	@Bean
	public HealthIndicator dbCountHealthIndicator(Collection<JdbcTemplate> repositories) {
	  CompositeHealthIndicator compositeHealthIndicator = new
	          CompositeHealthIndicator(healthAggregator);
	  for (JdbcTemplate repository: repositories) {
	      String name = repository.getClass().getSimpleName();
	      compositeHealthIndicator.addHealthIndicator(name, new MyHealthIndicator());
	  }
	  return compositeHealthIndicator;
	}	
}
