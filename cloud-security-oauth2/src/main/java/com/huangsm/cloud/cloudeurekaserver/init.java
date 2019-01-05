package com.huangsm.cloud.cloudeurekaserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动服务初始化
 *
 * @author huangsm
 * @version V1.0
 */
@Slf4j
@Component
public class init implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("权限管理器启动了.....");
    }
}
