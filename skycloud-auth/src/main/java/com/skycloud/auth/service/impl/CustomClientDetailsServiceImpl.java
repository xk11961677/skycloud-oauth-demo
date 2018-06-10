package com.skycloud.auth.service.impl;

import com.skycloud.auth.model.domain.ClientDetailsDO;
import com.skycloud.auth.service.CustomClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 */

@Service
@Slf4j
public class CustomClientDetailsServiceImpl implements CustomClientDetailsService {


    @Override
    public ClientDetailsDO findByClientId(String clientId) {
        ClientDetailsDO clientDetailsDO = new ClientDetailsDO();
        if("webapp".equals(clientId)) {
            build(clientDetailsDO);
        }else if("user".equals(clientId)) {
            assembleUser(clientDetailsDO);
        }else if("gateway".equals(clientId)) {
            assembleGetway(clientDetailsDO);
        }else if("admin".equals(clientId)) {
            buildAdmin(clientDetailsDO);
        }else {
            log.info("client not found");
        }
        return clientDetailsDO;
    }

    @Override
    public List<ClientDetailsDO> findAll(ClientDetailsDO clientDetails) {
        List<ClientDetailsDO> clientDetailsDOs = new ArrayList();
        return clientDetailsDOs;
    }

    private void build(ClientDetailsDO clientDetailsDO) {
        clientDetailsDO.setClientId("webapp");
        clientDetailsDO.setScope("all");
        clientDetailsDO.setClientSecret("webapp");
        clientDetailsDO.setAccessTokenValiditySeconds(3600);
        clientDetailsDO.setRefreshTokenValiditySeconds(3600);
        clientDetailsDO.setAuthorizedGrantTypes("password,authorization_code,client_credentials,implicit,refresh_token");
    }

    private void buildAdmin(ClientDetailsDO clientDetailsDO) {
        System.out.println("========================================admin");
//        clientDetailsDO.setResourceIds("admin");
        clientDetailsDO.setClientId("admin");
        clientDetailsDO.setScope("all");
        clientDetailsDO.setClientSecret("123456");
        clientDetailsDO.setAccessTokenValiditySeconds(3600);
        clientDetailsDO.setRefreshTokenValiditySeconds(3600);
        clientDetailsDO.setAuthorizedGrantTypes("password,authorization_code,client_credentials,implicit,refresh_token");
    }


    private void assembleUser(ClientDetailsDO clientDetailsDO) {
        System.out.println("========================================user");
//        clientDetailsDO.setResourceIds("user");
        clientDetailsDO.setClientId("user");
        clientDetailsDO.setScope("all");
        clientDetailsDO.setClientSecret("123456");
        clientDetailsDO.setAccessTokenValiditySeconds(3600);
        clientDetailsDO.setRefreshTokenValiditySeconds(3600);
        clientDetailsDO.setAuthorizedGrantTypes("password,authorization_code,client_credentials,implicit,refresh_token");
    }


    private void assembleGetway(ClientDetailsDO clientDetailsDO) {
        System.out.println("========================================user");
//        clientDetailsDO.setResourceIds("user");
        clientDetailsDO.setClientId("gateway");
        clientDetailsDO.setScope("all");
        clientDetailsDO.setClientSecret("123456");
        clientDetailsDO.setAccessTokenValiditySeconds(3600);
        clientDetailsDO.setRefreshTokenValiditySeconds(3600);
        clientDetailsDO.setAuthorizedGrantTypes("password,authorization_code,client_credentials,implicit,refresh_token");
    }
}
