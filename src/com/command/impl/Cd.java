package com.command.impl;

import java.io.File;

import com.command.Command;
import com.controller.FileController;

public class Cd implements Command {

	public int excute(String[] command) {
		
		int i = Command.UNDONE;
		if(command.length == 1) {
			System.out.println(FileController.basePath);
			i = Command.SUCCESS;
		}else if(command.length == 2) {
			//返回上层
			if( command[1].equals("..") ) {
				
				//到了到了根目录，没有上一层
				File file = new File(FileController.basePath);
				if(file.getParent() != null) {
					FileController.basePath = file.getParent();
				}
				i = Command.SUCCESS;
			}else if( command[1].equals(".") ) {
				System.out.println(FileController.basePath);
				i = Command.SUCCESS;
			}else {
				/*if(!command[1].contains(":")) { 
					//相对路径,拼接
					command[1] = FileController.basePath + "\\" + command[1];
				}*/
				
				File file = new File(command[1]);
				if(!file.isAbsolute()) {
					//相对路径,拼接
					command[1] = FileController.basePath + "\\" + command[1];
				}
				//切换到目标路径
				if(file.exists() && file.isDirectory() ) {
					FileController.basePath = file.getAbsolutePath();
					i = Command.SUCCESS;
				}else {
					i = Command.NOTFINDSECONDE;
				}
			}
		}
		return i;
	}

	/**
	 * cd 帮助
	 */
	public void help() {
		
		StringBuilder str = new StringBuilder();
		str.append("----------------cd help-----------\n");
		str.append("命令格式：\n");
		str.append("cd [路径]   显示当前目录名或改变当前目录。\n\n");
		str.append("只键入 cd 或 cd .  将列出当前目录绝对路径\n");
		str.append("键入 cd ..  改变当前目录到上一级\n");

		str.append("[路径] 应该表示为:[drive:][path][filename]\n");
		str.append("如: cd C:/user 将目录切换到C:/user \n");
		
		System.out.println(str);
	}

}
