package com.ln.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ln.entity.DeptBean;
import com.ln.entity.DeptBeanExample;
import com.ln.entity.PostBean;
import com.ln.mapper.DeptMapper;
import com.ln.mapper.PostMapper;
import com.ln.service.DeptService;
import com.ln.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/8 10:52
 */
@Service
public class DeptServieImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Resource
    private PostMapper postMapper;

    @Override
    public PageUtil<DeptBean> getAllDept(DeptBean deptBean, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        DeptBeanExample example = new DeptBeanExample();
        DeptBeanExample.Criteria criteria = example.createCriteria();
        if (deptBean!=null){
            if (deptBean.getDeptname()!=null&&deptBean.getDeptname().length()>=1){
                criteria.andDeptnameLike("%"+deptBean.getDeptname()+"%");
            }
        }
        List<DeptBean> deptBeans = deptMapper.selectByExample(example);
        PageInfo<DeptBean> pageInfo = new PageInfo<>(deptBeans);
        Long total = pageInfo.getTotal();
        PageUtil<DeptBean> page = new PageUtil(pageNum+"",total.intValue(),pageSize+"");
        page.setList(deptBeans);
        return page;
    }

    @Override
    public Map<String, Object> fpPost(Long id) {
        HashMap<String, Object> map = new HashMap<>();
        // 查询当前部门
        // 查询所有的职位
        // 查询已选择的职位
        DeptBean deptBean = deptMapper.selectByPrimaryKey(id);
        List<PostBean> postBeans = postMapper.selectByExample(null);
        List<Long> ids =deptMapper.selectPostById(id);
        map.put("deptBean",deptBean);
        map.put("postBeans",postBeans);
        map.put("ids",ids);
        return map;
    }


    @Override
    public String saveDeptPost(Long id, String[] checkedPost) {
        try {
            // 先删除已选择id
            // 在添加新的
            deptMapper.deletePostById(id);
            deptMapper.insertPostById(id,checkedPost);
            return "yes";
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }
}
