package com.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dao.registery.RegisteryDAO;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@RequestMapping(name="/products", method=RequestMethod.GET)
	public ModelAndView loadProducts(){
		
		ModelAndView mav = new ModelAndView("products");
		
		//ProductsModel pm = new ProductsModel();
		//mav.addObject("allProducts", pm.getAllProducts());
		//mav.addObject("allProducts", pm.getAllProductsSQL());
		mav.addObject("allProducts", RegisteryDAO.getProductsDAO().getAllProducts());
		return mav;
	}

}