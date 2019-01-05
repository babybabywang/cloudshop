package com.huangsm.cloud.cloudeurekaserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义认证类
 * @author huang
 * @PACKAGE_NAME com.huangsm.cloud.cloudeurekaserver.service
 * @PROJECT_NAME cloudshop
 * @date 2019/1/3
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private  Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("username:{}",s);
        String password = "123";
        String encodePs = bCryptPasswordEncoder.encode(password);
        logger.info("password:{}",encodePs);
        return new User(s,encodePs, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String password = "123";
        String encodePs = new BCryptPasswordEncoder().encode(password);
        System.out.println(encodePs);
    }
}
