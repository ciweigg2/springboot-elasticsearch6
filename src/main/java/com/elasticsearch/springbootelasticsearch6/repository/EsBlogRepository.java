package com.elasticsearch.springbootelasticsearch6.repository;

import com.elasticsearch.springbootelasticsearch6.entity.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


/**
 * <p class="detail">
 * 功能:Blog 存储库.
 * </p>
 *
 * @author Ciwei
 * @ClassName Es blog repository.
 * @Version V1.0.
 * @date 2018.08.21 23:17:59
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {

	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 *
	 * @param title    :
	 * @param Summary  :
	 * @param content  :
	 * @param tags     :
	 * @param pageable :
	 * @return page
	 * @author Ciwei
	 * @date 2018.08.21 23:18:00
	 */
	Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(String title, String Summary, String content, String tags, Pageable pageable);

	/**
	 * <p class="detail">
	 * 功能:
	 * </p>
	 *
	 * @param blogId :
	 * @return es blog
	 * @author Ciwei
	 * @date 2018.08.21 23:18:00
	 */
	EsBlog findByBlogId(Long blogId);

	List<EsBlog> findEsBlogByTags(String tags);
}
