package com.huangsm.cloud.cloudeurekaserver.hystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author huang
 * @PACKAGE_NAME com.huangsm.cloud.cloudeurekaserver.hystrix
 * @PROJECT_NAME cloudshop
 * @date 2019/1/2
 */
@FeignClient(value = "SERVICE-PROVIDER-HELLOWORLD")
public interface HelloFeign {
    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable("name") String name);
}
