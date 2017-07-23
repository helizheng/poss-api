package com.joenee.common.web.servlet;

import com.baomidou.mybatisplus.plugins.Page;
import com.joenee.common.model.SysRole;
import com.joenee.common.model.SysUser;
import com.joenee.common.service.SysRoleService;
import com.joenee.common.service.SysUserService;
import com.joenee.common.util.BaseResData;
import com.joenee.common.util.CommonUtil;
import com.joenee.common.util.PageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * SysRoleServlet
 *
 * @author Li zheng
 * @description
 * @date 2016/4/22
 */
@Controller
@RequestMapping("/system/role/")
public class SysRoleServlet extends BaseServlet{
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;


    @RequiresPermissions("role:list")
    @RequestMapping("list")
    public String toList(){
        return "/system/role/list";
    }

    @RequiresPermissions("role:list")
    @RequestMapping("findByPage")
    public void queryRoleForPage(PageUtil pageUtil,SysRole sysRole,HttpServletResponse response){
        Page page = new Page();
        page.setCurrent(pageUtil.getPageNumber());
        page.setSize(pageUtil.getLimit());
        BaseResData<Page<SysRole>> resd = sysRoleService.queryRoleForPage(page, sysRole);
        super.writeprint(response,pageUtil,resd.getData());
    }

    @RequiresPermissions("role:add")
    @RequestMapping("toAdd")
    public String toAdd(){
        return "/system/role/add";
    }

    @RequiresPermissions("role:add")
    @RequestMapping("add")
    @ResponseBody
    public BaseResData addRole(SysRole sysRole){
        BaseResData<Long> resd = sysRoleService.saveRole(sysRole);
        return resd;
    }

    @RequiresPermissions("role:update")
    @RequestMapping("toEdit/{id}")
    public String toEdit(@PathVariable Integer id,Model model){
        SysRole sysRole = sysRoleService.queryRoleById(id);
        model.addAttribute("role", sysRole);
        return "/system/role/add";
    }

    @RequiresPermissions("role:update")
    @RequestMapping("update")
    @ResponseBody
    public BaseResData<Boolean> updateRole(SysRole sysRole){
        BaseResData<Boolean> resd = sysRoleService.updateRole(sysRole);
        return resd;
    }

    @RequiresPermissions("role:delete")
    @RequestMapping("delete")
    @ResponseBody
    public BaseResData deleteRole(String ids){
        String[] roleIds = ids.split(",");
        BaseResData<Boolean> resd = new BaseResData<Boolean>();
        for (int i = 0; i < roleIds.length; i++) {
            Integer roleId = Integer.parseInt(roleIds[i]);
            resd = sysRoleService.deleteRole(roleId);
        }
        return resd;
    }

    /**
     * 用户管理 - 分配用户角色时查询角色
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("roleSelect")
    public String roleSelect(Integer userId,Model model){
        List<Integer> list = new ArrayList<Integer>();
        if(null!=userId){
            //查询用户角色
            List<SysRole> userRoles = sysUserService.queryUserRoleById(userId);
            String ugid = "";
            for (SysRole role : userRoles) {
                list.add(role.getId());
                ugid += role.getId() + ",";
            }
            //去除多余字符
            ugid = CommonUtil.trimComma(ugid);
            //用户拥有的角色ID字符串
            model.addAttribute("txtRoleSelect", ugid);
            //用户拥有的角色ID列表
            model.addAttribute("userRole", userRoles);
        }
        //查询用户未拥有的角色
        SysUser sysUser = new SysUser();
        if(list.size() > 0){
            sysUser.setList(list);
        }else{
            sysUser.setList(null);
        }
        List<SysRole> roles = sysRoleService.queryNotSelectRoleById(sysUser);
        model.addAttribute("role", roles);
        return "/system/user/roleSelect";
    }


}
