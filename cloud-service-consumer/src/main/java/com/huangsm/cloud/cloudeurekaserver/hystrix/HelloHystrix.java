package com.huangsm.cloud.cloudeurekaserver.hystrix;

import com.huangsm.cloud.cloudeurekaserver.hystrix.feign.HelloFeign;
import org.springframework.stereotype.Component;

/**
 * 熔断器
 * @author huang
 * @PACKAGE_NAME com.huangsm.cloud.cloudeurekaserver.hystrix
 * @PROJECT_NAME cloudshop
 * @date 2019/1/2
 */
@Component
public class HelloHystrix implements HelloFeign {
    @Override
    public String hello(String name) {
        return "哦~~服务短路了。。";
    }

    @Override
    public String getUser(String username) {
        return "短路了";
    }
}
