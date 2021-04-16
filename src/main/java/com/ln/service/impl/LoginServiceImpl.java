package com.ln.service.impl;

import com.ln.entity.UserBean;
import com.ln.mapper.LoginMapper;
import com.ln.service.LoginService;
import com.ln.util.MD5key;
import com.ln.util.ResultMsg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/13 19:04
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public UserBean goLogin(UserBean userBean) {
        if (userBean!=null){
            List<UserBean> userBeans =loginMapper.goLogin(userBean);
            if (userBeans!=null&&userBeans.size()==1){
                // 开始进行加密匹配
                UserBean user = userBeans.get(0);
                // 从数据中查找出加盐的字符串和前台用户输入的密码进行拼接
                String pwdString = user.getPwdsalt()+userBean.getPwd()+user.getPwdsalt();
                // 用拼接后的字符串进行MD5加密---再和存在数据库的密码进行匹配---找到进行返回
                String newPwd = new MD5key().getkeyBeanofStr(pwdString);
                System.out.println("拼接的密码："+pwdString);
                System.out.println("md5:"+newPwd);
                if (newPwd.equals(user.getPwd())){
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public Set<String> findAllQuanXian(Long id) {

        return loginMapper.findAllQuanXian(id);
    }
}
