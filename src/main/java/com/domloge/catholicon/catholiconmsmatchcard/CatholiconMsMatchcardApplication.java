package com.domloge.catholicon.catholiconmsmatchcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.domloge"})
public class CatholiconMsMatchcardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatholiconMsMatchcardApplication.class, args);
	}

}

