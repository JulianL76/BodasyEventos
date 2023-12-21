package com.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//import com.app.web.services.IUploadFileService;

@SpringBootApplication
@ComponentScan(basePackages = "com.app.web")
public class Bodas1Application {

	@Autowired
	//private IUploadFileService uploadFileService;

	public static void main(String[] args) {
		SpringApplication.run(Bodas1Application.class, args);
	}

}
