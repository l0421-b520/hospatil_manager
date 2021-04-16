package com.ln.service;

import com.ln.entity.MeunBean;
import com.ln.util.PageUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MenuService {
    List<MeunBean> getMeunList(HttpSession session);

    String saveSelect(MeunBean meunBean);

    String deleteByid(Long pid);

    PageUtil<MeunBean> getMenus(Integer pid, Integer pageNum, Integer pageSize);
}
