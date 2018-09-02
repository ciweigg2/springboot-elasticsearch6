package com.elasticsearch.springbootelasticsearch6.service;

import com.elasticsearch.springbootelasticsearch6.model.Blog;

public interface BlogService {

    /**
     * 新增博客
     * @param blog 参数实体
     * @return 博客完整信息
     */
    Blog saveBlog(Blog blog);

}
