package com.skycloud.api.client;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 *	@author sky
 */
@EnableFeignClients(basePackageClasses=ClientConfiguration.class)
@ComponentScan(basePackageClasses = ClientConfiguration.class)
@EnableCircuitBreaker
public class ClientConfiguration {

}
