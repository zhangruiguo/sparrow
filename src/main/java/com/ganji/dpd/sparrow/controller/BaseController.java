package com.ganji.dpd.sparrow.controller;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
	protected static final int SUCCESS = 1;
	protected static final int FAIL = 0;
	
	protected Map<String,Object> message(int status,Object data){
		HashMap<String,Object> message = new HashMap<String,Object>();
		message.put("status", status);
		message.put("data", data);
		return message;
	}
}
