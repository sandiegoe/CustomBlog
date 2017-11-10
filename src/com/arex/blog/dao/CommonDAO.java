package com.arex.blog.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import com.arex.blog.utils.PageInfo;

public interface CommonDAO<T> {

	public void save(T t);
	
	public void saveCollection(Collection<T> collection);
	
	public T searchById(Serializable id);
	
	public List<T> searchByIds(Serializable[] ids);
	
	public void deleteById(Serializable id);
	
	public void update(T t);
	
	public List<T> searchCollectionByConditionNoPage(String hqlWhere, Object[] objects, LinkedHashMap<String, String> orderby);
	
	public void deleteByCollection(Collection<T> collection);
	
	public List<T> searchCollectionByCondition(String hqlWhere,
			final Object[] objects, LinkedHashMap<String, String> orderby, PageInfo pageInfo);
}
