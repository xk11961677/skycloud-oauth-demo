package com.skycloud.admin.entity;

import com.skycloud.admin.common.entity.BasicEntity;
import com.skycloud.admin.common.util.SpringUtils;
import com.skycloud.admin.service.PermissionService;
import lombok.Data;

/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
@Data
public class PermissionEntity extends BasicEntity {
	private static final long serialVersionUID = 1L;
	
	/**
	 *
	 */
	private Integer id;
	/**
	 *资源名称
	 */
	private String name;
	/**
	 *资源编码
	 */
	private String code;
	/**
	 *图标
	 */
	private String icon;
	/**
	 *资源类型:1.菜单 2.按钮 3.tab页面 4.banner权限
	 */
	private String type;
	/**
	 *链接
	 */
	private String url;
	/**
	 *父类id
	 */
	private String parentId;
	/**
	 *是否叶子节点
	 */
	private Integer leaf;
	

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
        return PermissionEntity.class.getName();
    }
	
	/**
	 * 获取service数据操作类型
	 */
	@Override
    public PermissionService service() {
        return (PermissionService) SpringUtils.getBean("permissionServiceImpl");
    }
	
	/*user customize code start*/

	/*user customize code end*/
}

