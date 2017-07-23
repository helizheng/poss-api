package com.joenee.common.web.servlet;

import com.joenee.common.model.SysUser;
import com.joenee.common.service.SysResourceService;
import com.joenee.common.util.BaseResData;
import com.joenee.common.util.TreeObject;
import com.joenee.common.util.TreeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * SysIndexServlet
 *
 * @author Li zheng
 * @description
 * @date 2016/4/20
 */
@Controller
@RequestMapping("/system/index")
public class SysIndexServlet {
    @Autowired
    private SysResourceService sysResourceService;

    @RequestMapping
    public String toIndex(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        SysUser user = (SysUser) session.getAttribute("APP_USER");
        BaseResData<List<TreeObject>> resd = sysResourceService.queryUserResourceById(user.getId());
        if(resd.getData() != null && resd.getData().size() > 0){
            TreeUtil treeUtil = new TreeUtil();
            List<TreeObject> ns = treeUtil.getChildTreeObjects(resd.getData(), 0);
            model.addAttribute("list",ns);
        }
        return "index";
    }

    public static void main(String[] args) {
        System.out.println(Math.sin(45));
        System.out.println(Math.sin(Math.PI*45/180));
    }
}
