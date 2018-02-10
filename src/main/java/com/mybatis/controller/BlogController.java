/**
 * 
 */
package com.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.entity.Blog;
import com.mybatis.service.BlogService;

/**
 * @author asus
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
	
	
   private BlogService blogService;
   
   @Autowired
   public void setBlogService(BlogService blogService) {
	   this.blogService = blogService;
   }

	/**
	 * 
	 */
	public BlogController() {
	}
	
	@RequestMapping("/findById")
	public @ResponseBody Blog findById(@RequestParam Integer id){
		Blog blog = blogService.findById(id);
		return blog;
	}
	
	
	@RequestMapping(value = "/add", method= {RequestMethod.POST} )
	public @ResponseBody Blog findById(@RequestBody Blog blog){
		blogService.add(blog);
		return blog;
	}
	

}
