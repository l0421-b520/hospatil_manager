package com.ln.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Data
public class UserBean {
    private Long id;

    private String uname;

    private String pwd;

    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String telphone;

    private String address;

    private String gender;

    private String pwdsalt;
    // 用户选择的部门
    private Long[] deptids;
    // 查询所有部门
    private List<DeptBean> dlist;

    private String username;
}