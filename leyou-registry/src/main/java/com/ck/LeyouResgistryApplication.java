package com.ck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author CK
 * @create 2020-02-08-10:05
 */
@SpringBootApplication
@EnableEurekaServer
public class LeyouResgistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeyouResgistryApplication.class);
    }

}
