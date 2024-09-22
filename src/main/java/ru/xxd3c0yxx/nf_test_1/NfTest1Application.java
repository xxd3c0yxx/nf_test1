package ru.xxd3c0yxx.nf_test_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NfTest1Application {

	public static void main(String[] args) {
		SpringApplication.run(NfTest1Application.class, args);
	}

}
