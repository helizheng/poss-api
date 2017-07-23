package com.joenee.common.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.joenee.common.model.SysRole;
import com.joenee.common.model.SysUser;
import com.joenee.common.util.BaseResData;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 查询所有角色 - 分页
     * @param page
     * @param sysRole
     * @return
     */
    BaseResData<Page<SysRole>> queryRoleForPage(Page page,SysRole sysRole);

    /**
     * 删除角色
     * @param id
     * @return
     */
    BaseResData<Boolean> deleteRole(Integer id);

    /**
     * 修改角色
     * @param sysRole
     * @return
     */
    BaseResData<Boolean> updateRole(SysRole sysRole);

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    BaseResData<Long> saveRole(SysRole sysRole);

    /**
     * 修改角色拥有的菜单资源
     * @param ids 资源ID字符串  比如1,2,3,4
     * @param roleId 角色ID
     * @return
     */
    BaseResData saveRoleResource(String ids,Integer roleId);

    /**
     * 未被选择的角色
     * @param sysUser
     * @return
     */
    List<SysRole> queryNotSelectRoleById(SysUser sysUser);

    /**
     * 根据ID查询角色信息
     * @param id
     * @return
     */
    SysRole queryRoleById(Integer id);
}
