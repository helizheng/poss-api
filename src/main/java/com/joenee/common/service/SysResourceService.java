package com.joenee.common.service;

import com.baomidou.mybatisplus.service.IService;
import com.joenee.common.model.SysResource;
import com.joenee.common.util.BaseResData;
import com.joenee.common.util.TreeObject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
public interface SysResourceService extends IService<SysResource> {
    /**
     * 查询资源列表
     * @param sysResource
     * @return
     */
    BaseResData<List<TreeObject>> queryResource(SysResource sysResource);

    /**
     * 删除菜单资源
     * @param id
     * @return
     */
    BaseResData<Boolean> deleteResource(Integer id);

    /**
     * 修改菜单资源
     * @param sysResource
     * @return
     */
    BaseResData<Boolean> updateResource(SysResource sysResource);

    /**
     * 新增菜单资源
     * @param sysResource
     * @return
     */
    BaseResData<Long> saveResource(SysResource sysResource);

    /**
     * 根据用户ID查询该用户权限菜单
     * @param userId
     * @return
     */
    BaseResData<List<TreeObject>> queryUserResourceById(Integer userId);

    /**
     * 根据资源ID查询资源信息
     * @param id
     * @return
     */
    BaseResData<SysResource> queryResourceById(Integer id);

    /**
     * 根据角色ID查询权限菜单
     * @param roleId
     * @return
     */
    BaseResData<List<TreeObject>> queryRoleResourceById(Integer roleId);
}
