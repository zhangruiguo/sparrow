package com.ganji.dpd.sparrow.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ganji.dpd.sparrow.service.ValidationService;



@RestController
@RequestMapping("/validation")
public class ValidationController extends BaseController{
	@Resource
	private ValidationService validationService;
	
	@RequestMapping(value = "/connection",method=RequestMethod.GET)
	public Object validateConnection(HttpServletRequest request){
		String host = request.getParameter("host");
		String port = request.getParameter("port");
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		String db = request.getParameter("db");
		String tb = request.getParameter("tb");
		
		if(host == null || port == null || user == null){
			return message(FAIL, "illegal parameter");
		}
		
		try {
			String disableBlob = request.getParameter("disableBlob");
			List<String> cols =validationService.validate(host, port, user, pwd, db, tb,(disableBlob !=null && "true".equals(disableBlob.trim())));
			
			String withCols = request.getParameter("withCols");
			if(withCols != null && "true".equals(withCols)){
				return message(SUCCESS, cols);
			} else {
				return message(SUCCESS, "");
			}
			
		} catch (Exception e) {
			return message(FAIL, e.getMessage());
		}
	}
}
	

