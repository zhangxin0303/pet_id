package com.cqcej.web;

import com.cqcej.web.modules.app.utils.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

	@Autowired
	AppConfig config;
	
	@Test
	public void contextLoads() {
		System.out.println(config.getTencentCloudIM().generateSign("admin"));
	}
}
