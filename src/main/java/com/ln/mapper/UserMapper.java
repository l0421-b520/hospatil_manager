package com.ln.mapper;

import com.ln.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserBeanExample example);

    int deleteByExample(UserBeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBean record);

    int insertSelective(UserBean record);

    List<UserBean> selectByExample(UserBeanExample example);

    UserBean selectByPrimaryKey(Long id);

    int updateByExampleSelective(UserBean record, UserBeanExample example);

    int updateByExample(UserBean record, UserBeanExample example);

    int updateByPrimaryKeySelective(UserBean record);

    int updateByPrimaryKey(UserBean record);

    List<DeptBean> getSelecBuMentById(Integer id);

    void deleteBuMenById(Long id);

    void saveUserDept(@Param("id") Long id, @Param("array") String[] deptids);

    void deletePostById(Long id);

    List<UserVo> getUserList();

    List<PostBean> deptPostByIds(Integer userDeptId);

    List<Integer> getSelecDepttById(Integer id);

    void insertUserPost(@Param("userid") Long userid, @Param("postid") Long postid);
}