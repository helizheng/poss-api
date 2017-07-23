package com.joenee.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.joenee.common.enums.PossErrorEnum;
import com.joenee.common.mapper.SysRoleMapper;
import com.joenee.common.mapper.SysRoleResourceMapper;
import com.joenee.common.mapper.SysUserMapper;
import com.joenee.common.model.SysRole;
import com.joenee.common.model.SysRoleResource;
import com.joenee.common.model.SysUser;
import com.joenee.common.service.SysRoleService;
import com.joenee.common.shiro.MyRealm;
import com.joenee.common.util.BaseResData;
import com.joenee.common.util.LoggerUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询所有角色 - 分页
     *
     * @param page
     * @param sysRole
     * @return
     */
    public BaseResData<Page<SysRole>> queryRoleForPage(Page page, SysRole sysRole) {
        BaseResData<Page<SysRole>> resd = new BaseResData<Page<SysRole>>();
        try {
            List<SysRole> sysRoles = sysRoleMapper.selectPage(page, null);
            page.setRecords(sysRoles);
            resd.setData(page);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    public BaseResData<Boolean> deleteRole(Integer id) {
        BaseResData<Boolean> resd = new BaseResData<Boolean>();
        try {
            sysRoleMapper.deleteById(id);
            sysRoleResourceMapper.deleteById(id);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 修改角色
     *
     * @param sysRole
     * @return
     */
    public BaseResData<Boolean> updateRole(SysRole sysRole) {
        BaseResData<Boolean> resd = new BaseResData<Boolean>();
        try {
            sysRoleMapper.updateById(sysRole);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 新增角色
     *
     * @param sysRole
     * @return
     */
    public BaseResData<Long> saveRole(SysRole sysRole) {
        BaseResData<Long> resd = new BaseResData<Long>();
        try {
            sysRoleMapper.insert(sysRole);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 修改角色拥有的菜单资源
     *
     * @param ids    资源ID字符串  比如1,2,3,4
     * @param roleId 角色ID
     * @return
     */
    public BaseResData saveRoleResource(String ids, Integer roleId) {
        BaseResData resd = new BaseResData();
        try {
            //首先清除缓存
            RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
            MyRealm userRealm = (MyRealm)securityManager.getRealms().iterator().next();
            userRealm.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
            //业务操作
            SysRoleResource roleResource = new SysRoleResource();
            roleResource.setRoleId(roleId);
            sysRoleResourceMapper.deleteById(roleId);
            if(StringUtils.isNotEmpty(ids)){
                String[] id = ids.split(",");
                for (int i = 0; i < id.length; i++) {
                    Integer resId = Integer.parseInt(id[i]);
                    roleResource.setResourceId(resId);
                    sysRoleResourceMapper.insert(roleResource);
                }
            }
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 未被选择的角色
     *
     * @param sysUser
     * @return
     */
    public List<SysRole> queryNotSelectRoleById(SysUser sysUser) {
        List<SysRole> list = null;
        try {
            list = sysUserMapper.queryNotSelectRoleById(sysUser);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * 根据ID查询角色信息
     *
     * @param id
     * @return
     */
    public SysRole queryRoleById(Integer id) {
        SysRole sysRole = null;
        try {
            sysRole = sysRoleMapper.selectById(id);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(), e);
        }
        return sysRole;
    }
}
