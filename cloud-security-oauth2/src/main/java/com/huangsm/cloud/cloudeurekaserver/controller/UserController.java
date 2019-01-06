package com.huangsm.cloud.cloudeurekaserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 得到用户信息
 * @author huangsm
 * @version V1.0
 */
@RestController
@Slf4j
public class UserController {
    @GetMapping("/user")
    public Principal user(Principal user){
        log.info(user.getName());
        return user;
    }
}
