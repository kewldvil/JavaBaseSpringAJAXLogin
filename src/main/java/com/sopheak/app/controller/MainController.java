package com.sopheak.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value="/product")
	public String userpagePage(ModelMap mm){
		System.out.println(proservice.listAllProduct());
		mm.addAttribute("products",proservice.listAllProduct());
		return "product";
	}
	
/*	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
	public ResponseEntity<Map<String,Object>> deleteAction(@RequestParam int id){
		System.out.println("olo");
		Map<String, Object> map = new HashMap<>();
		 if(proservice.deleteProductById(id)){
			 map.put("STATUS", "PRODUCT HAS BEEN DELETED");
			 return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		 }else{
			 map.put("STATUS", "OPERATION ERRORED !");
			 return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		 }
		
	}*/

	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Boolean deleteAction(@RequestParam int id){
		 if(proservice.deleteProductById(id)) return true;
		 else return false;
	
	}
}
