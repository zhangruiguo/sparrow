package com.ganji.dpd.sparrow.service;

import java.util.List;

public interface ValidationService {
	public List<String> validate(String host, String port, String user, String pwd,
			String db, String tb, boolean disableBlob) throws Exception;
}
