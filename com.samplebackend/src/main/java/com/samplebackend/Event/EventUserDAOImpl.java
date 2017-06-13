package com.samplebackend.Event;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.samplebackend.job.JobUser;
import com.samplebackend.job.JobUserDAO;

@Repository
@Transactional
@EnableTransactionManagement
public class EventUserDAOImpl implements EventUserDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<EventUser> getall()
	{
	
		Session session=sessionFactory.getCurrentSession();
		List<EventUser> list=session.createQuery("from EventUser").list();
		return list;
		
	}

	public void addEventApplied(EventUser eventUser)
	{
		Session session=sessionFactory.getCurrentSession();
		session.save(eventUser);
	}
	
}
