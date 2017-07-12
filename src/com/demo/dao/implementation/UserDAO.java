package com.demo.dao.implementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.demo.dao.implementation.HibernateConnection;
import com.demo.pojo.User;

public class UserDAO implements com.demo.dao.layer.UserDAO{

	public String doHibernateLogin(String username, String password){
		try{
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			
			session.beginTransaction();
			
			List<User> user = session.createQuery("From User where username='"+username+"' and password='"+password+"'").list();
			
			session.close();
			
			if(user.size() == 1) return "login success"	;
			else return "Please try again...";
		}
		catch(Exception e){
			return "Please try again...";
		}
	}
	
	public String doHibernateSignUp(User user){
		try{
			Session session = HibernateConnection.doHibernateConnection().openSession();
			session.beginTransaction();
			
			session.save(user);
			
			session.getTransaction().commit();
			session.close();
			return "Sign Up Successfully...";
		}
		catch(Exception e){
			e.printStackTrace();
			return "User is already there with this username";
		}
	}
	
}
