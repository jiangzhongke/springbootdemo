package com.bootdemo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="my")
public class MyConfig {
    private String name;
    private Integer port;
    private List<String> servers = new ArrayList<String>();

    public String geName(){
        return this.name;
    }

    public Integer gePort(){
        return this.port;
    }
    public List<String> getServers() {
        return this.servers;
    }
}