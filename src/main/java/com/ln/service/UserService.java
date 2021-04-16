package com.ln.service;

import com.ln.entity.UserBean;
import com.ln.entity.UserVo;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserVo> getUserList();

    Map<String, Object> findBuMenMsg(Integer id);

    String saveUserDept(Long id, String[] deptids);

    UserBean fpPost(Integer id);

    String saveUserPost(UserBean userBean);
}
