package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Init {
	public static String path;
	public static String user;
	static {
		Properties p = new Properties();
		
		try(
			InputStream in = Init.class.getClassLoader().getResourceAsStream("file.properties");
		) {
			p.load(in);
			path = p.getProperty("initPath");
			user = p.getProperty("user");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
