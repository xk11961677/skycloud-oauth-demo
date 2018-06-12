package com.skycloud.admin.web;

import com.skycloud.api.client.user.UserApi;
import com.skycloud.api.dto.UserDTO;
import com.skycloud.common.base.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sky
 **/
@RestController
@RequestMapping("permission")
@Slf4j
public class PermissionController {

    @Resource
    private UserApi userApi;

    @RequestMapping("getMenu")
    public ResponseVo<String> getMenu() {
        log.info("===========>>:{}getMenu");
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseVo<UserDTO> userDto = userApi.getUser("123", "123");
        if(StringUtils.isEmpty(userDto)) {
            UserDTO data = userDto.getData();
            log.info("===========>>:{}"+data);
        }
        return ResponseVo.getSuccessResult(name);
    }
}
