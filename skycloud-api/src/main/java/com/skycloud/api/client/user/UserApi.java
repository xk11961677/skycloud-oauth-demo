package com.skycloud.api.client.user;

import com.skycloud.api.dto.UserDTO;
import com.skycloud.common.base.ResponseVo;
import com.skycloud.common.feign.OAuth2FeignAutoConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sky
 **/
@FeignClient(name = "user" ,configuration = OAuth2FeignAutoConfiguration.class ,fallback = UserApi.UserApiFallback.class)
public interface UserApi {

    @RequestMapping(value = "/user/getUser",method = RequestMethod.GET)
    @ResponseBody
    ResponseVo<UserDTO> getUser(@RequestParam("username") String username, @RequestParam("password") String password);

    @Component
    class UserApiFallback implements UserApi {

        @Override
        public ResponseVo<UserDTO> getUser(@RequestParam("username") String username, @RequestParam("password") String password) {
            System.out.println("===========>>user client fallback:{}");
            return new ResponseVo<>();
        }
    }
}
