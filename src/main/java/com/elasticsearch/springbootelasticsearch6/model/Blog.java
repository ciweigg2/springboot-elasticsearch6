package com.elasticsearch.springbootelasticsearch6.model;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * <p class="detail">
 * 功能:Blog 实体
 * </p>
 *
 * @author Ciwei
 * @ClassName Blog.
 * @Version V1.0.
 * @date 2018.08.19 21:20:58
 */
@Data
public class Blog implements Serializable {

	private Long blogId; //博客唯一标识

	private String title;

	private String summary;

	private String content;

	private Date createTime;

	private Integer readSize = 0; // 访问量、阅读量

	private Integer commentSize = 0;  // 评论量

	private Integer voteSize = 0;  // 点赞量

	private String tags;	//标签

}
