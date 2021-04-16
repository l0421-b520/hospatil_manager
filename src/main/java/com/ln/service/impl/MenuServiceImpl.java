package com.ln.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ln.entity.MeunBean;
import com.ln.entity.MeunBeanExample;
import com.ln.entity.UserBean;
import com.ln.mapper.MeunMapper;
import com.ln.redis.RedisUtil;
import com.ln.service.MenuService;
import com.ln.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/8 16:21
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MeunMapper meunMapper;

    @Resource
    private RedisUtil redisUtil;
    @Override
    public List<MeunBean> getMeunList(HttpSession session) {
        // 树状图后台回显
        //Long []ids = {6L,10L,8L};
        /*
        左侧树状图，拿到没有按钮的菜单---所有人登陆进来都可以看到的页面

        MeunBeanExample example = new MeunBeanExample();
        MeunBeanExample.Criteria criteria = example.createCriteria();
        criteria.andIsbuttonEqualTo(0);
        List<MeunBean> meunBeans = meunMapper.selectByExample(example);
        /*for (int i = 0; i < ids.length; i++) {
            for (MeunBean meunBean : meunBeans) {
                if (ids[i].equals(meunBean.getId())){
                    meunBean.setChecked(true);
                    break;
                }
            }
        }*/
        // 用户登陆进来后看到不同的页面
        UserBean user = (UserBean) session.getAttribute("user");
        // 根据用户id去查询用户都有哪些树形菜单
        if (user!=null){
            List<MeunBean> list =null;
            // 拿着存在redis数据库中的键 去查对应的值
            Object redisListCache = redisUtil.getObject(user.getId());
            if (redisListCache!=null){
                // 说明数据库里有 直接返回
                System.out.println("redis中有该数据，直接返回");
                list= (List<MeunBean>) redisListCache; //直接强转返回
            }else{
                // redis中没有，需要去数据库中查出来，在放到redis中
                list =meunMapper.selectMeunListById(user.getId());
                redisUtil.putObject(user.getId(),list);
                System.out.println("redis缓存中不存在，从数据库中取出，并且放入缓存");
            }
            return list;
        }
        return null;
    }


    @Override
    public PageUtil<MeunBean> getMenus(Integer pid,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        MeunBeanExample example = new MeunBeanExample();
        MeunBeanExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid.longValue());
        List<MeunBean> list = meunMapper.selectByExample(example);
        PageInfo<MeunBean> pageInfo = new PageInfo<>(list);
        Long total = pageInfo.getTotal();
        PageUtil<MeunBean> page = new PageUtil(pageNum+"",total.intValue(),pageSize+"");
        page.setList(list);
        return page;
    }

    @Override
    public String saveSelect(MeunBean meunBean) {
        if (meunBean.getId()==null){
            // 添加
            try {
                meunMapper.insertSelective(meunBean);
                return "yes";
            }catch (Exception e){
                e.printStackTrace();
                return "no";
            }
        }else {
            // 修改
            meunMapper.updateByPrimaryKeySelective(meunBean);
           return "yes";
        }
    }

    List<Long> ids=new ArrayList();
    @Override
    public String deleteByid(Long pid) {
        // 调取递归方法
        deleteMeunById(pid);
        // 删除
        for (Long id : ids) {
            meunMapper.deleteByPrimaryKey(id);
        }
        return "yes";
    }

    private void deleteMeunById(Long pid){
        ids.add(pid);
        // 先根据id进行查询
        MeunBeanExample example = new MeunBeanExample();
        MeunBeanExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);
        //查询到下级集合
        List<MeunBean> list = meunMapper.selectByExample(example);
        if (list!=null&&list.size()>=1){
            for (MeunBean meunBean : list) {
                deleteMeunById(meunBean.getId());
            }
        }
    }
}
