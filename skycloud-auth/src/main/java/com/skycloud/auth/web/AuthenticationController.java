package com.skycloud.auth.web;

import com.skycloud.auth.model.domain.UserDO;
import com.skycloud.common.base.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDO userDO = null;
        HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        if (principal instanceof UserDetails) {
//            return ((UserDetails) principal).getUsername();

            userDO = new UserDO(1, "system", "123456", authorities, true);
            return userDO;
        }

        if (principal instanceof Principal) {
            userDO = new UserDO(1, ((Principal) principal).getName(), "123456", authorities, true);
            return userDO;
        }
        return userDO;
//        return authentication.getPrincipal();
    }

    /**
     * 清除Redis中 accesstoken refreshtoken
     *
     * @param accesstoken accesstoken
     * @return true/false
     */
    @PostMapping("/removeToken")
    public ResponseData removeToken(String accesstoken) {
        boolean flag = consumerTokenServices.revokeToken(accesstoken);
        return ResponseData.getSuccessResult(flag);
    }
}