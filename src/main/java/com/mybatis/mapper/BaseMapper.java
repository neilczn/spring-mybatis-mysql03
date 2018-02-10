/**
 * 
 */
package com.mybatis.mapper;

import java.util.List;

import com.mybatis.entity.BaseEntity;

/**
 * @author asus
 *
 */
public interface BaseMapper<T extends BaseEntity> {

	public T findById(Integer id);
	
	public List<T> querylist(Integer greaterThanId);

	public void add(T t);
	
}
