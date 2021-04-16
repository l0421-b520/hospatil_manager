package com.ln.service;

import com.ln.entity.MeunBean;
import com.ln.entity.PostBean;
import com.ln.util.PageUtil;

import java.util.List;

public interface PostService {

    PageUtil<PostBean> getPostList(PostBean postBean, Integer pageNum, Integer pageSize);

    List<MeunBean> getMeunListById(Long id);

    String savePostMeun(Long postid, Long[] ids);
}
