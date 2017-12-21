package com.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.StringTokenizer;

@RefreshScope
@RestController
public class TestController {

    @Value("${sb-test.ribbon.NFLoadBalancerRuleClassName}")
    private String rule;

    @GetMapping("xxx")
    public String xxx() {
//        String agent = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("user-agent");
//        System.out.println(agent);
//        StringTokenizer st = new StringTokenizer(agent,";");
//        st.nextToken();
//        String userbrowser = st.nextToken();
//        System.out.println(userbrowser);
//        String useros = st.nextToken();
//        System.out.println(useros);
//        return agent;
        return this.rule;
    }
}
