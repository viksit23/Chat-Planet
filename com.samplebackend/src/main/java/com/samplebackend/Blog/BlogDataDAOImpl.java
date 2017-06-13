package com.samplebackend.Blog;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
@Transactional
@EnableTransactionManagement
public class BlogDataDAOImpl implements BlogDataDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public void addBlogData(BlogData blogdata) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(blogdata);
	}
	
	public BlogData getBlogDataById(int blogdataid){
		Session session = sessionFactory.getCurrentSession();
		BlogData blogData = (BlogData)session.createQuery("from BlogData where BlogDataId="+blogdataid).list().get(0);
		return blogData;
	}

	public List<BlogData> listBlogData() {
		Session session = sessionFactory.getCurrentSession();
		List<BlogData> list  = session.createQuery("from BlogData").list();
		return list;
	}
	
	public void deleteBlogData(BlogData blogdata){
		Session session = sessionFactory.getCurrentSession();
		session.delete(blogdata);
	}

}