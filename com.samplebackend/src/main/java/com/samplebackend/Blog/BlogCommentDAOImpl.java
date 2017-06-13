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
public class BlogCommentDAOImpl implements BlogCommentDAO{


	@Autowired
	SessionFactory sessionFactory;
	
	public void addBlogComment(BlogComment blogComment) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(blogComment);
	}
	
	public List<BlogComment> listBlogComment() {
		Session session = sessionFactory.getCurrentSession();
		List<BlogComment> list  = session.createQuery("from BlogComment").list();
		return list;
	}
	
	
	
}
