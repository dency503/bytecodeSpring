package com.bytecode.bytecodeecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.bytecode.bytecodeecommerce")
public class BytecodeEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BytecodeEcommerceApplication.class, args);
	}

}