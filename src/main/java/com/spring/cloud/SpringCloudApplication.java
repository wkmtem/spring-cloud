package com.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Class Name: SpringCloudApplication
 * Package: com.spring.cloud
 * Description: 
 * @author wkm
 * Create DateTime: 2017/12/15 下午11:24
 * Version: 1.0
 */
/** 启用Feign客户端 */
@EnableFeignClients
/** 开启容错机制 */
@EnableHystrix
/** 声明自动发现的服务端 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudApplication.class, args);
	}
}
