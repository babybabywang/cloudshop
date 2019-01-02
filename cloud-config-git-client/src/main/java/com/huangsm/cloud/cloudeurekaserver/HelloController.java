package com.huangsm.cloud.cloudeurekaserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang
 * @PACKAGE_NAME com.huangsm.cloud.cloudeurekaserver
 * @PROJECT_NAME cloudshop
 * @date 2019/1/2
 */
@RestController
public class HelloController {
    @Value("${name}")
    private String name;

    @GetMapping("/hello")
    public String hello(){
        return name;
    }
}
