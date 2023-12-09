package com.bytecode.bytecodeecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
@SpringBootApplication

public class BytecodeEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BytecodeEcommerceApplication.class, args);
	}

}
