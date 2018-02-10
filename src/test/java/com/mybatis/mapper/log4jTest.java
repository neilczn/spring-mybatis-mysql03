/**
 * 
 */
package com.mybatis.mapper;

import org.apache.log4j.Logger;

/**
 * @author asus
 *
 */
public class log4jTest {

	/**
	 * 
	 */
	public log4jTest() {
		
	}
	
	 public static void main(String[] args) {
		 final Logger log = Logger.getLogger(log4jTest.class);
		 log.info("hello this is log4j info log");
	 }
	

}
