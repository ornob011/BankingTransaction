package com.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// This is the main class of the application.
@SpringBootApplication
public class BankingTransactionApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
		return applicationBuilder.sources(BankingTransactionApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(BankingTransactionApplication.class, args); // This is used to run the application.
	}
}
