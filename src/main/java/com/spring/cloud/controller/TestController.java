package com.spring.cloud.controller;

import org.apache.tomcat.util.http.RequestUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.StringTokenizer;

@RestController
public class TestController {

    @GetMapping("xxx")
    public String xxx() {
        String agent = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("user-agent");
        System.out.println(agent);
        StringTokenizer st = new StringTokenizer(agent,";");
        st.nextToken();
        String userbrowser = st.nextToken();
        System.out.println(userbrowser);
        String useros = st.nextToken();
        System.out.println(useros);
        return agent;
    }
}
