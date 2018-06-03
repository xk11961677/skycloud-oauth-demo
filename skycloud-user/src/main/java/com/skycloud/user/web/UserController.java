package com.skycloud.user.web;

import com.skycloud.api.dto.UserDTO;
import com.skycloud.common.base.BaseContextHandler;
import com.skycloud.common.base.ResponseData;
import com.skycloud.user.entity.UserEntity;
import com.skycloud.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sky
 * @description
 **/
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 根据用户名和密码获取用户
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("getUser")
    @ResponseBody
    public ResponseData<UserDTO> getUser(String username, String password) {
        String token = BaseContextHandler.getToken();
        log.info("login success "+token);
        ResponseData<UserDTO> result;
        UserDTO userDTO = null;
        UserEntity userEntity = new UserEntity();
        userEntity.setName(username);
        userEntity.setPassword(password);
        UserEntity user = (UserEntity) userService.getOne(userEntity);
        if (user != null) {
            userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
        }
        result = ResponseData.getSuccessResult(userDTO);
        return result;
    }

}
