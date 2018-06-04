package com.skycloud.admin.service.impl;

import com.skycloud.admin.common.service.impl.BaseServiceImpl;
import com.skycloud.admin.service.UserService;
import com.skycloud.admin.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
* 描述：</b><br>
* @author：系统生成
* @version:1.0
*/
@Repository
@Slf4j
public class UserServiceImpl extends BaseServiceImpl implements UserService {


   @Override
   public UserEntity get(Integer id){
       UserEntity userEntity = new UserEntity();
       userEntity.setId(id);
       return (UserEntity)get(userEntity);
   }


   /*user customize code start*/

   /*user customize code end*/
}
