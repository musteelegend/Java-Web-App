package com.demo.models;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.demo.pojo.User;

public class Login_Model
{
	public String do_login_process(String username , String password)
	{
		try
		{
			Database_Connectivity dc = new Database_Connectivity();
			Statement statement = dc.do_db_connection();
			
			ResultSet rs = statement.executeQuery("select count(*) from user where username='"+username+"' and password='"+password+"'");
			int count = 0;
			while(rs.next())
			{
				count = rs.getInt(1);
			}
			
			rs.close();
			
			if(count == 1)
			{
				return "login success";
			}
			else
			{
				return "Username OR Password does not match";
			}
			
		}
		catch(Exception e)
		{
			return "Something went wrong...Please try again ! ! !";
		}
	}
	
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
}
