package com.ln.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ln.entity.MeunBean;
import com.ln.entity.PostBean;
import com.ln.entity.PostBeanExample;
import com.ln.mapper.MeunMapper;
import com.ln.mapper.PostMapper;
import com.ln.service.PostService;
import com.ln.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/8 15:01
 */
@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostMapper postMapper;

    @Resource
    private MeunMapper meunMapper;

    @Override
    public PageUtil<PostBean> getPostList(PostBean postBean, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PostBeanExample example = new PostBeanExample();
        PostBeanExample.Criteria criteria = example.createCriteria();
        if (postBean!=null){
            if (postBean.getPostname()!=null&&postBean.getPostname().length()>=1){
                criteria.andPostnameLike("%"+postBean.getPostname()+"%");
            }
        }
        List<PostBean> postBeans = postMapper.selectByExample(example);
        PageInfo<PostBean> pageInfo = new PageInfo<>(postBeans);
        Long total = pageInfo.getTotal();
        PageUtil<PostBean> page = new PageUtil(pageNum+"",total.intValue(),pageSize+"");
        page.setList(postBeans);
        return page;
    }

    @Override
    public List<MeunBean> getMeunListById(Long postid) {
        // 全查菜单
        List<MeunBean> meunBeans = meunMapper.selectByExample(null);
        // 再去中间表查询当前职位所拥有的权限
        List<Long> meunids =postMapper.getMeunids(postid);
        // 判断如果职位拥有的菜单等于菜单里的id 就将树形结构设置为true
        if (meunids!=null&&meunids.size()>=1){
            for (Long meunid : meunids) {
                for (MeunBean meunBean : meunBeans) {
                    if (meunid.equals(meunBean.getId())){
                        meunBean.setChecked(true);
                        break;
                    }
                }
            }
        }
        return meunBeans;
    }

    @Override
    public String savePostMeun(Long postid, Long[] ids) {
        String msg = null;
        try {
            // 先删除职位已选择的菜单
            postMapper.deletePostMeunByPostid(postid);
            // 在添加职位菜单
            if (ids!=null&&ids.length>=1){
                postMapper.insertPostMeun(postid,ids);
                msg = "yes";
            }
            return msg;
        }catch (Exception e){
            e.printStackTrace();
            return "no";
        }
    }
}
