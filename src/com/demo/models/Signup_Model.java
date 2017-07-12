package com.demo.models;

import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;

import com.demo.pojo.User;

public class Signup_Model {
	
	public String doSignUp(String username , String password , String gender , String vehicle , String country , String image)
	{
		try
		{
			Database_Connectivity dc = new Database_Connectivity();
			Statement statment = dc.do_db_connection();
			
			statment.execute("insert into user values('"+username+"' , '"+password+"' , '"+gender+"' , '"+vehicle+"' , '"+country+"' , '"+image+"')");
			
			return "Sign Up Successfully...";
		}
		catch(Exception e)
		{
			System.out.println(e);
			return "Something went wrong pelase try again ! ! !";
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
