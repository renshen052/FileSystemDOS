package com.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import java.util.Scanner;

import com.command.Command;
import com.command.impl.Cd;
import com.command.impl.Copy;
import com.command.impl.Del;
import com.command.impl.Dir;
import com.command.impl.Help;
import com.command.impl.Mkdir;
import com.command.impl.Type;
import com.utils.Init;

public class FileController {
	/**
	 * 当前基本路径
	 */
	public static String basePath;
	/**
	 * 当前用户
	 */
	static String user;
	
	/**
	 * 当前执行的命令
	 */
	Command CurrentCommand;
	
	Scanner in = new Scanner(System.in);
	
	
	public FileController() {
		//初始化
		basePath = Init.path;
		user = Init.user;
		CurrentCommand = null;
	}
	
	
	public void start() {
		System.out.println("欢迎来到 DosfileSystem! 如果需要帮助，请键入 help");
		controller();
		
	}
	
	/**
	 * 格式化命令输入
	 * @return
	 */
	public String[] getCinFormat() {
		System.out.print(basePath + " #" + user + "#:");
		String parse = in.nextLine(); //输入
		String[] command = parse.trim().split(" ");
		command[0] = command[0].toLowerCase();//全部转换到小写
		return command;
	}
	
	/**
	 * 控制中枢
	 */
	public void controller() {
		
		while(true) {
			String[] command = getCinFormat();
			switch(command[0]) {
			case "cd":
				CurrentCommand = new Cd();
				break;
			case "dir":
				CurrentCommand = new Dir();
				break;
			case "mkdir":
				CurrentCommand = new Mkdir();
				break;
			case "copy":
				CurrentCommand = new Copy();
				break;
			case "del":
				CurrentCommand = new Del();
				break;
			case "type":
				CurrentCommand = new Type();
				break;
			case "exit":
				System.exit(0);//退出
			case "help":
				CurrentCommand = new Help();
				break;
			default:
				System.out.println(command[0] + "未找到");
			}
			//执行命令,返回执行状态
			if(CurrentCommand == null)continue;
			int i = CurrentCommand.excute(command);
			showStatus(i,command);
		}
	}
	


	private void showStatus(int i, String[] command) {
		switch(i) {
		case Command.SUCCESS :
			//执行成功不回显
			break;
		case Command.ERRORCOMMAND :
			System.out.println(command[0] + " 语法错误");
			break;
		case Command.ERROR:
			System.out.println(command[0] + "执行出错");
		case Command.NOTFINDCOMMAND :
			System.out.println(command[0] + " 未找到该命令");
			break;
		case Command.NOTFINDSECONDE :
			System.out.println(command[1] + " 未找到");
			break;
		case Command.DONTREAD :
			System.out.println(command[1] + " 查看的文件不可读");
			break;
		case Command.NOTADIRECTORY:
			System.out.println(command[3] + " 不是一个目录");
			break;
		default:
			System.out.println("状态码错误");
		}
	}
}
