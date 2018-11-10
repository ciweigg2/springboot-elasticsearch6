package com.elasticsearch.springbootelasticsearch6.controller;

import com.alibaba.fastjson.JSON;
import com.elasticsearch.springbootelasticsearch6.entity.EsBlog;
import com.elasticsearch.springbootelasticsearch6.model.Blog;
import com.elasticsearch.springbootelasticsearch6.repository.EsBlogRepository;
import com.elasticsearch.springbootelasticsearch6.service.BlogService;
import com.elasticsearch.springbootelasticsearch6.service.EsBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {
	
	@Autowired
    private EsBlogService esBlogService;

	@Autowired
    private BlogService blogService;

	@Autowired
    private EsBlogRepository esBlogRepository;
	 
    @GetMapping
    public Page<EsBlog> listEsBlogs(
            @RequestParam(value="order",required=false,defaultValue="new") String order,
            @RequestParam(value="keyword",required=false,defaultValue="" ) String keyword,
            @RequestParam(value="async",required=false) boolean async,
            @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
            @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize) {

        Page<EsBlog> page = null;
        List<EsBlog> list = null;
        try {
            if (order.equals("hot")) { // 最热查询
                Sort sort = new Sort(Direction.DESC,"readSize","commentSize","voteSize","createTime");
                Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
                page = esBlogService.listHotestEsBlogs(keyword, pageable);
            } else if (order.equals("new")) { // 最新查询
                Sort sort = new Sort(Direction.DESC,"createTime");
                Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
                page = esBlogService.listNewestEsBlogs(keyword, pageable);
            }

        } catch (Exception e) {
            Pageable pageable = PageRequest.of(pageIndex, pageSize);
            page = esBlogService.listEsBlogs(pageable);
        }  

        list = page.getContent();   // 当前所在页面数据列表

        return page;
    }

    @GetMapping(value = "/saveOrUpdate")
    @ResponseBody
    public String saveOrUpdate(Blog blog){
        blogService.saveBlog(blog);
        return "更新成功啦";
    }

    /**
     * 中文分词测试
     *
     * @param tags 需要分词的参数
     * @return
     */
    @GetMapping(value = "/ik")
    public List<EsBlog> ik(String tags){
        return esBlogRepository.findEsBlogByTags(tags);
    }

}
