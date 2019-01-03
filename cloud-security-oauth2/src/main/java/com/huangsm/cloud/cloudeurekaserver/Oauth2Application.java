package com.huangsm.cloud.cloudeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * oauth2
 * @author huangsm
 * @date 2019年1月2日 22:17:30
 */

@SpringBootApplication
@RestController
public class Oauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

}

