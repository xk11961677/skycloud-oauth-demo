package com.skycloud.admin.entity;

import com.skycloud.admin.common.entity.BasicEntity;
import com.skycloud.admin.common.util.SpringUtils;
import com.skycloud.admin.service.UserRoleService;
import lombok.Data;

/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
@Data
public class UserRoleEntity extends BasicEntity {
	private static final long serialVersionUID = 1L;
	
	/**
	 *
	 */
	private Integer id;
	/**
	 *
	 */
	private Integer userId;
	/**
	 *
	 */
	private Integer roleId;
	

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
        return UserRoleEntity.class.getName();
    }
	
	/**
	 * 获取service数据操作类型
	 */
	@Override
    public UserRoleService service() {
        return (UserRoleService) SpringUtils.getBean("userRoleServiceImpl");
    }
	
	/*user customize code start*/

	/*user customize code end*/
}

