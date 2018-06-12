
package com.skycloud.auth.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


/**
 * The class Token store config.
 *
 */
@Configuration
public class TokenStoreConfig {




	/**
	 * Jwt token store token store.
	 *
	 * @return the token store
	 */
	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	/**
	 * Jwt access token converter jwt access token converter.
	 *
	 * @return the jwt access token converter
	 */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("jwt");
		return converter;
	}

	/**
	 * Jwt token enhancer token enhancer.
	 *
	 * @return the token enhancer
	 */
	@Bean
	@ConditionalOnBean(TokenEnhancer.class)
	public TokenEnhancer jwtTokenEnhancer() {
		return new TokenJwtEnhancer();
	}

}
