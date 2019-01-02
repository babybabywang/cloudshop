package com.huangsm.cloud.cloudeurekaserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang
 * @PACKAGE_NAME com.huangsm.cloud.cloudeurekaserver.controller
 * @PROJECT_NAME cloudshop
 * @date 2019/1/2
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable("name") String name){
        return "hello:"+name+":"+port;
    }
}
