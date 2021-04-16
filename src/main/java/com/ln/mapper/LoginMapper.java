package com.ln.mapper;

import com.ln.entity.UserBean;

import java.util.List;
import java.util.Set;

public interface LoginMapper {

    List<UserBean> goLogin(UserBean userBean);

    Set<String> findAllQuanXian(Long id);
}
