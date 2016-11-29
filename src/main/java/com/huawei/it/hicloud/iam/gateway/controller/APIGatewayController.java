package com.huawei.it.hicloud.iam.gateway.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class APIGatewayController{

	private static Logger logger = Logger.getLogger(APIGatewayController.class);
	
	@RequestMapping("/me")
	@ResponseBody
	public Object who(HttpServletRequest request,
			HttpServletResponse response) {
		Object user;
		try{
			user =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e){
			user=null;
		}
		//User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	@RequestMapping(value="/tokens", method=RequestMethod.POST)
	@ResponseBody
	public void token(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("tokens");
	}
	
}
