package com.demo.dao.layer;

import com.demo.pojo.User;

public interface UserDAO {
	public String doHibernateLogin(String username, String password);
	public String doHibernateSignUp(User user);
}
