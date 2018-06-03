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
        clientDetailsDO.setClientId("webapp");
        clientDetailsDO.setScope("select,write");
        clientDetailsDO.setClientSecret("webapp");
        clientDetailsDO.setAccessTokenValiditySeconds(3600);
        clientDetailsDO.setRefreshTokenValiditySeconds(3600);
        clientDetailsDO.setAuthorizedGrantTypes("password,authorization_code,client_credentials,implicit,refresh_token");
        return clientDetailsDO;
    }

    @Override
    public List<ClientDetailsDO> findAll(ClientDetailsDO clientDetails) {
        List<ClientDetailsDO> clientDetailsDOs = new ArrayList();
        return clientDetailsDOs;
    }
}
