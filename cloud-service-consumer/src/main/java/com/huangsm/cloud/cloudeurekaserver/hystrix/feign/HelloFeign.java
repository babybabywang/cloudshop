package com.huangsm.cloud.cloudeurekaserver.hystrix.feign;

import com.huangsm.cloud.cloudeurekaserver.hystrix.HelloHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author huang
 * @PACKAGE_NAME com.huangsm.cloud.cloudeurekaserver.hystrix
 * @PROJECT_NAME cloudshop
 * @date 2019/1/2
 */
@FeignClient(value = "SERVICE-PROVIDER-HELLOWORLD",fallback = HelloHystrix.class)
@Component
public interface HelloFeign {
    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable("name") String name);

    @GetMapping("/password/{username}")
    public String getUser(@PathVariable("username") String username);
}
