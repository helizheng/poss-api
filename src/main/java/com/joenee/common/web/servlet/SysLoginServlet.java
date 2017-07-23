package com.joenee.common.web.servlet;

import com.joenee.common.model.SysUser;
import com.joenee.common.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * SysLoginServlet
 *
 * @author Li zheng
 * @description
 * @date 2016/4/18
 */
@Controller
@RequestMapping("/system/")
public class SysLoginServlet {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        request.removeAttribute("error");
        return "/login";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String username, String password, HttpServletRequest request) {
        try {
            if (!request.getMethod().equals("POST")) {
                request.setAttribute("error", "支持POST方法提交！");
            }
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                request.setAttribute("error", "用户名或密码不能为空！");
                return "/login";
            }

            Subject user = SecurityUtils.getSubject();
            // 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
            // 认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理
            // 当以上认证成功后会向下执行,认证失败会抛出异常
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                user.login(token);
            } catch (LockedAccountException lae) {
                token.clear();
                request.setAttribute("error", "用户已经被锁定不能登录，请与管理员联系！");
                return "/login";
            } catch (ExcessiveAttemptsException e) {
                token.clear();
                request.setAttribute("error", "账号：" + username + " 登录失败次数过多,锁定10分钟!");
                return "/login";
            } catch (AuthenticationException e) {
                token.clear();
                request.setAttribute("error", "用户或密码不正确！");
                return "/login";
            }

            /*SysUser userLogin = systemUserService.queryUserByName(username);
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("APP_USER", userLogin);*/
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "登录异常，请联系管理员！");
            return "/login";
        }
        return "redirect:index";
    }


   /* @RequestMapping("/logout")
    public String logout(Model model){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyRealm userRealm = (MyRealm)securityManager.getRealms().iterator().next();
        //清除缓存
        userRealm.clearCachedAuthorizationInfo(currentUser.getPrincipals());
        return "/login";
    }*/

    /*@RequestMapping("unauthorized")
    public String unauthorized(){
        return "/unauthorized";
    }*/
}
