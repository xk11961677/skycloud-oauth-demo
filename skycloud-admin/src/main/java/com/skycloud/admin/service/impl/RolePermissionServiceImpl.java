package com.skycloud.admin.service.impl;

import com.skycloud.admin.common.service.impl.BaseServiceImpl;
import com.skycloud.admin.entity.RolePermissionEntity;
import com.skycloud.admin.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
* 描述：</b><br>
* @author：系统生成
* @version:1.0
*/
@Repository
@Slf4j
public class RolePermissionServiceImpl extends BaseServiceImpl implements RolePermissionService {


   @Override
   public RolePermissionEntity get(Integer id){
       RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
       rolePermissionEntity.setId(id);
       return (RolePermissionEntity)get(rolePermissionEntity);
   }


   /*user customize code start*/

   /*user customize code end*/
}
