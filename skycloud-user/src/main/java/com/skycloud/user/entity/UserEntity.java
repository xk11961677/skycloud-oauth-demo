package com.skycloud.user.entity;

import com.skycloud.user.common.entity.BasicEntity;
import com.skycloud.user.common.util.SpringUtils;
import com.skycloud.user.service.UserService;
import lombok.Data;

/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
@Data
public class UserEntity extends BasicEntity {
	private static final long serialVersionUID = 1L;

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
	

	/**
	 * 获取主键字段
	 */
	@Override
    public String primaryKey() {
    	if(id==null){
    		throw new IllegalArgumentException("主键为空!");
    	}
    	return id.toString();
    }
    
	/**
	 * 获取实体类名称
	 */
	@Override
    public String className() {
        return UserEntity.class.getName();
    }
	
	/**
	 * 获取service数据操作类型
	 */
	@Override
    public UserService service() {
        return (UserService) SpringUtils.getBean("userServiceImpl");
    }
	
	/*user customize code start*/

	/*user customize code end*/
}

