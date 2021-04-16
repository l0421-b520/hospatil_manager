package com.ln.controller;

import com.ln.entity.MeunBean;
import com.ln.service.MenuService;
import com.ln.util.PageUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/8 16:18
 */
@RestController
@RequestMapping("/mu")
public class MenuController {

    @Resource
    private MenuService menuService;

    @RequestMapping("/getMenuList")
    public List<MeunBean> getMenuList(HttpSession session){

        return menuService.getMeunList(session);
    }


    @RequestMapping("/getMenus")
    public PageUtil<MeunBean> getMenus(Integer pid, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize){

        return menuService.getMenus(pid,pageNum,pageSize);
    }

    @RequestMapping("/saveSelect")
    public String saveSelect(@RequestBody MeunBean meunBean){
        return menuService.saveSelect(meunBean);
    }

    @RequestMapping("/deleteByid")
    public String deleteByid(Long pid) {
        return menuService.deleteByid(pid);
    }
}
