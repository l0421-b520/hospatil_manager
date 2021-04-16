package com.ln.controller;

import com.ln.entity.DeptBean;
import com.ln.service.DeptService;
import com.ln.util.PageUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/8 10:51
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    @RequestMapping("/getAllDept")
    public PageUtil<DeptBean> getAllDept(@RequestBody DeptBean deptBean,
                                         @RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "3") Integer pageSize){
        return  deptService.getAllDept(deptBean,pageNum,pageSize);
    }

    @RequestMapping("/fpPost")
    public Map<String,Object> fpPost(Long id){
        return deptService.fpPost(id);
    }

    @RequestMapping("/saveDeptPost")
    public String saveDeptPost(Long id,@RequestBody String[] checkedPost){
        return  deptService.saveDeptPost(id,checkedPost);
    }

}
