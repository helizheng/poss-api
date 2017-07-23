package com.joenee.common.web.servlet;

import com.joenee.common.model.SysResource;
import com.joenee.common.service.SysResourceService;
import com.joenee.common.service.SysRoleService;
import com.joenee.common.util.BaseResData;
import com.joenee.common.util.TreeObject;
import com.joenee.common.util.TreeUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysResourceServlet
 *
 * @author Li zheng
 * @description
 * @date 2016/4/25
 */
@Controller
@RequestMapping("/system/resource/")
public class SysResourceServlet {
    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     *  跳转到菜单列表
     * @return
     */
    @RequiresPermissions("resource:list")
    @RequestMapping("list")
    public String toList() {
        return "/system/resource/list";
    }


    /**
     * 查询菜单数据
     * @return
     */
    @RequiresPermissions("resource:list")
    @RequestMapping("treelists")
    @ResponseBody
    public Map findByPage(SysResource sysResource) {
        BaseResData<List<TreeObject>> resd = sysResourceService.queryResource(sysResource);
        TreeUtil treeUtil = new TreeUtil();
        List<TreeObject> ns = treeUtil.getChildTreeObjects(resd.getData(), 0);
        Map map = new HashMap();
        map.put("treelists", ns);
        return map;
    }

    /**
     * 菜单树结构
     * @return
     */
    @RequiresPermissions("resource:list")
    @RequestMapping("reslists")
    @ResponseBody
    public List<TreeObject> reslists() {
        BaseResData<List<TreeObject>> resd = sysResourceService.queryResource(null);
        TreeUtil treeUtil = new TreeUtil();
        List<TreeObject> ns = treeUtil.getChildTreeObjects(resd.getData(), 0, "　");
        return ns;
    }


    @RequiresPermissions("resource:add")
    @RequestMapping("toAdd")
    public String toAdd(){
        return "/system/resource/add";
    }

    @RequiresPermissions("resource:add")
    @RequestMapping("add")
    @ResponseBody
    public BaseResData addResource(SysResource sysResource){
        BaseResData<Long> resd = sysResourceService.saveResource(sysResource);
        return resd;
    }

    @RequiresPermissions("resource:update")
    @RequestMapping("toEdit/{id}")
    public String toEdit(@PathVariable Integer id,Model model){
        BaseResData<SysResource> resd = sysResourceService.queryResourceById(id);
        model.addAttribute("resource", resd.getData());
        return "/system/resource/add";
    }

    @RequiresPermissions("resource:update")
    @RequestMapping("update")
    @ResponseBody
    public BaseResData<Boolean> updateResource(SysResource sysResource){
        BaseResData<Boolean> resd = sysResourceService.updateResource(sysResource);
        return resd;
    }

    @RequiresPermissions("resource:delete")
    @RequestMapping("delete")
    @ResponseBody
    public BaseResData deleteResource(String ids){
        String[] userIds = ids.split(",");
        BaseResData<Boolean> resd = new BaseResData<Boolean>();
        for (int i = 0; i < userIds.length; i++) {
            Integer userId = Integer.parseInt(userIds[i]);
            resd = sysResourceService.deleteResource(userId);
        }
        return resd;
    }


    /**
     * 权限分配页面
     *
     * @param model
     * @return
     */
    @RequestMapping("permissions")
    public String permissions(Long roleId,Model model) {
        BaseResData<List<TreeObject>> resd = sysResourceService.queryResource(null);
        TreeUtil treeUtil = new TreeUtil();
        List<TreeObject> ns = treeUtil.getChildTreeObjects(resd.getData(), 0);
        model.addAttribute("permissions", ns);
        model.addAttribute("roleId", roleId);
        return "/system/resource/permissions";
    }

    /**
     * 根据角色ID查询该角色拥有的权限菜单
     * @param roleId
     * @return
     */
    @RequestMapping("findRes")
    @ResponseBody
    public List<TreeObject> findRes(Integer roleId){
        BaseResData<List<TreeObject>> resd = sysResourceService.queryRoleResourceById(roleId);
        return resd.getData();
    }

    /**
     * 添加角色权限菜单
     * @param roleId
     * @param request
     * @return
     */
    @RequestMapping("addRoleRes")
    @ResponseBody
    public BaseResData addRoleResource(Integer roleId,HttpServletRequest request){
        String[] resId = request.getParameterValues("resId[]");
        String str = "";
        if(resId != null){
            for (int i = 0; i < resId.length; i++) {
                str += resId[i]+",";
            }
        }

        BaseResData resd = sysRoleService.saveRoleResource(str, roleId);
        return resd;
    }
}
