/**
 * 
 */
package com.mybatis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybatis.entity.Blog;
import com.mybatis.mapper.BlogMapper;
import com.mybatis.service.BlogService;

/**
 * @author asus
 *
 */
@Service
public class BlogServiceImpl implements BlogService {
	
	private BlogMapper blogMapper;
		
	/**
	 * @param blogMapper the blogMapper to set
	 */
	@Autowired
	public void setBlogMapper(BlogMapper blogMapper) {
		this.blogMapper = blogMapper;
	}

	/**
	 * 
	 */
	public BlogServiceImpl() {
	}

	/* (non-Javadoc)
	 * @see com.mybatis.service.BlogService#add(com.mybatis.entity.Blog)
	 */
	public void add(Blog blog) {
		this.blogMapper.add(blog);
		//int a = 1/0;//测试事务
	}

	public Blog findById(Integer id) {
		return this.blogMapper.findById(id);
	}

	@Override
	public List<Blog> querylist(Integer greaterThanId) {
		return this.blogMapper.querylist(greaterThanId);
	}

}
