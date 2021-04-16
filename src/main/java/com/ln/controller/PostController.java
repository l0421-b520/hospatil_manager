package com.ln.controller;

import com.ln.entity.MeunBean;
import com.ln.entity.PostBean;
import com.ln.service.PostService;
import com.ln.util.PageUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/8 14:57
 */
@RestController
@RequestMapping("/po")
public class PostController {

    @Resource
    private PostService postService;

    @RequestMapping("/getPostList")
    public PageUtil<PostBean> getPostList(@RequestBody PostBean postBean, @RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue = "5")Integer pageSize){
        return postService.getPostList(postBean,pageNum,pageSize);
    }

    @RequestMapping("/getMeunListById")
    public List<MeunBean> getMeunListById(Long id){
        return postService.getMeunListById(id);
    }

    @RequestMapping ("/savePostMeun")
    public String savePostMeun(Long postid,@RequestBody Long[] ids){
        return postService.savePostMeun(postid,ids);
    }
}
