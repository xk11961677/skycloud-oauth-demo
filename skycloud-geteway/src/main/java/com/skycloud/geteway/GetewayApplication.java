package com.skycloud.geteway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 应用入口
 *
 * @author sky
 */

@SpringCloudApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class GetewayApplication {


    /**
     * 入口
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(GetewayApplication.class, args);
    }


}
