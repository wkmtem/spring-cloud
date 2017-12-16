package com.spring.cloud;

import com.spring.cloud.service.RestTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
/** 导入配置 */
@Import(SpringCloudApplication.class)
public class SpringCloudApplicationTests {

	@Autowired
	private RestTemplateService restTemplateService;

	@Test
	public void restHttpTest() {
		System.out.println(this.restTemplateService.restHttp());
	}

	@Test
	public void okHttp3Test() {
		System.out.println(this.restTemplateService.okHttp3());
	}

	@Test
	public void okHttpTest() {
		System.out.println(this.restTemplateService.okHttp());
	}


	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Test
	public void testLoadBalance() {
		String serviceId = "spring-cloud";
		for (int i = 0; i < 100; i ++) {
			ServiceInstance serviceInstance = this.loadBalancerClient.choose(serviceId);
			System.out.println(serviceInstance.getHost() + ":" + serviceInstance.getPort());
		}
	}

}
