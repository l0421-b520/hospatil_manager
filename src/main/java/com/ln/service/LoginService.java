package com.ln.service;

import com.ln.entity.UserBean;
import com.ln.util.ResultMsg;

import java.util.Set;

public interface LoginService {
    UserBean goLogin(UserBean userBean);

    Set<String> findAllQuanXian(Long id);
}
