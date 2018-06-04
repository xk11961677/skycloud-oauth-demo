package com.skycloud.admin.service.impl;

import com.skycloud.admin.common.service.impl.BaseServiceImpl;
import com.skycloud.admin.entity.UserRoleEntity;
import com.skycloud.admin.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
* 描述：</b><br>
* @author：系统生成
* @version:1.0
*/
@Repository
@Slf4j
public class UserRoleServiceImpl extends BaseServiceImpl implements UserRoleService {


   @Override
   public UserRoleEntity get(Integer id){
       UserRoleEntity userRoleEntity = new UserRoleEntity();
       userRoleEntity.setId(id);
       return (UserRoleEntity)get(userRoleEntity);
   }


   /*user customize code start*/

   /*user customize code end*/
}
