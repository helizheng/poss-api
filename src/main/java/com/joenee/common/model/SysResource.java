package com.joenee.common.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.ArrayList;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
@TableName("sys_resource")
public class SysResource extends Model<SysResource> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 资源名称
     */
	private String name;
    /**
     * 资源父ID
     */
	@TableField("parent_id")
	private Integer parentId;
    /**
     * 资源标识
     */
	@TableField("res_key")
	private String resKey;
    /**
     * 资源类型（0：菜单，1：按钮）
     */
	private Integer type;
    /**
     * 资源url
     */
	@TableField("res_url")
	private String resUrl;
    /**
     * 权限标识
     */
	private String permission;
	private Integer level;
	private String icon;
    /**
     * 是否禁用（1：正常，0：禁用）
     */
	private Integer status;
    /**
     * 描述
     */
	private String description;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;

	@TableField(exist = false)
	private List<SysResource> children = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getResKey() {
		return resKey;
	}

	public void setResKey(String resKey) {
		this.resKey = resKey;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<SysResource> getChildren() {
		return children;
	}

	public void setChildren(List<SysResource> children) {
		this.children = children;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysResource{" +
			"id=" + id +
			", name=" + name +
			", parentId=" + parentId +
			", resKey=" + resKey +
			", type=" + type +
			", resUrl=" + resUrl +
			", permission=" + permission +
			", level=" + level +
			", icon=" + icon +
			", status=" + status +
			", description=" + description +
			", createTime=" + createTime +
			"}";
	}
}
