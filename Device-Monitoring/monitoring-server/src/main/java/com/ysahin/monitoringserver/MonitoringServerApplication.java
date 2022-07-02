package com.ysahin.monitoringserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yusufSahin
 *
 */
/*
 * Monitoring server runs on 7070 port. 
 * You have to write localhost:7070 on your browser. you can see monitoring server UI. 
 */

@SpringBootApplication
public class MonitoringServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringServerApplication.class, args);
	}

}
