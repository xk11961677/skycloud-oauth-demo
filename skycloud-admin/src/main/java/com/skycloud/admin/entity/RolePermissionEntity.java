package com.skycloud.admin.entity;

import com.skycloud.admin.common.entity.BasicEntity;
import com.skycloud.admin.service.RolePermissionService;
import com.skycloud.admin.common.util.SpringUtils;
import lombok.Data;

/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
@Data
public class RolePermissionEntity extends BasicEntity {
	private static final long serialVersionUID = 1L;
	
	/**
	 *
	 */
	private Integer id;
	/**
	 *
	 */
	private Integer roleId;
	/**
	 *
	 */
	private Integer permissionId;
	

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
        return RolePermissionEntity.class.getName();
    }
	
	/**
	 * 获取service数据操作类型
	 */
	@Override
    public RolePermissionService service() {
        return (RolePermissionService) SpringUtils.getBean("rolePermissionServiceImpl");
    }
	
	/*user customize code start*/

	/*user customize code end*/
}

