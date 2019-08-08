package com.command.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.stream.events.Comment;

import com.command.Command;
import com.controller.FileController;

public class Mkdir implements Command {

	@Override
	public int excute(String[] command) {
		
		int i = Command.UNDONE;
		
		//建立目录时，默认不递归
		boolean r = false;
		
		if(command.length == 2) {
			
			//递归参数
			if(command[1].equals("/R") || command[1].equals("/r")) {//有,第二参数有错误
				return Command.NOTFINDSECONDE;
			}
			
			//检测路径类型,如果是相对路径，要拼接
			if(!command[1].contains(":")) {
				command[1] = FileController.basePath + "/" + command[1];
			}
			
			i = mkdir(command[1],r);
			
			
		}else if(command.length == 3){
			//递归参数
			if(command[1].equals("/R") || command[1].equals("/r")) {//有,递归
				r = true;
			}else {
				return Command.ERRORCOMMAND;
			}
			
			//检测路径类型,如果是相对路径，要拼接
			if(!command[2].contains(":")) {
				command[2] = FileController.basePath + "/" + command[2];
			}
			
			i = mkdir(command[2],r);
			
		}else {//命令语法不正确
			return Command.ERRORCOMMAND;
		}
		
		return i;
	}

	/**
	 * 建立目录，当r为true时递归
	 * @param command
	 * @param r
	 * @return
	 */
	private int mkdir(String command, boolean r) {
		
		File f = new File(command);
		File p = f.getParentFile();
		//父路径不存在、递归
		if(!p.exists() && r) {
			//父路径不存在，创建父路径
			mkdir(p.getPath(),r);
			//然后创建子路径
			f.mkdir();
			return Command.SUCCESS;
		}else if( p.exists()) {
			f.mkdir();
			return Command.SUCCESS;
		}else {
			return Command.ERRORCOMMAND;
		}
	}

	/**
	 * mkdir 帮助
	 */
	public void help() {

		StringBuilder str = new StringBuilder();
		str.append("----------------mkdir help-----------\n");
		str.append("命令格式：\n");
		str.append("mkdir [参数][路径]   新建目录。\n\n");

		str.append("/R   如果新建的目录上一级不存在，那么进行递归的建立，\n");
		str.append("如: mkdir E:\\fileJavaSystem\\test \n");
		str.append("如果要建立的目录test的上级目录fileJavaSsytem不存在，需要加入递归参数 /R ,否则建立失败\n\n");
		System.out.println(str);

	}

}
