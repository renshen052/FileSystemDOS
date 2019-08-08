package com.command.impl;

import com.command.Command;

public class Help implements Command {

	public int excute(String[] command) {
		int i = Command.UNDONE;
		if(command.length == 1) {
			help();
			i = Command.SUCCESS;
		}else if(command.length > 2){
			i = Command.ERRORCOMMAND;
		}else {
			switch(command[1]) {
			case "cd":
				new Cd().help();
				break;
			case "dir":
				new Dir().help();
				break;
			case "mkdir":
				new Mkdir().help();
				break;
			case "copy":
				new Copy().help();
				break;
			case "del":
				new Del().help();
				break;
			case "type":
				new Type().help();
				break;
			case "help":
				help();
			default:
				i = Command.NOTFINDSECONDE;
				return i;
			}
			i = Command.SUCCESS;
		}
		return i;
	}

	/**
	 * 帮助文档的概述
	 */
	public void help() {
		StringBuilder str = new StringBuilder();
		str.append("-------------------------帮助-------------------------\n");
		str.append("命令格式：\n");
		str.append("命令名称 [参数][参数]\n\n");
		
		str.append("cd \t").append("显示当前目录名或改变当前目录。\n");
		str.append("dir \t").append("显示目录中的文件和子目录列表。\n");
		str.append("mkdir \t").append("创建目录\n");
		str.append("copy \t").append("将一份或多份文件复制到另一个位置。\n");
		str.append("del \t").append("删除一个或数个文件。\n");
		str.append("type \t").append("显示文本文件的内容\n");
		str.append("exit \t").append("退出\n");
		
		str.append("若想查看详细命令，请键入: \"help 命令名称\" 查看  \n");
		System.out.println(str);
	}

}
