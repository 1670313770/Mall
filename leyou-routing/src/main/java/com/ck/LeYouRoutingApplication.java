package com.ck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author CK
 * @create 2020-02-08-10:21
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class LeYouRoutingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeYouRoutingApplication.class);
    }


}
