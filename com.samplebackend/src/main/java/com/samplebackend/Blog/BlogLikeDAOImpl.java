package com.samplebackend.Blog;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public class BlogLikeDAOImpl implements BlogLikeDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public void addBlogLike(BlogLike blogLike) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(blogLike);
	}
	
	public List<BlogLike> listBlogLike() {
		Session session = sessionFactory.getCurrentSession();
		List<BlogLike> list  = session.createQuery("from BlogLike").list();
		return list;
}
}
