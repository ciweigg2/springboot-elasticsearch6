package com.elasticsearch.springbootelasticsearch6.service;


import com.elasticsearch.springbootelasticsearch6.entity.EsBlog;
import com.elasticsearch.springbootelasticsearch6.vo.TagVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p class="detail">
 * 功能:
 * </p>
 *
 * @author Ciwei
 * @ClassName Es blog service.
 * @Version V1.0.
 * @date 2018.08.22 21:43:26
 */
public interface EsBlogService {

	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 *
	 * @param id :
	 * @author Ciwei
	 * @date 2018.08.22 21:43:26
	 */
	void removeEsBlog(String id);

	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 *
	 * @param esBlog :
	 * @return es blog
	 * @author Ciwei
	 * @date 2018.08.22 21:43:26
	 */
	EsBlog updateEsBlog(EsBlog esBlog);

	/**
	 * Gets es blog by blog id.
	 *
	 * @param blogId the blog id
	 * @return the es blog by blog id
	 */
	EsBlog getEsBlogByBlogId(Long blogId);
 
	Page<EsBlog> listNewestEsBlogs(String keyword, Pageable pageable);
 
	Page<EsBlog> listHotestEsBlogs(String keyword, Pageable pageable);
	
	Page<EsBlog> listEsBlogs(Pageable pageable);

	List<EsBlog> listTop5NewestEsBlogs();
	
	List<EsBlog> listTop5HotestEsBlogs();
	
	List<TagVO> listTop30Tags();

}
