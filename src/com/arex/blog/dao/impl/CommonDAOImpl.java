package com.arex.blog.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.arex.blog.dao.CommonDAO;
import com.arex.blog.utils.GenericUtils;
import com.arex.blog.utils.PageInfo;

@Component(value = "commonDAOImpl")
@Transactional
public class CommonDAOImpl<T> implements CommonDAO<T> {

	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	@Override
	public void save(T t) {
		hibernateTemplate.save(t);
	}

	@Override
	public T searchById(Serializable id) {
		Class clazz = GenericUtils.getActualClass(this.getClass());
		return (T) hibernateTemplate.get(clazz, id);
	}

	@Override
	public List<T> searchByIds(Serializable[] ids) {
		List<T> lists = new ArrayList<T>();
		for (int i = 0; i < ids.length; ++i) {
			Serializable id = ids[i];
			lists.add(this.searchById(id));
		}
		return lists;
	}

	@Override
	public void deleteById(Serializable id) {
		Class clazz = GenericUtils.getActualClass(this.getClass());
		String simpleName = clazz.getSimpleName();  //Blog
		String rename = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);   //blog
		String strID = new StringBuilder().append(rename).append("Id").toString();

//		System.out.println(simpleName);
//		System.out.println(rename);
//		System.out.println(strID);
		//delete from Blog blog where blog.blogId =: blogId
//		System.out.println("delete from " + simpleName + " " + rename
//				+ " where " + rename + "." + strID + " =: " + strID);

		hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(
			"delete from " + simpleName + " " + rename + "  where " + rename + "." + strID + " =:" + strID)
			.setParameter(strID, id)
			.executeUpdate();

	}

	@Override
	public void update(T t) {
		hibernateTemplate.update(t);
	}

	@Override
	public List<T> searchCollectionByConditionNoPage(String hqlWhere,
			final Object[] objects, LinkedHashMap<String, String> orderby) {

		Class clazz = GenericUtils.getActualClass(this.getClass());
		String hqlOrderby = this.orderbyCondition(orderby);
		final String hql = "from " + clazz.getSimpleName() + " o " + hqlWhere
				+ hqlOrderby;

		List<T> list = (List<T>) hibernateTemplate
				.execute(new HibernateCallback<T>() {

					@Override
					public T doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						setQueryParameters(query, objects);
						return (T) query.getResultList();
					}
				});

		return list;
	}
	
	@Override
	public List<T> searchCollectionByCondition(String hqlWhere,
			final Object[] objects, LinkedHashMap<String, String> orderby,
			final PageInfo pageInfo) {
		Class clazz = GenericUtils.getActualClass(this.getClass());
		String hqlOrderby = this.orderbyCondition(orderby);
		final String hql = "from " + clazz.getSimpleName() + " o " + hqlWhere
				+ hqlOrderby;

		List<T> list = (List<T>) hibernateTemplate
				.execute(new HibernateCallback<T>() {

					@Override
					public T doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						setQueryParameters(query, objects);
						pageInfo.setTotalResult(query.getResultList().size());
						query.setFirstResult(pageInfo.getBeginResult());
						query.setMaxResults(pageInfo.getPageSize());
						return (T) query.getResultList();
					}
				});

		return list;
	}

	private String orderbyCondition(LinkedHashMap<String, String> orderby) {
		String hqlOrderby = "";
		if (orderby != null) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("order by ");
			for (Entry entry : orderby.entrySet()) {
				buffer.append(entry.getKey() + " " + entry.getValue() + " ,");
			}
			hqlOrderby = buffer.deleteCharAt(buffer.length() - 1).toString();
		}
		return hqlOrderby;
	}

	private void setQueryParameters(Query query, Object[] objects) {
		for (int i = 0; objects != null && i < objects.length; ++i) {
			query.setParameter(i, objects[i]);
		}
	}

	@Override
	public void deleteByCollection(Collection<T> collection) {
		hibernateTemplate.deleteAll(collection);
	}

	@Override
	public void saveCollection(Collection<T> collection) {
		for (Iterator<T> iterator = collection.iterator(); iterator.hasNext();) {
			T t = iterator.next();
			this.save(t);
		}
	}

}
