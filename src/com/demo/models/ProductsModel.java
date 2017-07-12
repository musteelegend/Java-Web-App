package com.demo.models;

import java.util.List;

import org.hibernate.Session;

import com.demo.pojo.Products;

public class ProductsModel {
	
	public List<Products> getAllProducts(){
		
		Session session = HibernateConnection.doHibernateConnection().openSession();
		
		List<Products> allProducts = session.createQuery("From Products").list();
		session.close();
		
		return allProducts;
	}
	
	public List<Object[]> getAllProductsSQL(){
		Session session = HibernateConnection.doHibernateConnection().openSession();
		
		List<Object[]> allProducts = session.createSQLQuery("select * from products").list();
		session.close();
		
		return allProducts;
	}

}
