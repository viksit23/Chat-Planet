package com.samplebackend.friend;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Transactional
@Repository
@EnableTransactionManagement
public class FriendDAOImpl implements FriendDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	public void addFriend(Friend friend) {
		Session session = sessionFactory.getCurrentSession();
		session.save(friend);
	}
	
	
	public void updateFriend(Friend friend){
		Session session = sessionFactory.getCurrentSession();
		session.update(friend);
	}

	public List<Friend> getFriendRequsts(long userId) {
		
		Session session = sessionFactory.getCurrentSession();
		List<Friend> list = (List<Friend>) session.createQuery("from Friend where userId ="+userId+"and status='SENT'").list();
		
		return list;
	}

	

	public Friend getFriend(long loggedInUserId, long friendId ) {
		Session session = sessionFactory.getCurrentSession();
		Friend friend = (Friend)session.createQuery("from Friend where userId ="+loggedInUserId+"and friendId="+friendId).list().get(0);
		
		return friend;
	}
	
	public List<Friend> listFriends(long userId){
		Session session = sessionFactory.getCurrentSession();
		List<Friend> list = (List<Friend>) session.createQuery("from Friend where userId ="+userId+"").list();
		
		return list;
}


	public void delFriend(long loggedInUserId, long friendId) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("delete from Friend where friendId="+friendId + " and userId=" + loggedInUserId).executeUpdate();	
	}
	}
