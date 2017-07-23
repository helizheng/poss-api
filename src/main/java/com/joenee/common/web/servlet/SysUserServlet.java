package com.joenee.common.web.servlet;

import com.baomidou.mybatisplus.plugins.Page;
import com.joenee.common.enums.MsgEnum;
import com.joenee.common.model.SysUser;
import com.joenee.common.model.SysUserRole;
import com.joenee.common.service.SysUserService;
import com.joenee.common.util.BaseResData;
import com.joenee.common.util.PageUtil;
import com.joenee.common.util.PasswordHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


/**
 * SysUserServlet
 *
 * @author Li zheng
 * @description
 * @date 2016/4/15
 */
@Controller
@RequestMapping("/system/user/")
public class SysUserServlet extends BaseServlet{
    @Autowired
    private SysUserService sysUserService;

    @RequiresPermissions("user:list")
    @RequestMapping("list")
    public String toList(){
        return "/system/user/list";
    }

    @RequiresPermissions("user:list")
    @RequestMapping("findByPage")
    public void queryUserForPage(PageUtil pageUtil,SysUser sysUser,HttpServletResponse response){
        Page page = new Page();
        page.setCurrent(pageUtil.getPageNumber());
        page.setSize(pageUtil.getLimit());
        BaseResData<Page<SysUser>> resd = sysUserService.queryUserForPage(page, sysUser);
        super.writeprint(response, pageUtil,resd.getData());
    }

    @RequiresPermissions("user:add")
    @RequestMapping("toAdd")
    public String toAddUser(){
        return "/system/user/add";
    }

    @RequiresPermissions("user:add")
    @RequestMapping("add")
    @ResponseBody
    public BaseResData addUser(SysUser sysUser,String txtGroupsSelect){
        BaseResData<Integer> resd = new BaseResData<Integer>();
        //判断用户是否存在
        SysUser su = sysUserService.queryUserByName(sysUser.getUserName());
        if(su != null){
            resd.setSuccess(false);
            resd.setMsg(MsgEnum.USER_EXISTS.getDescription());
            return resd;
        }
        PasswordHelper passwordHelper = new PasswordHelper();
        //密码加密
        passwordHelper.encryptPassword(sysUser);
        //默认不锁定
        sysUser.setLocked(1);
        //修改用户
        resd = sysUserService.saveUser(sysUser);
        SysUserRole sur = new SysUserRole();
        sur.setUserId(resd.getData());
        //修改用户的角色
        sysUserService.saveUserRole(txtGroupsSelect, sur);
        return resd;
    }

    @RequiresPermissions("user:update")
    @RequestMapping("toEdit/{userId}")
    public String toEditUser(@PathVariable Integer userId,Model model){
        SysUser sysUser = sysUserService.queryUserById(userId);
        model.addAttribute("user", sysUser);
        return "/system/user/add";
    }

    @RequiresPermissions("user:update")
    @RequestMapping("update")
    @ResponseBody
    public BaseResData updateUser(SysUser sysUser,String txtGroupsSelect){
        //默认不锁定
        sysUser.setLocked(1);
        //修改用户
        BaseResData<Boolean> resd = sysUserService.updateUser(sysUser);
        SysUserRole sur = new SysUserRole();
        sur.setUserId(sysUser.getId());
        //修改用户的角色
        sysUserService.saveUserRole(txtGroupsSelect, sur);
        return resd;
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("delete")
    @ResponseBody
    public BaseResData deleteUser(String ids){
        String[] userIds = ids.split(",");
        BaseResData<Boolean> resd = new BaseResData<Boolean>();
        for (int i = 0; i < userIds.length; i++) {
            Integer userId = Integer.parseInt(userIds[i]);
            resd = sysUserService.deleteUser(userId);
        }
        return resd;
    }

    @RequiresPermissions("user:updatePassword")
    @RequestMapping("toUpdatePassword/{userId}")
    public String toUpdatePassword(@PathVariable Long userId,Model model){
        model.addAttribute("userId", userId);
        return "/system/user/updatePassword";
    }

    @RequiresPermissions("user:updatePassword")
    @RequestMapping(value = "updatePassword")
    @ResponseBody
    public BaseResData updatePassword(Integer userId,String oldPassword,String newPassword,String confirmPassword){
        //根据userId查询出用户基本信息
        SysUser sysUser = sysUserService.queryUserById(userId);
        //根据用户输入的老密码和查询出的用户salt和用户名判断老密码是否正确
        PasswordHelper passwordHelper = new PasswordHelper();
        boolean b = passwordHelper.equalPassword(oldPassword, sysUser);
        BaseResData<Boolean> resd = new BaseResData<Boolean>();
        if(!b){
            resd.setSuccess(false);
            resd.setMsg(MsgEnum.RAW_PASSWORD_ERROR.getDescription());
            return resd;
        }
        if(!StringUtils.isEmpty(newPassword) && !newPassword.equals(confirmPassword)){
            resd.setSuccess(false);
            resd.setMsg(MsgEnum.NEWS_OLD_PASSWORD_NOTEQUAL.getDescription());
            return resd;
        }

        //密码加密
        sysUser.setPassword(newPassword);
        passwordHelper.encryptPassword(sysUser);
        //修改密码
        SysUser su1 = new SysUser();
        su1.setId(userId);
        su1.setPassword(sysUser.getPassword());
        resd = sysUserService.updateUser(sysUser);
        return resd;
    }

    @RequiresPermissions("user:resetPassword")
    @RequestMapping(value = "resetPassword")
    @ResponseBody
    public BaseResData resetPassword(Integer userId){
        //根据userId查询出用户基本信息
        SysUser sysUser = sysUserService.queryUserById(userId);
        //重置密码
        PasswordHelper passwordHelper = new PasswordHelper();
        sysUser.setPassword(sysUser.getAccountName());
        passwordHelper.encryptPassword(sysUser);
        //修改密码
        SysUser su1 = new SysUser();
        su1.setId(userId);
        su1.setPassword(sysUser.getPassword());
        BaseResData<Boolean> resd = sysUserService.updateUser(sysUser);
        return resd;
    }
}
