package com.ln.controller;

import com.ln.entity.UserBean;
import com.ln.entity.UserVo;
import com.ln.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/6 13:35
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/getUserList")
    public List<UserVo> getUserList(){
        return userService.getUserList();
    }

    @RequestMapping("/findBuMenMsg")
    public Map<String, Object> findBuMenMsg(Integer id){
        return userService.findBuMenMsg(id);
    }

    @RequestMapping("/saveUserDept")
    public String saveUserDept(Long id,@RequestBody String[] deptids){
        return userService.saveUserDept(id,deptids);
    }

    @RequestMapping("/fpPost")
    public UserBean fpPost(Integer id){
        return userService.fpPost(id);
    }

    @RequestMapping("/saveUserPost")
    public String saveUserPost(@RequestBody UserBean userBean){
        return userService.saveUserPost(userBean);
    }

}
