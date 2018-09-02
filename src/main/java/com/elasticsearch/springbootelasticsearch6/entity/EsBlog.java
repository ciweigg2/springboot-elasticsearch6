package com.elasticsearch.springbootelasticsearch6.entity;

import com.elasticsearch.springbootelasticsearch6.model.Blog;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p class="detail">
 * 功能:
 * </p>
 *
 * @author Ciwei
 * @ClassName Es blog.
 * @Version V1.0.
 * @date 2018.08.22 21:40:19
 */
@Document(indexName = "blog", type = "blog")
@Data
public class EsBlog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id  // 主键
	private String id;
	@Field(type = FieldType.Long)
	private Long blogId; // Blog 实体的 id
	@Field(type = FieldType.Text)
	private String title;
	@Field(type = FieldType.Text)
	private String summary;
	@Field(type = FieldType.Text)
	private String content;
	@Field( type = FieldType.Date, format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss") //新增的时间格式化
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8") //返回的时间格式化
	private Date createTime;
	@Field(type = FieldType.Integer,index = false)  // 不做全文检索字段
	private Integer readSize = 0; // 访问量、阅读量
	@Field(type = FieldType.Integer,index = false)  // 不做全文检索字段
	private Integer commentSize = 0;  // 评论量
	@Field(type = FieldType.Integer,index = false)  // 不做全文检索字段
	private Integer voteSize = 0;  // 点赞量
	@Field(type = FieldType.Text,fielddata = true, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word" ,store = true)
	private String tags;  // 标签

	/**
	 * 懒得设置值了 在这边写了
	 * @param blog
	 */
	public EsBlog(Blog blog){
		this.blogId = blog.getBlogId();
		this.title = blog.getTitle();
		this.summary = blog.getSummary();
		this.content = blog.getContent();
		this.createTime = new Date();
		this.readSize = blog.getReadSize();
		this.commentSize = blog.getCommentSize();
		this.voteSize = blog.getVoteSize();
		this.tags = blog.getTags();
	}

}
