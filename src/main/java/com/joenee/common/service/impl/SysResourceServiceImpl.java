package com.joenee.common.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.joenee.common.enums.PossErrorEnum;
import com.joenee.common.mapper.SysResourceMapper;
import com.joenee.common.mapper.SysUserMapper;
import com.joenee.common.model.SysResource;
import com.joenee.common.service.SysResourceService;
import com.joenee.common.util.BaseResData;
import com.joenee.common.util.LoggerUtil;
import com.joenee.common.util.TreeObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {
    public static Logger log = Logger.getLogger(SysResourceServiceImpl.class);
    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询资源列表
     *
     * @param sysResource
     * @return
     */
    public BaseResData<List<TreeObject>> queryResource(SysResource sysResource) {
        BaseResData<List<TreeObject>> resd = new BaseResData<List<TreeObject>>();
        try {
            List<TreeObject> list = sysUserMapper.queryResource(sysResource);
            resd.setData(list);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 删除菜单资源
     *
     * @param id
     * @return
     */
    public BaseResData<Boolean> deleteResource(Integer id) {
        BaseResData<Boolean> resd = new BaseResData<Boolean>();
        try {
            sysResourceMapper.deleteById(id);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 修改菜单资源
     *
     * @param sysResource
     * @return
     */
    public BaseResData<Boolean> updateResource(SysResource sysResource) {
        BaseResData<Boolean> resd = new BaseResData<Boolean>();
        try {
            sysResourceMapper.updateById(sysResource);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 新增菜单资源
     *
     * @param sysResource
     * @return
     */
    public BaseResData<Long> saveResource(SysResource sysResource) {
        BaseResData<Long> resd = new BaseResData<Long>();
        try {
            sysResourceMapper.insert(sysResource);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 根据用户ID查询该用户权限菜单
     *
     * @param userId
     * @return
     */
    public BaseResData<List<TreeObject>> queryUserResourceById(Integer userId) {
        BaseResData<List<TreeObject>> resd = new BaseResData<List<TreeObject>>();
        try {
            List<TreeObject> list = sysUserMapper.queryUserResourceById(userId);
            resd.setData(list);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 根据资源ID查询资源信息
     *
     * @param id
     * @return
     */
    public BaseResData<SysResource> queryResourceById(Integer id) {
        BaseResData<SysResource> resd = new BaseResData<SysResource>();
        try {
            SysResource sysResource = sysResourceMapper.selectById(id);
            resd.setData(sysResource);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 根据角色ID查询权限菜单
     *
     * @param roleId
     * @return
     */
    public BaseResData<List<TreeObject>> queryRoleResourceById(Integer roleId) {
        BaseResData<List<TreeObject>> resd = new BaseResData<List<TreeObject>>();
        try {
            List<TreeObject> list = sysUserMapper.queryRoleResourceById(roleId);
            resd.setData(list);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }
}
