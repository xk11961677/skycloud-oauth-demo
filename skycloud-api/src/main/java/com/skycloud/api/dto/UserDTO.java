package com.skycloud.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sky
 **/
@Data
public class UserDTO implements Serializable {

    /**
     *
     */
    private Integer id;
    /**
     */
    private String name;
    /**
     *用户编码
     */
    private String code;
    /**
     */
    private String mobile;
    /**
     *登录密码
     */
    private String password;
    /**
     *状态
     */
    private Integer status;
    /**
     *
     */
    private String nickName;
    /**
     *真实姓名
     */
    private String realName;

}
