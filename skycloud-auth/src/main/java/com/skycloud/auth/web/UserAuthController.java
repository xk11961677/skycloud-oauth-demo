package com.skycloud.auth.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 服务之间调用验证用户信息接口
 */
@RestController
@Slf4j
public class UserAuthController {

    /**
     * 各个服务获取验证授权用户信息的接口
     * @param user
     * @return
     */
    @GetMapping("/userinfo")
    public Principal user(Principal user){
        log.info("userinfo");
        return user;
    }

}
