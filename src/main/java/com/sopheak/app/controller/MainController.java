package com.sopheak.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sopheak.app.service.IProductService;

@Controller
public class MainController {
	@Autowired
	private IProductService proservice;
	
	@RequestMapping(value="/")
	public String homePage(ModelMap mm){
		System.out.println(proservice.listAllProduct());
		mm.addAttribute("test",proservice.listAllProduct());
		return "index";
	}
}
