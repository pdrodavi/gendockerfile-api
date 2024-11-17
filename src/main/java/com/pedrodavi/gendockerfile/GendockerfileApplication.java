package com.pedrodavi.gendockerfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GendockerfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(GendockerfileApplication.class, args);
	}

}
