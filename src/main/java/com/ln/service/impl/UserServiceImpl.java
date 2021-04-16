package com.ln.service.impl;

import com.ln.entity.*;
import com.ln.mapper.DeptMapper;
import com.ln.mapper.MeunMapper;
import com.ln.mapper.UserMapper;
import com.ln.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/6 13:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private DeptMapper deptMapper;

    @Resource
    private MeunMapper meunMapper;
    @Override
    public List<UserVo> getUserList() {

        return userMapper.getUserList();
    }

    @Override
    public Map<String, Object> findBuMenMsg(Integer id) {
        // 查询个人信息
        UserBean userBean = userMapper.selectByPrimaryKey(id.longValue());
        // 查询所有部门信息
        List<DeptBean> deptBeans = deptMapper.selectByExample(null);
        // 查询已选择的部门
        List<Integer> ids=userMapper.getSelecDepttById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userBean",userBean);
        map.put("deptBeans",deptBeans);
        map.put("ids",ids);
        return map;
    }

    @Override
    public String saveUserDept(Long id, String[] deptids) {
        try {
            // 先删除职位，在删除部门，最后添加
            userMapper.deletePostById(id);
            userMapper.deleteBuMenById(id);
            userMapper.saveUserDept(id,deptids);
            return "yes";
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }

    @Override
    public UserBean fpPost(Integer id) {
        // 先查询当前对象
        UserBean userBean = userMapper.selectByPrimaryKey(id.longValue());
        // 在查询这个对象有没有部门
        List<DeptBean> deptBeans = userMapper.getSelecBuMentById(id);
        // 遍历这个部门里有没有职位
        if (deptBeans!=null&&deptBeans.size()>=1){
            for (DeptBean deptBean : deptBeans) {
                // 用部门的id去查询这个部门所拥有的的职位的集合
                List<PostBean> postBeans =deptMapper.getPostListByDeptid(deptBean.getId());
                // 再查询用户在这个部门下所选择的职位
                Long[] postids =deptMapper.getUserAndPostAndDeptById(deptBean.getId(),id);
                deptBean.setPlist(postBeans);
                deptBean.setPostids(postids);
            }
        }
        userBean.setDlist(deptBeans);
        return userBean;
    }

    @Override
    public String saveUserPost(UserBean userBean) {
        /**
         * 判断当前对象是否为空
         */
        if (userBean!=null){
            // 先删除用户所选择的职位
            userMapper.deletePostById(userBean.getId());
            // 判断部门集合是否为空
            if (userBean.getDlist()!=null&&userBean.getDlist().size()>=1){
                for (DeptBean deptBean : userBean.getDlist()) {
                    // 判断这个部门下选择的职位是否为空
                    if (deptBean.getPostids()!=null&&deptBean.getPostids().length>=1){
                        // 遍历这个职位数组添加
                        for (Long postid : deptBean.getPostids()) {
                            userMapper.insertUserPost(userBean.getId(),postid);
                        }
                    }
                }
            }

        }
        return "yes";
    }


}
