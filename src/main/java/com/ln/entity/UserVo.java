package com.ln.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/10 19:32
 */
@Data
public class UserVo implements Serializable {

    private Long id;
    private String uname;
    private Integer gender;
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String telphone;
    private String address;
    private String deptname;
    private String postname;
}
