package com.joenee.common.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.joenee.common.model.SysRole;
import com.joenee.common.model.SysUser;
import com.joenee.common.model.SysUserRole;
import com.joenee.common.util.BaseResData;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 查询用户列表 - 分页
     * @param page
     * @param sysUser
     * @return
     */
    BaseResData<Page<SysUser>> queryUserForPage(Page page,SysUser sysUser);

    /**
     * 删除用户
     * @param id
     * @return
     */
    BaseResData<Boolean> deleteUser(Integer id);

    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    BaseResData<Boolean> updateUser(SysUser sysUser);

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    BaseResData<Integer> saveUser(SysUser sysUser);

    /**
     * 新增用户拥有的角色
     * @param ids
     * @param sysUserRole
     * @return
     */
    BaseResData<Long> saveUserRole(String ids,SysUserRole sysUserRole);

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    SysUser queryUserById(Integer id);

    /**
     * 根据用户名称查询用户信息
     * @param userName
     * @return
     */
    SysUser queryUserByName(String userName);

    /**
     * 根据用户名称查询该用户拥有的角色
     * @param userName
     * @return
     */
    Set<String> queryUserRoleByName(String userName);

    /**
     * 根据用户ID查询该用户拥有的角色
     * @param id
     * @return
     */
    List<SysRole> queryUserRoleById(Integer id);

    /**
     * 根据用户名称查询该用户拥有的菜单资源
     * @param userName
     * @return
     */
    Set<String> queryUserResourceByName(String userName);
}
