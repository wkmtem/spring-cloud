package com.spring.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Class Name: BaseFeignClient
 * Package: com.spring.cloud.feign
 * Description: 优雅的实现微服务地址参数的转换
 * @author wkm
 * Create DateTime: 2017/12/16 上午2:16
 * Version: 1.0
 */
/** 申明Feign客户端, 并指明服务id */
@FeignClient(value = "spring-cloud")
public interface BaseFeignClient {

    /** 不能用@GetMapping组合替换 */
    @RequestMapping(value = "xxx/{id}", method = RequestMethod.GET)
    /** 只要参数是复杂对象, 即使指定了是GET, feign依然会以POST方法进行发送请求 */
    //@RequestMapping(value = "xxx", method = RequestMethod.GET)
    //xxx(@RequestParam("id")Long id, @RequestParam("username")String username);
    //xxx(@RequestParam Map<String, Object> param);
    /** @PathVariable必须指定value */
    String xxx(@PathVariable("id") String id);
}
