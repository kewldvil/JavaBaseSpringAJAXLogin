package com.sopheak.app.dao.implement;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sopheak.app.dao.IUserDao;
import com.sopheak.app.entities.User;

@Repository
public class UserDaoImp implements IUserDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public User findUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where username= :username");
		query.setParameter("username", username);
		
		User user = (User) query.uniqueResult();
		return user;
	}

}
