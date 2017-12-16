package com.spring.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.cloud.feign.BaseFeignClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * Class Name: RestTemplateService
 * Package: com.spring.cloud.service
 * Description: spring RESTful Http 请求
 * @author wkm
 * Create DateTime: 2017/12/14 上午4:08
 * Version: 1.0
 */
@Service
//@AllArgsConstructor
//@NoArgsConstructor
public class RestTemplateService {

    /** RestTemplate底层默认的JDK实现 */
    @Autowired
    private RestTemplate restTemplate;
    /** RestTemplate底层支持的okHttp3实现 */
    @Autowired
    private RestTemplate okHttp3Client;
    /** OkHttp3独立的实现的Client */
    @Autowired
    private OkHttpClient okHttpClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    /** Feign客户端 */
    @Autowired
    private BaseFeignClient baseFeignClient;


    /**
     * Method Name: xxx
     * Description: rpc调用
     * Create DateTime: 2017/12/15 下午11:26
     * @return
     */
    /** 配置容错机制, 指定fallbackMethod容错方法 */
    /** 默认阀值: 5秒内的20次故障, 自动断开, 不再进行通讯 */
    @HystrixCommand(fallbackMethod = "xxxFallbackMethod")
    public String xxx (String id) {
        /** Client的application.name */
        //String serviceId = "spring-cloud";

        /*List<ServiceInstance> serviceInstanceList = this.discoveryClient.getInstances(applicationName);
        if (CollectionUtils.isEmpty(serviceInstanceList)) {
            return null;
        }
        ServiceInstance serviceInstance = serviceInstanceList.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        return host + port;*/
        //return this.okHttp3Client.getForObject("http://" + host + ":" + port, String.class);
        /** 通过Ribbon负载均衡(默认轮询)面向服务调用远程服务id, 自动替换为ip:port */
        /** getForObject直接对json的返回值进行反序列化 */
        //return this.okHttp3Client.getForObject("http://" + serviceId + "/" + id, String.class);
        /** 通过Feign客户端实现 */
        return this.baseFeignClient.xxx(id);
    }

    /** 容错方法，参数、返回值需与rpc调用方法一致 */
    public String xxxFallbackMethod(String id) {
        return null;
    }


    /**
     * Method Name: restHttp
     * Description: RestTemplate 底层 JDK 的实现
     * Create DateTime: 2017/12/15 下午11:32
     * @return
     */
    public String restHttp () {
        return this.restTemplate.getForObject(
                "http://139.196.195.162:8081/sso/register/check?account=compass",
                String.class);
    }

    /**
     * Method Name: okHttp3
     * Description: RestTemplate 底层 okHttp3 的实现
     * Create DateTime: 2017/12/15 下午11:33
     * @return
     */
    public String okHttp3 () {
        return this.okHttp3Client.getForObject(
                "http://139.196.195.162:8081/sso/register/check?account=compass",
                String.class);
    }

    /**
     * Method Name: okHttp
     * Description: OkHttpClient 独立的实现
     * Create DateTime: 2017/12/15 下午11:33
     * @return
     */
    public String okHttp () {
        Response response = null;
        try {
            String url = "http://139.196.195.162:8081/sso/register/check?account=compass";
            Request request = new Request.Builder().url(url).build();
            response = this.okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(response.body() != null){
                /** 一定要关闭，不然会泄露资源 */
                response.body().close();
            }
        }
        return null;
    }
}
