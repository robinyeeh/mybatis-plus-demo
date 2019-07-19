/**
 * All rights Reserved, Designed By Robin
 * Copyright: Copyright(C) 2016-2020
 * Company    Robin  Co., Ltd.
 * <p>
 * Date:2019-07-11
 */
package com.robinye.mbp;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Video服务启动类
 *
 * @author yetianbing
 * @version 1.0.0
 * @since jdk8
 */

@SpringBootApplication(scanBasePackages = "com.robinye.mbp")
@MapperScan(basePackages = "com.robinye.mbp.mapper")
@EnableAsync
@EnableDiscoveryClient
@EnableFeignClients
@EnableApolloConfig
public class MbpVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbpVideoApplication.class, args);
    }
}
