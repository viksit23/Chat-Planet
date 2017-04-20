package com.samplebackend.Event;

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
public class EventDAOImpl  implements EventDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	
	public void addEvent(Event event) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(event);
	}

	
	public Event getEventById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Event event = (Event)session.createQuery("from Event where EventId="+id).list().get(0);	
		return event;
	}

	
	public List<Event> listEvents() {
		Session session = sessionFactory.getCurrentSession();
		List<Event> list = session.createQuery("from Event where posted="+1).list();	
		return list;
	}
}