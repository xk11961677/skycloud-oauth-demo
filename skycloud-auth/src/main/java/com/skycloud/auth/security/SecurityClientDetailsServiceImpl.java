package com.skycloud.auth.security;

import com.skycloud.auth.model.domain.ClientDetailsDO;
import com.skycloud.auth.service.CustomClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**

 */
@Service
@Slf4j
public class SecurityClientDetailsServiceImpl implements ClientDetailsService {
    @Autowired
    private CustomClientDetailsService customClientDetailsService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetailsDO clientDetailsDO = customClientDetailsService.findByClientId(clientId);
        String resourceIds = clientDetailsDO.getResourceIds();
        String scopes = clientDetailsDO.getScope();
        String grantTypes = clientDetailsDO.getAuthorizedGrantTypes();
        String authorities = clientDetailsDO.getAuthorities();
        String redirectUris = clientDetailsDO.getWebServerRedirectUri();
        Integer refreshTokenValiditySeconds = clientDetailsDO.getRefreshTokenValiditySeconds();
        Integer accessTokenValiditySeconds = clientDetailsDO.getAccessTokenValiditySeconds();
        String clientSecret = clientDetailsDO.getClientSecret();
        log.info("clientDetailsDO  info : " + clientDetailsDO.toString());
//        BaseClientDetails baseClientDetails = new BaseClientDetails(clientId,resourceIds,scopes,grantTypes,authorities,redirectUris);
//        baseClientDetails.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
//        baseClientDetails.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
//        baseClientDetails.setClientSecret(clientSecret);
//        baseClientDetails.setClientId(clientId);

        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();
        builder.withClient(clientId)
                .secret(clientSecret)
//                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .authorizedGrantTypes(clientDetailsDO.getAuthorizedGrantTypes().split(","))
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
                .scopes("all");
        ClientDetailsService build = null;
        try {
            build = builder.build();
        } catch (Exception e) {
            log.error("init={}", e.getMessage(), e);
        }

        return build.loadClientByClientId(clientId);
    }
}
