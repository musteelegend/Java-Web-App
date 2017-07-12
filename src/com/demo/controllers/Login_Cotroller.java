package com.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dao.registery.RegisteryDAO;
import com.demo.pojo.User;

@Controller
public class Login_Cotroller /*implements Validator*/ {

	
	@RequestMapping(value="/login" , method=RequestMethod.GET)
	public ModelAndView loadloginpage()
	{
		ModelAndView mav = new ModelAndView("login");
		System.out.println("Login method has been called");
		User user = new User();
		mav.addObject("user", user);
		return mav;
	}
	
	/*
	@RequestMapping(value="/login" , method=RequestMethod.GET)
	public String loadloginpage()
	{
		return "login";
	}
	*/

	@RequestMapping(value="/login" , method=RequestMethod.POST)
	public String do_login(HttpServletRequest req , Model md , HttpSession session , @Valid User user, BindingResult br)
	{
		try
		{
			//System.out.println(br.getAllErrors().size());
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			System.out.println("Username and pasword are : "+username +"  "+ password);
			if(br.getAllErrors().size() > 0){
				System.out.println("Server side validation takes place....");
			}
			else{
			/*Login_Model lm = new Login_Model();
			String message = lm.doHibernateLogin(username, password);*/
			String message = RegisteryDAO.getUserDAO().doHibernateLogin(username, password);
			if(message.equals("login success"))
			{
				session.setAttribute("username", username);
				return "redirect:/myprofile";
			}
			else
			{
				md.addAttribute("error_msg", message);
			}
			/*String message = lm.do_login_process(username, password);
			
			if(message.equals("login success"))
			{
				session.setAttribute("username", username);
				return "redirect:/myprofile";
			}
			else
			{
				md.addAttribute("error_msg", message);
			}*/
			}
			return "login";
		}
		catch(Exception e)
		{
			return "login";
		}
	}

	/*@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "username", "field.required");
		
	}*/
	
}
