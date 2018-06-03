package com.skycloud.auth.model.domain;

import lombok.Data;

/**
 * @author sky
 * @description
 * @create 2018-06-02 下午8:53
 **/
@Data
public class ClientDetailsDO {

    private String clientId;

    private String clientSecret;

    private String resourceIds;

    private String scope;

    private String authorities;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String  autoApproveScopes;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private String additionalInformation;

}
