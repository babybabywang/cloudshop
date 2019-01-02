package com.huangsm.cloud.cloudeurekaserver.client;

import com.huangsm.cloud.cloudeurekaserver.hystrix.feign.HelloFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang
 * @PACKAGE_NAME com.huangsm.cloud.cloudeurekaserver.client
 * @PROJECT_NAME cloudshop
 * @date 2019/1/2
 */
@RestController
public class ConsumerController {
    @Autowired
    private HelloFeign helloFeign;

    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable("name") String name){
      return   helloFeign.hello(name);
    }
}
