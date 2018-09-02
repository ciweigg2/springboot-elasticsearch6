package com.elasticsearch.springbootelasticsearch6;

import com.alibaba.fastjson.JSON;
import com.elasticsearch.springbootelasticsearch6.entity.EsBlog;
import com.elasticsearch.springbootelasticsearch6.utils.ESUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootElasticsearch6ApplicationTests {

    @Autowired
    private ESUtil esUtil;

    @Test
    public void contextLoads() {

        String[] an = esUtil.getAnalyzes("blog","我是小刺猬");
        System.out.println(JSON.toJSONString(an));

        String[] su = esUtil.getSuggestion(EsBlog.class,"刺");
        System.out.println(su);

    }

}
