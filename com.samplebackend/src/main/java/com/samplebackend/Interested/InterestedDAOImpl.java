package com.samplebackend.Interested;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.samplebackend.Blog.Blog;
import com.samplebackend.Event.Event;

@Transactional
@EnableTransactionManagement
public class InterestedDAOImpl implements InterestedDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	public void addInterested(Interested interested) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(interested);
	}
	public void deleteInterested(Interested interested) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(interested);
	}
	public List<Interested> listInterested() {
		Session session = sessionFactory.getCurrentSession();
		List<Interested> list = session.createQuery("from Event where posted="+1).list();	
		return list;
	}
	
	
}
