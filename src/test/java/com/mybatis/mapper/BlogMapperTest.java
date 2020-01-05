package com.mybatis.mapper;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.entity.Blog;
import com.mybatis.service.BlogService;
import com.mybatis.util.MybatisUtil;


public class BlogMapperTest {

	private ApplicationContext ac;
	private BlogMapper blogMapper;
	private BlogService blogService;
	
	@Before
	public void setUp() throws Exception {
		ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml"});
		System.out.println(ac);
	}
	
	/**
	 * 测试mybatis的分页查询
	 */
	@Test
	public void testQueryList(){
		BlogMapper blogMapper = (BlogMapper)ac.getBean("blogMapper");
		List<Blog> list1 =  blogMapper.querylist(0);
		System.out.println(list1);
		//获取第1页，10条内容，默认查询总数count
		PageHelper.startPage(2, 3);
		List<Blog> list2 =  blogMapper.querylist(3);
		for (Blog blog : list2) {
			System.out.println(blog);
		}
		//用PageInfo对结果进行包装
		PageInfo page = new PageInfo(list2);
		System.out.println(page);
	}
	
	/**
	 * 测试slf4j
	 */
	@Test
	public void testLog(){
		System.out.println(BlogMapperTest.class);
		Logger log = LoggerFactory.getLogger(BlogMapperTest.class);
		log.info("hello, my name is {}", "chengyi");
	}
	
	/**
	 * 测试添加以及事务
	 */
	@Test
	public void testadd4(){
		try {
			/*blogMapper = (BlogMapper) ac.getBean("blogMapper");
			blogMapper.add(blog);*/
			
			Blog blog = new Blog(8,"test8",6);
			blogService = (BlogService)ac.getBean("blogServiceImpl");
			blogService.add(blog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * spring整合mybatis
	 */
	@Test
	public void testFindById3() {
		blogMapper = (BlogMapper) ac.getBean("blogMapper");
		Blog blog = blogMapper.findById(3000);
		System.out.println(blog);
	}
	
	/**
	 * 测试导入spring包后数据库连接
	 */
	@Test
	public void testFindById2() {
		DataSource dataSource = (DataSource)ac.getBean("comboPooledDataSourceID");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String title = jdbcTemplate.queryForObject("select title from blog where id =1", String.class);
		System.out.println(title);	
	}

	/**
	 * 单独测试mybatis
	 */
	@Test
	public void testFindById1() {
		
		SqlSession sqlSession = null;
		try{
			sqlSession = MybatisUtil.getSqlSession();			
			BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
			Blog blog = blogMapper.findById(1);
			System.out.println(blog);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			MybatisUtil.closeSqlSession();
		}
		
	}

}
