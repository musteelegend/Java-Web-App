package com.demo.controllers;

import java.io.File;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dao.registery.RegisteryDAO;
import com.demo.pojo.User;

@Controller
@MultipartConfig
public class Signup_Controller
{
	@RequestMapping(value="/signup" , method=RequestMethod.GET)
	public ModelAndView load_signup()
	{
		ModelAndView mav = new ModelAndView("signup");
		return mav;
	}
	
	@RequestMapping(value="/signup" , method=RequestMethod.POST)
	public ModelAndView doSignUpProcess(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView("signup");
		String message = "";
		
		if(ServletFileUpload.isMultipartContent(request))
		{
			try
			{
			List<FileItem> data = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			
			String username = data.get(0).getString();
			String password = data.get(1).getString();
			String repassword = data.get(2).getString();
			String gender = data.get(3).getString();
			String vehicle = data.get(4).getString();
			String country = data.get(5).getString();
			
			String image = new File(data.get(6).getName()).getName();
			
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setGender(gender);
			user.setCountry(country);
			user.setVehicle(vehicle);
			user.setImage(image);
			
			if(password.equals(repassword))
			{
				/*Signup_Model sm = new Signup_Model();
				
				//message = sm.doSignUp(username, repassword, gender, vehicle, country, image);
				message = sm.doHibernateSignUp(user);*/
				message = RegisteryDAO.getUserDAO().doHibernateSignUp(user);
				String path = request.getSession().getServletContext().getRealPath("/") + "//WEB-INF//images//";
				
				data.get(6).write(new File(path + File.separator + image));
				
			}
			else
			{
				message = "Password does not match..please try again";
			}
			
			}
			catch(Exception e)
			{
				System.out.println(e);
				message = "Please try again....";
			}
		}
		mav.addObject("message", message);
		return mav;
	}
	
	@RequestMapping(value="/dynamic/{message}" , method=RequestMethod.GET)
	public ModelAndView dynamicDemo(@PathVariable("message") String message)
	{
		ModelAndView mav = new ModelAndView("signup");
		System.out.println(message);
		return mav;
	}
}