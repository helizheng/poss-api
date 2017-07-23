package com.joenee.common.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
@TableName("sys_role_resource")
public class SysRoleResource extends Model<SysRoleResource> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId("role_id")
	private Integer roleId;
    /**
     * 资源id
     */
	@TableField("resource_id")
	private Integer resourceId;


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	protected Serializable pkVal() {
		return this.roleId;
	}

	@Override
	public String toString() {
		return "SysRoleResource{" +
			"roleId=" + roleId +
			", resourceId=" + resourceId +
			"}";
	}
}
