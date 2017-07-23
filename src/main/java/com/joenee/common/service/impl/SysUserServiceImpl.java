package com.joenee.common.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.joenee.common.enums.PossErrorEnum;
import com.joenee.common.mapper.SysUserMapper;
import com.joenee.common.mapper.SysUserRoleMapper;
import com.joenee.common.model.SysResource;
import com.joenee.common.model.SysRole;
import com.joenee.common.model.SysUser;
import com.joenee.common.model.SysUserRole;
import com.joenee.common.service.SysUserService;
import com.joenee.common.util.BaseResData;
import com.joenee.common.util.LoggerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author helizheng
 * @since 2017-07-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 查询用户列表 - 分页
     *
     * @param page
     * @param sysUser
     * @return
     */
    public BaseResData<Page<SysUser>> queryUserForPage(Page page, SysUser sysUser) {
        BaseResData<Page<SysUser>> resd = new BaseResData<Page<SysUser>>();
        try {
            if(StringUtils.isEmpty(sysUser.getAccountName())){
                sysUser.setAccountName(null);
            }
            List<SysUser> sysUsers = sysUserMapper.selectPage(page, new EntityWrapper<SysUser>(sysUser)
                    .like("user_name", sysUser.getUserName()));
            page.setRecords(sysUsers);
            resd.setData(page);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public BaseResData<Boolean> deleteUser(Integer id) {
        BaseResData<Boolean> resd = new BaseResData<Boolean>();
        try {
            //删除用户
            sysUserMapper.deleteById(id);
            //删除用户角色中间表数据
            sysUserRoleMapper.deleteById(id);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 修改用户
     *
     * @param sysUser
     * @return
     */
    public BaseResData<Boolean> updateUser(SysUser sysUser) {
        BaseResData<Boolean> resd = new BaseResData<Boolean>();
        try {
            sysUserMapper.updateById(sysUser);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(), e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     */
    public BaseResData<Integer> saveUser(SysUser sysUser) {
        BaseResData<Integer> resd = new BaseResData<Integer>();
        try {
            Integer insert = sysUserMapper.insert(sysUser);
            resd.setData(insert);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(),e);
            resd.setSuccess(false);
            resd.setMsg(PossErrorEnum._SYS_ERROR.getMessage());
        }
        return resd;
    }

    /**
     * 新增用户拥有的角色
     * @param ids
     * @param sysUserRole
     * @return
     */
    public BaseResData<Long> saveUserRole(String ids,SysUserRole sysUserRole) {
        BaseResData<Long> resd = new BaseResData<Long>();
        try {
            if(StringUtils.isNotEmpty(ids)){
                //删除之前的关系
                sysUserRoleMapper.deleteById(sysUserRole.getUserId());
                //保存现在的关系
                String[] roleIds = ids.split(",");
                for (int i = 0; i < roleIds.length; i++) {
                    sysUserRole.setRoleId(Integer.parseInt(roleIds[i]));
                    sysUserRoleMapper.insert(sysUserRole);
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
     * 根据用户ID查询用户信息
     *
     * @param id
     * @return
     */
    public SysUser queryUserById(Integer id) {
        SysUser user = null;
        try {
            user = sysUserMapper.selectById(id);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     * 根据用户名称查询用户信息
     *
     * @param userName
     * @return
     */
    public SysUser queryUserByName(String userName) {
        SysUser user = new SysUser();
        try {
            user.setAccountName(userName);
            user = sysUserMapper.selectOne(user);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     * 根据用户名称查询该用户拥有的角色
     *
     * @param userName
     * @return
     */
    public Set<String> queryUserRoleByName(String userName) {
        Set<String> set = null;
        try {
            List<SysRole> list = sysUserMapper.queryUserRoleByName(userName);
            if(list != null && list.size() > 0){
                set = new HashSet<String>();
            }
            for(SysRole role : list){
                set.add(role.getName());
            }
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(), e);
        }
        return set;
    }

    /**
     * 根据用户ID查询该用户拥有的角色
     *
     * @param id
     * @return
     */
    public List<SysRole> queryUserRoleById(Integer id) {
        List<SysRole> list = null;
        try {
            list = sysUserMapper.queryUserRoleById(id);
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * 根据用户名称查询该用户拥有的菜单资源
     *
     * @param userName
     * @return
     */
    public Set<String> queryUserResourceByName(String userName) {
        Set<String> set = null;
        try {
            List<SysResource> list = sysUserMapper.queryUserResourceByName(userName);
            if(list != null && list.size() > 0){
                set = new HashSet<String>();
            }
            for(SysResource resource : list){
                set.add(resource.getPermission());
            }
        } catch (Exception e) {
            LoggerUtil.log.error(e.getMessage(), e);
        }
        return set;
    }

}
