package com.lncui.shiro.controller;

import com.lncui.shiro.model.SysUser;
import com.lncui.shiro.vo.SysUserVO;
import com.lncui.shiro.service.SysUserService;
import com.lncui.shiro.util.PasswordUtil;
import com.zhlh.Tiny.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    /*
    * 用户添加操作
    * */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(SysUserVO vo) {
        try {
            vo.setPassword(PasswordUtil.encrypt(vo.getPassword(), vo.getUsername()));
            SysUser sysUser = new SysUser();
            BeanUtil.copy(vo, sysUser);
            sysUserService.insert(sysUser);
            return buildSuccJson();
        } catch (Exception e) {
            e.printStackTrace();
            return buildErrJson("error");
        }
    }

}
