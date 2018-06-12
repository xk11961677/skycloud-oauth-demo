package com.skycloud.user.web;

import com.skycloud.api.dto.UserDTO;
import com.skycloud.common.base.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sky
 * @description
 **/
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {


    /**
     * 根据用户名和密码获取用户
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("getUser")
    @ResponseBody
    public ResponseVo<UserDTO> getUser(String username, String password) {
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = new UserDTO();
        userDTO.setName("123456789");
        return ResponseVo.getSuccessResult(userDTO);
    }

}
