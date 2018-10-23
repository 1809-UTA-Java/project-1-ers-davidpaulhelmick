package com.revature.ers.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.ers.model.UserRole;
import com.revature.ers.model.Users;
import com.revature.ers.util.HibernateUtil;

public class UserDAO {
	public List<Users> getUsers() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from Users").list();
	}
	
	public Users getUser(Integer employeeId) {
		List<Users> users = new ArrayList<Users>();
		
		Session session = HibernateUtil.getSession();
		
		users = session.createQuery("from Users e where e.userID=:eId").setInteger("eId", employeeId).list();
		if (users.isEmpty()) {
			return null;
		}
		
		return users.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public Users getUserByName(String aName) {
		Session session = HibernateUtil.getSession();
		List<Users> users = new ArrayList<Users>();
		
		users = (List<Users>) session.createQuery(
				"from Users where username = :nameVar")
				.setString("nameVar", aName).list();
		if(users.isEmpty()) {
			return null;
		}
		
		return users.get(0);
	}
	
	public void saveUser(Users e) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(e);
		tx.commit();
	}
	
	public void setUser(Users user) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
	}
	
	public UserRole role(Integer statusNum) {
		Session session = HibernateUtil.getSession();
		return (UserRole) session.createQuery("from UserRole rs where rs.urID=:num").setInteger("num", statusNum).list().get(0);
	}

}
