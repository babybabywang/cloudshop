package com.huangsm.cloud.cloudeurekaserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang
 * @PACKAGE_NAME com.huangsm.cloud.cloudeurekaserver.controller
 * @PROJECT_NAME cloudshop
 * @date 2019/1/3
 */
@RestController
public class UserController {
    /**
     * 根据用户名查询用户密码
     * @param username 用户名
     * @return 密码
     */
    @GetMapping("/password/{username}")
    public String getUser(@PathVariable("username") String username){
        return username;
    }

}
