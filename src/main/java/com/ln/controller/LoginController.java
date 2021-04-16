package com.ln.controller;

import com.ln.entity.UserBean;
import com.ln.service.LoginService;
import com.ln.util.ResultMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/7 16:13
 */
@Controller
@RequestMapping("/us")
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping("/goLogin")
    @ResponseBody
    public ResultMsg goLogin(@RequestBody UserBean userBean, HttpSession session){
        UserBean userBean1 = loginService.goLogin(userBean);
        if (userBean1!=null){
            session.setAttribute("user",userBean1);
            // 查询这个用户的所有权限
            Set<String> urls =loginService.findAllQuanXian(userBean1.getId());
            session.setAttribute("urls",urls);
           return new ResultMsg(0,"登陆成功！");
        }else{
            return new ResultMsg(1,"账号或密码错误,请重新登陆！");
        }

    }

    @RequestMapping("/getUserInSession")
    public UserBean getUserInSession(HttpSession session){
        UserBean user = (UserBean) session.getAttribute("user");
        return user;
    }
}
