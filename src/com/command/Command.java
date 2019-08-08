package com.command;

import java.util.regex.Matcher;

/**
 * @author h w j
 * @time 2019年8月6日
 * @instruction
 * 命令
 */
public interface Command {
	
	/**
	 * 命令未找到
	 */
	final int NOTFINDCOMMAND = -1;
	
	/**
	 * 参数未找到
	 */
	final int NOTFINDSECONDE = -2;
	
	/**
	 * 语法异常
	 */
	final int ERRORCOMMAND = -3;
	
	/**
	 * 执行成功
	 */
	final int SUCCESS = 1;
	
	/**
	 * 未完成
	 */
	final int UNDONE = 0;
	
	/**
	 * 不可读
	 */
	final int DONTREAD = 2;

	/**
	 * 执行出错
	 */
	final int ERROR = 3;
	
	/**
	 * 不是目录
	 */
	final int NOTADIRECTORY = 4;
	
	/**
	 * 执行命令
	 */
	int excute(String[] command);
	
	/**
	 * 命令的帮助
	 */
	void help();
	
}
