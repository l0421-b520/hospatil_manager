package com.ln.mapper;

import com.ln.entity.MeunBean;
import com.ln.entity.MeunBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface MeunMapper {
    long countByExample(MeunBeanExample example);

    int deleteByExample(MeunBeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MeunBean record);

    int insertSelective(MeunBean record);

    List<MeunBean> selectByExample(MeunBeanExample example);

    MeunBean selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MeunBean record, @Param("example") MeunBeanExample example);

    int updateByExample(@Param("record") MeunBean record, @Param("example") MeunBeanExample example);

    int updateByPrimaryKeySelective(MeunBean record);

    int updateByPrimaryKey(MeunBean record);

    List<MeunBean> selectMeunListById(Long id);

    Set<String> findAllquanXian(Long id);
}