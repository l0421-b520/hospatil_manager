package com.ln.entity;

import lombok.Data;

import java.util.List;

@Data
public class DeptBean {
    private Long id;

    private String deptname;
    // 这个部门所拥有的的职位
    private Long[] postids;
    // 所有的职位
    private List<PostBean> plist;


}