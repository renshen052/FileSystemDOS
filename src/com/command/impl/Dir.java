package com.command.impl;

import java.io.File;
import java.io.FileFilter;
import java.sql.Date;

import com.command.Command;
import com.controller.FileController;

public class Dir implements Command {

	public int excute(String[] command) {
		

		int i = UNDONE;
		// 显示目录中的文件和子目录列表
		if (command.length == 1) {
			
			i = list(FileController.basePath,"/D/R");
		} else if (command.length == 2) {
			
			//command[1] = Command.replaceAll(command[1]);
			
			// 测试command第二个参数是否是一个有效路径
			File f = new File(command[1]);
			if (f.exists()) {// 存在
				
				i = list(command[1],"/D/R"); //默认，显示子目录、显示文件，不显示隐藏文件
			} else {
				// 不是，那么可能是附加参数
				i = list(FileController.basePath, command[1]);
			}
		} else if (command.length == 3) {
			
		//	command[1] = Command.replaceAll(command[1]);
			
			// 测试command第二个参数是否是一个有效路径
			File f = new File(command[1]);
			if (f.exists()) {
				
				// 存在,传递附加参数
				i = list(command[1], command[2]);
			} else {// 不是,语法错误
				
				i = Command.ERRORCOMMAND;
			}

		} else {// 参数过多，语法错误
			
			i = Command.ERRORCOMMAND;
		}

		return i;

	}

	/**
	 * 根据参数，列出basePath的指定文件
	 * 
	 * @param basePath  指定路径
	 * @param parameter 参数
	 * @return 执行状态
	 */
	private int list(String basePath, String parameter) {

		int i = Command.UNDONE;
		if(basePath.charAt(basePath.length()-1) !='\\'){
			basePath = basePath + "\\";
		}
		File file = new File(basePath);
		
		File[] files = null;
		try {
			
			files = file.listFiles(new FileFilter() {
				// 过滤文件
				public boolean accept(File pathname) {
					boolean i = false;
					
					if ( ( parameter.indexOf("/D") != -1 && pathname.isDirectory() ) || //文件
						  (  parameter.indexOf("/R") != -1 && pathname.isFile() ) || //目录
						  ( !pathname.isHidden() && parameter.indexOf("/H") != -1  ) 
					){
						/*if( ( parameter.indexOf("/D") == -1 && pathname.isDirectory() ) ||
								( parameter.indexOf("/R") == -1 && pathname.isFile() )
								
						){
							return false;
						}*/
						return true;
					}
					
					return false;
				}
			});
		} catch (Exception e) {
			System.out.println("获取目录失败");
		}
		
		System.out.println("\n" + file.toString() + "的目录");

		System.out.print(showFiles(files));
		// 成功
		i = Command.SUCCESS;

		return i;
	}

	/**
	 * 显示文件列表
	 * 
	 * @param files
	 * @return
	 */
	public StringBuilder showFiles(File[] files) {
		StringBuilder str = new StringBuilder();
		for (File f : files) {
			str.append("\n");
			str.append(f.getName() + " ").append("<").append(f.isDirectory() ? "目录>" : "文件>" + "  ");
			str.append(f.length() / 1024.0 / 1024 + "MB ");
			str.append("lastModified:").append(new Date(f.lastModified()).toLocalDate()).append("\n");
		}
		str.append("\n");
		return str;
	}

	/**
	 * dir 帮助
	 */
	public void help() {
		System.out.println("----------------dir help-----------");

		StringBuilder str = new StringBuilder();
		str.append("----------------dir help-----------\n");
		str.append("命令格式：\n");
		str.append("dir [路径] [参数]\n\n");
		str.append("只键入 dir 表示列出当前目录中的所有文件和子目录列表");

		str.append("[路径] 应该表示为:[drive:][path][filename]\n");
		str.append("指定要列出的驱动器、目录和文件\n");
		str.append("如: dir C:/user/my.ini \n");

		str.append("[参数] 表示显示带有指定属性的文件\n");
		str.append("[/D] 显示目录\n");
		str.append("[/R] 显示文件\n");
		str.append("[/H] 显示隐藏文件\n");
		str.append(" 如:  dir /D/R/H  列出当前目录下的 目录、制度文件，隐藏文件\n\n");
		System.out.println(str);

	}

}
