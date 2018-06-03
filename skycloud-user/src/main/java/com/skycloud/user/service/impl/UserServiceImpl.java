package com.skycloud.user.service.impl;

import com.skycloud.user.common.service.impl.BaseServiceImpl;
import com.skycloud.user.entity.UserEntity;
import com.skycloud.user.service.UserService;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

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
