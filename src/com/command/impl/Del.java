package com.command.impl;

import java.io.File;
import java.util.Scanner;

import com.command.Command;
import com.controller.FileController;

public class Del implements Command {

	@Override
	public int excute(String[] command) {
		
		if(command.length < 2) {
			return Command.ERRORCOMMAND;
		}
		

		// 是否需要确认，默认不需要
		boolean p = false;
		// 是否要递归删除，默认否
		boolean r = false;

		if (command[1].contains("/P") || command[1].contains("/p")) {// 需要确认
			p = true;
		}
		if (command[1].contains("/R") || command[1].contains("/r")) {// 需要递归删除
			r = true;
		}

		for (String str : command) {
			File file = new File(str);
			
			//绝对路径
			if (!file.exists()) {
				//相对路径
				file = new File(FileController.basePath + "//" + str);
				if(!file.exists()) {
					// 文件不存在，看下一个
					continue;
				}
			}
				
			del(file, p, r);
		}

		return Command.SUCCESS;
	}

	/**
	 * 删除目录或者文件
	 * @param file
	 * @param p
	 * @param r
	 */
	public void del(File file, boolean p, boolean r) {
		
		//递归的删除
		if(file.isDirectory() && file.list().length > 0) {
			if(r) {
				for(File f : file.listFiles()) {
					del(f,p,r);
				}
				//删除空目录
				if(p && !userConfirm(file)) {//需要确认，且用户输入取消,不删除
					return;
				}else {//否则删除
					file.delete();
				}
				
				//空文件夹删除不做确认
				//file.delete();
			}
			
		}else {
			//删除文件
			if(p && !userConfirm(file)) {//需要确认，且用户输入取消,不删除
				return;
			}else {//否则删除
				file.delete();
			}
		}
	}
	
	/**
	 * 向用户询问，是否要继续删除
	 * @param file
	 * @return
	 * 继续删除返回true，否则返回false
	 */
	public boolean userConfirm(File file) {
		Scanner in = new Scanner(System.in);
		String who = file.isDirectory()?" <目录> ":" <文件> ";
		System.out.println("确认要删除 " + file.getName() + who + " 吗？y继续/其他终止");
		String u = in.next();
		if(u.equals("y")) {
			return true;
		}else {
			System.out.println(file.getName() + "删除终止");
			return false;
		}
	}
	
	
	

	/**
	 * del 帮助
	 */
	public void help() {

		StringBuilder str = new StringBuilder();
		str.append("----------------del help-----------\n");
		str.append("命令格式：\n");
		str.append("del [参数][names]   删除目录或者文件。\n\n");

		str.append(" names    指定一个或多个文件或者目录列表,可以是绝对路径或相对路径\r\n");
		str.append("/P   删除每一个文件之前提示确认\n");
		str.append("/R   如果删除的是目录，那么进行递归的删除，\n");
		str.append("要删除的目录不为空且有子目录，递归删除，否则停止删除\n");

		str.append("如: del E:\\fileJavaSystem\\test\\水电费第三方.txt \n\n");

		System.out.println(str);
	}

}
