package com.skycloud.auth.web;


import com.skycloud.common.base.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping("/login")
    public ResponseData<OAuth2AccessToken> login(@RequestParam String username,@RequestParam String password){
        try {
            logger.info("login  start ......");
            Map<String,String> parameters = new HashMap<>();
            parameters.put("username",username);
            parameters.put("password",password);
            parameters.put("grant_type","password");//设置授权类型为密码模式
            Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("admin"));//此处不能为空
            Authentication authentication = new UsernamePasswordAuthenticationToken("webapp", "webapp",grantedAuthorities);
            ResponseEntity<OAuth2AccessToken> responseEntity= tokenEndpoint.postAccessToken(authentication ,parameters);
            logger.info("login  end ......");
            return  ResponseData.getSuccessResult(responseEntity.getBody());
        }catch (InvalidGrantException e){
            logger.error("login error  用户名密码不正确 ....",e);
            return ResponseData.getFailureResult("用户名密码不正确");
        }
        catch (Exception e){
            logger.error("login error ....",e);
            return ResponseData.getFailureResult(e.getMessage());
        }
    }


    @GetMapping("/logouting")
    public ResponseData<String> logouting(String accessToken){
        try {
            logger.info("logout  start ......");
            consumerTokenServices.revokeToken(accessToken);
            logger.info("logout  end ......");
            return ResponseData.getSuccessResult("退出登入成功");
        }
        catch (Exception e){
            logger.error("logout error ....",e);
            return ResponseData.getFailureResult(e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseData<String> test(){

        return ResponseData.getSuccessResult("String");
    }
}
