package com.skycloud.auth.service;


import com.skycloud.auth.model.domain.ClientDetailsDO;

import java.util.List;

/**
 */
public interface CustomClientDetailsService {

    public ClientDetailsDO findByClientId(String clientId) ;

    public List<ClientDetailsDO> findAll(ClientDetailsDO clientDetails);
}
