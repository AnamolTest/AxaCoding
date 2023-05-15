package jp.co.axa.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages={
		"jp.co.axa.apidemo.controllers", "jp.co.axa.apidemo.entities","jp.co.axa.apidemo.jwt","jp.co.axa.apidemo.repositories","jp.co.axa.apidemo.security","jp.co.axa.apidemo.services","jp.co.axa.constant"})
public class ApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDemoApplication.class, args);
	}

}
