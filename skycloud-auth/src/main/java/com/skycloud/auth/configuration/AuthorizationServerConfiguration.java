package com.skycloud.auth.configuration;

import com.skycloud.auth.security.SecurityClientDetailsServiceImpl;
import com.skycloud.auth.security.SecurityUserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * @author sky
 * @description
 * @create 2018-06-02 下午3:22
 **/
@Configuration
@EnableAuthorizationServer
@Slf4j
public class AuthorizationServerConfiguration  extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private RedisConnectionFactory connectionFactory;

    @Bean
    public UserDetailsService userDetailsService(){
        return new SecurityUserDetailsServiceImpl();
    }

    @Bean
    public ClientDetailsService clientDetailsService(){
        return new SecurityClientDetailsServiceImpl();
    }

//    @Bean
//    public RedisTokenStore tokenStore() {
//        return new RedisTokenStore(connectionFactory);
//    }

    //配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false)
                .userDetailsService(userDetailsService())//若无，refresh_token会有UserDetailsService is required错误
                //   .tokenStore(tokenStore());
                .tokenStore(new InMemoryTokenStore());
    }

    //对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    //配置OAuth2的客户端相关信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }
}
