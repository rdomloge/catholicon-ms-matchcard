package com.domloge.catholicon.catholiconmsmatchcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(basePackages= {"com.domloge"})
@EntityScan("com.domloge.catholiconmsmatchcardlibrary")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
public class CatholiconMsMatchcardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatholiconMsMatchcardApplication.class, args);
	}

}