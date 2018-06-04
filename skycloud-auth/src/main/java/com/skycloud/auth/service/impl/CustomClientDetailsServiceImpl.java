package com.skycloud.auth.service.impl;

import com.skycloud.auth.model.domain.ClientDetailsDO;
import com.skycloud.auth.service.CustomClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 */

@Service
public class CustomClientDetailsServiceImpl implements CustomClientDetailsService {


    @Override
    public ClientDetailsDO findByClientId(String clientId) {
        ClientDetailsDO clientDetailsDO = new ClientDetailsDO();
        if("webapp".equals(clientId)) {
            build(clientDetailsDO);
        }else {
            buildAdmin(clientDetailsDO);
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
        System.out.println("========================================");
        clientDetailsDO.setResourceIds("admin");
        clientDetailsDO.setClientId("admin");
        clientDetailsDO.setScope("all");
        clientDetailsDO.setClientSecret("admin");
        clientDetailsDO.setAccessTokenValiditySeconds(3600);
        clientDetailsDO.setRefreshTokenValiditySeconds(3600);
        clientDetailsDO.setAuthorizedGrantTypes("password,authorization_code,client_credentials,implicit,refresh_token");
    }
}
