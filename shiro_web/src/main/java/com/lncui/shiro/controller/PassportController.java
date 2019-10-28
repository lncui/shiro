package com.lncui.shiro.controller;

import com.lncui.shiro.model.SysUser;
import com.lncui.shiro.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/passport")
public class PassportController extends BaseController {

    //进入登录页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/signin")
    public String submitLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam(value = "remember", required = false) String remember,
                              Model model) {
        log.info("用户登录操作----->账号：{},密码：{}",username,password);

         //错误信息
         String errMsg = null;

        //判断当前的用户是否已经被认证. 即是否已经登录.
        // 调动 Subject 的 isAuthenticated()

        //判断账号密码不能为空
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {

            //获取当前的 Subject. 调用 SecurityUtils.getSubject();
            Subject currentUser = SecurityUtils.getSubject();

            //把用户名和密码封装为UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            //设置记住我
            if (remember != null) {
                if (remember.equals("on")) {
                    //说明选择了记住我
                    token.setRememberMe(true);
                } else {
                    token.setRememberMe(false);
                }
            } else {
                token.setRememberMe(false);
            }
            try {
                // 执行登录.
                currentUser.login(token);
                //登录成功跳转list页面
                return "redirect:/passport/index";
            } catch (UnknownAccountException uae) {// 若没有指定的账户, 则 shiro 将会抛出 UnknownAccountException 异常.
                log.info("---->" + token.getPrincipal() + "账户不存在");
                errMsg = "账户不存在";
            } catch (IncorrectCredentialsException ice) { // 若账户存在, 但密码不匹配, 则 shiro 会抛出 IncorrectCredentialsException 异常。
                log.info("----> " + token.getPrincipal() + " 账户密码不匹配");
                errMsg = "账户密码不匹配";
            } catch (LockedAccountException lae) {// 用户被锁定的异常 LockedAccountException
                log.info("账户 " + token.getPrincipal() + " 被锁定，请联系管理员.");
                errMsg = "账户被锁定，请联系管理员";
            } catch (Exception ex) {
                log.info("未知错误，错误信息" + ex.getMessage());
                errMsg = "未知错误，错误信息" + ex.getMessage();

            }
        } else {
            errMsg = "请输入账号和密码";
        }
        model.addAttribute("errMsg", errMsg);
        return "login";
    }


    /*
  * 登录成功跳转的页面
  *
  * */
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        SysUser user = (SysUser) currentUser.getPrincipal();
        if(user!= null){
            currentUser.getSession().setAttribute("activeUser",user);
        }
        return "page/list";
    }


    /*
    * 退出操作
    * */
    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model){
        log.info("-------进入退出操作----------");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    /*
    * 角色拦截
    * */
    @RequiresRoles(value = {"role:root", "role:admin"}, logical = Logical.OR)
    @RequestMapping("/Test")
    @ResponseBody
    public void Test() {
        System.out.println("==========Test==============");
    }

    /*
    * 资源权限拦截
    * */
    @RequiresPermissions(value = {"users", "user:add"}, logical = Logical.OR)
    @RequestMapping("/testPermissions")
    @ResponseBody
    public void testPermissions() {
        System.out.println("=========testPermissions===============");
    }

}
