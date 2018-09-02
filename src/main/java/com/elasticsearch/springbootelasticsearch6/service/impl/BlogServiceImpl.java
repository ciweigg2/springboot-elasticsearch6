package com.elasticsearch.springbootelasticsearch6.service.impl;

import com.elasticsearch.springbootelasticsearch6.entity.EsBlog;
import com.elasticsearch.springbootelasticsearch6.model.Blog;
import com.elasticsearch.springbootelasticsearch6.service.BlogService;
import com.elasticsearch.springbootelasticsearch6.service.EsBlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private EsBlogService esBlogService;

    /**
     * 新增博客
     *
     * @param blog 参数实体
     * @return 博客完整信息
     */
    @Override
    public Blog saveBlog(Blog blog) {
        //按照正常逻辑 应该先把数据添加到数据库 然后再保存到elasticsearch
        //省略保存数据库操作
        //查询elasticsearch是否存在当前博客数据
        EsBlog esBlog = esBlogService.getEsBlogByBlogId(blog.getBlogId());
        if (esBlog == null) {
            //不存在 新增
            esBlog = new EsBlog(blog);
        } else {
            //更新 elasticsearch是根据他的id更新的
            //忽略id更新 因为blog中没有id这个字段 会把id覆盖层null的
            BeanUtils.copyProperties(blog, esBlog ,new String[] { "id" ,"createTime"});
        }
        //保存数据
        esBlogService.updateEsBlog(esBlog);
        return blog;
    }

}
