package com.ln.service;

import com.ln.entity.DeptBean;
import com.ln.util.PageUtil;

import java.util.Map;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/8 10:52
 */
public interface DeptService {

    PageUtil<DeptBean> getAllDept(DeptBean deptBean, Integer pageNum, Integer pageSize);

    Map<String, Object> fpPost(Long id);

    String saveDeptPost(Long id, String[] checkedPost);
}
