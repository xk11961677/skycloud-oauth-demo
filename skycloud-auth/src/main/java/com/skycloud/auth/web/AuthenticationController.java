package com.skycloud.auth.web;

import com.skycloud.auth.model.domain.UserDO;
import com.skycloud.common.base.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashSet;
import java.util.Objects;

/**
 * @date 2018年03月10日
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

//    @Autowired
//    private TokenStore tokenStore;

    @Autowired
    private TokenStore jwtTokenStore;

    /**
     * 认证页面
     *
     * @return ModelAndView
     */
    @GetMapping("/require")
    public ModelAndView require() {
        return new ModelAndView("ftl/login");
    }

    /**
     * 用户信息校验
     *
     * @return 用户信息
     */
    @RequestMapping("/user")
    public UserDO user() {
        HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        OAuth2Authentication authentication = (OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDO userDO = null;
        if(authentication.isClientOnly()) {
            //TODO  根据client 与scope查询client是否存在,存在则返回信息
            userDO = new UserDO(1, Objects.toString(principal), Objects.toString(principal), authorities, true);
        }else {
            if (principal instanceof UserDetails) {
                userDO = new UserDO(1, "system", "123456", authorities, true);
                return userDO;
            }
            if (principal instanceof Principal) {
                userDO = new UserDO(1, ((Principal) principal).getName(), "123456", authorities, true);
                return userDO;
            }
        }
        return userDO;
//        return authentication.getPrincipal();
    }

    /**
     *
     * @param accesstoken accesstoken
     * @return true/false
     */
    @PostMapping("/layout")
    public ResponseVo removeToken(String accesstoken) {
        OAuth2AccessToken accessToken = jwtTokenStore.readAccessToken(accesstoken);
        boolean flag = consumerTokenServices.revokeToken(accessToken.getValue());
        jwtTokenStore.removeAccessToken(accessToken);
//        tokenStore.removeRefreshToken(accessToken.getRefreshToken());
        return ResponseVo.getSuccessResult(flag);
    }
}