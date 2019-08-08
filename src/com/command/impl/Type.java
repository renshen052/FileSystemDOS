package com.command.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.command.Command;
import com.controller.FileController;

public class Type implements Command {

	@Override
	public int excute(String[] command) {

		// 判断命令合法
		if (command.length != 2) {
			return Command.ERRORCOMMAND;
		}

		File file = new File(command[1]);

		if(!file.isAbsolute()) {
			file = new File(FileController.basePath + "/" + command[1]);
		}
		
		
		if (file.isFile()) {
			char[] c = command[1].toCharArray();
			// 是文件且后缀为"txt"
			if ( c[c.length-1]=='t' && c[c.length-2]=='x' && c[c.length-3]=='t' ) {
				return type(file);
			}
		}
		return Command.NOTFINDSECONDE;
	}

	/**
	 * 读文件
	 */
	public int type(File file) {
		System.out.println("--------------:" + file.getName());
		try(
				InputStream is = new FileInputStream(file);
				Reader reader = new InputStreamReader(is);
				BufferedReader bufferedReader = new BufferedReader(reader);
		){
			String str;
			while( (str = bufferedReader.readLine()) != null ) {
				System.out.println(str);
			}
			return Command.SUCCESS;
		}catch(Exception e) {
			return Command.ERRORCOMMAND;
		}
	
	}

	/**
	 * type 帮助
	 */
	public void help() {

		StringBuilder str = new StringBuilder();
		str.append("----------------type help-----------\n");
		str.append("命令格式：\n");
		str.append("type [文本文件]  查看文本文件\n\n");

		str.append("如: type E:\\fileJavaSystem\\test\\水电费第三方.txt \n\n");

		System.out.println(str);

	}

}
